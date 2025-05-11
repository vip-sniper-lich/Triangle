package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BankCardTest {

    @DisplayName("Тест базового конструктора банковской карты")
    @ParameterizedTest(name = "Создание карты с номером={0}, CSV={1}, сроком действия={2}")
    @CsvSource({
            "'1234567812345678', '123', '12/25'",
            "'8765432187654321', '456', '06/23'",
            "'1111222233334444', '789', '01/30'"
    })
    void testConstructorBasic(String numberCard, String CSV, String serviceLife) {
        BankCard card = new BankCard(numberCard, CSV, serviceLife);

        assertEquals(numberCard, card.getNumberCard());
        assertEquals(CSV, card.getCSV());
        assertEquals(serviceLife, card.getServiceLife());
        assertNull(card.getId_owner());
        assertNull(card.getStatus());
        assertNull(card.getScore());
    }

    @DisplayName("Тест конструктора банковской карты со статусом")
    @ParameterizedTest(name = "Создание карты со статусом={0}, номером={1}, CSV={2}, сроком действия={3}")
    @CsvSource({
            "'active', '1234567812345678', '123', '12/25'",
            "'blocked', '8765432187654321', '456', '06/23'",
            "'overdue', '1111222233334444', '789', '01/30'"
    })
    void testConstructorWithStatus(String status, String numberCard, String CSV, String serviceLife) {
        BankCard card = new BankCard(status, numberCard, CSV, serviceLife);

        assertEquals(status, card.getStatus());
        assertEquals(numberCard, card.getNumberCard());
        assertEquals(CSV, card.getCSV());
        assertEquals(serviceLife, card.getServiceLife());
        assertNull(card.getId_owner());
        assertNull(card.getScore());
    }

    @DisplayName("Тест оплаты с карты")
    @ParameterizedTest(name = "Оплата со статусом={0}, балансом={1}, ценой={2}, ожидаемый результат={3}")
    @CsvSource({
            "'active', '1000', '500', true",
            "'active', '200', '500', false",
            "'blocked', '1000', '500', false",
            "'overdue', '1000', '500', false",
            "'unopened', '1000', '500', false"
    })
    void testPay(String status, String score, String price, boolean expected) {
        BankCard card = new BankCard(score, "owner1", status, "1234567812345678", "123", "12/25");

        assertEquals(expected, card.pay(price));

        if (expected) {
            assertEquals(String.valueOf(Integer.parseInt(score) - Integer.parseInt(price)), card.getScore());
        }
    }

    @DisplayName("Тест перевода между картами")
    @ParameterizedTest(name = "Перевод с карты со статусом={0}, балансом={1}, суммой={2}, на карту с балансом={3}, ожидаемый результат={4}")
    @CsvSource({
            "'active', '1000', '500', '200', true",
            "'active', '200', '500', '100', false",
            "'blocked', '1000', '500', '200', false",
            "'overdue', '1000', '500', '200', false"
    })
    void testTransfer(String fromStatus, String fromScore, String price, String toScore, boolean expected) {
        BankCard fromCard = new BankCard(fromScore, "owner1", fromStatus, "1234567812345678", "123", "12/25");
        BankCard toCard = new BankCard(toScore, "owner2", "active", "8765432187654321", "456", "06/23");

        assertEquals(expected, fromCard.transfer(price, toCard));

        if (expected) {
            assertEquals(String.valueOf(Integer.parseInt(fromScore) - Integer.parseInt(price)), fromCard.getScore());
            assertEquals(String.valueOf(Integer.parseInt(toScore) + Integer.parseInt(price)), toCard.getScore());
        }
    }

    @DisplayName("Тест пополнения карты")
    @ParameterizedTest(name = "Пополнение со статусом={0}, начальным балансом={1}, суммой={2}, ожидаемый результат={3}")
    @CsvSource({
            "'active', '1000', '500', true",
            "'blocked', '1000', '500', false",
            "'overdue', '1000', '500', false",
            "'unopened', '1000', '500', false"
    })
    void testReplenish(String status, String initialScore, String amount, boolean expected) {
        BankCard card = new BankCard(initialScore, "owner1", status, "1234567812345678", "123", "12/25");
        System.out.println("Сумма на карте: " + card.getScore());
        assertEquals(expected, card.replenish(amount));
        if (expected) {
            assertEquals(String.valueOf(Integer.parseInt(initialScore) + Integer.parseInt(amount)), card.getScore());
        }
    }

    @DisplayName("Тест обработки неверного статуса карты")
    @Test
    void testStatusCardWithInvalidCode() {
        BankCard card = new BankCard("1000", "owner1", "active", "1234567812345678", "123", "12/25");

        assertDoesNotThrow(() -> card.statusCard(99));
        // Проверяем, что статус не изменился
        assertEquals("active", card.getStatus());
    }
}