package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.example.Utilities.*;

class UtilitiesTest {

    @ParameterizedTest(name = "Число {0} четное: {1}")
    @CsvSource({
            "0, true",
            "2, true",
            "-4, true",
            "1, false",
            "3, false",
            "-5, false"
    })
    void testIsEven(int number, boolean expected) {
        assertEquals(expected, isEven(number));
    }

    // Тесты для factorial
    @ParameterizedTest(name = "Факториал {0} = {1}")
    @CsvSource({
            "0, 1",
            "1, 1",
            "5, 120",
            "10, 3628800",
            "20, 2432902008176640000"
    })
    void testFactorialValid(int number, long expected) {
        assertEquals(expected, factorial(number));
    }

    @ParameterizedTest(name = "Факториал отрицательного числа {0}")
    @ValueSource(ints = {-1, -5, -10})
    void testFactorialInvalid(int number) {
        assertThrows(IllegalArgumentException.class, () -> factorial(number));
    }

    // Тесты для isPrime
    @ParameterizedTest(name = "Число {0} простое: {1}")
    @CsvSource({
            "0, false",
            "1, false",
            "2, true",
            "3, true",
            "4, false",
            "17, true",
            "25, false",
            "97, true",
            "-5, false"
    })
    void testIsPrime(int number, boolean expected) {
        assertEquals(expected, isPrime(number));
    }

    // Тесты для degreesToRadians
    @ParameterizedTest(name = "{0} градусов = {1} радиан")
    @CsvSource({
            "0, 0",
            "180, 3.141592653589793",
            "90, 1.5707963267948966",
            "360, 6.283185307179586",
            "-45, -0.7853981633974483"
    })
    void testDegreesToRadians(double degrees, double expectedRadians) {
        assertEquals(expectedRadians, degreesToRadians(degrees), 1e-10);
    }

    // Тесты для roundToNDecimalPlaces
    @ParameterizedTest(name = "Округление {0} до {1} знаков = {2}")
    @CsvSource({
            "3.14159, 2, 3.14",
            "2.71828, 3, 2.718",
            "1.23456, 0, 1.0",
            "0.99999, 2, 1.0",
            "-1.235, 2, -1.24",
            "0.5, 0, 1.0"
    })
    void testRoundToNDecimalPlaces(double value, int places, double expected) {
        assertEquals(expected, roundToNDecimalPlaces(value, places));
    }

    @ParameterizedTest(name = "Округление с отрицательным количеством знаков {1}")
    @CsvSource({
            "1.23, -1",
            "5.67, -5"
    })
    void testRoundInvalidPlaces(double value, int places) {
        assertThrows(IllegalArgumentException.class,
                () -> roundToNDecimalPlaces(value, places));
    }

    // Дополнительный тест для граничного случая округления
    @Test
    void testRoundEdgeCase() {
        assertEquals(0.0, roundToNDecimalPlaces(0.0, 5));
    }
}