package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest
{

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0, 1, 1",
            "100, 100, 200",
            "-100000, 100000, 0",
            "999999999, 1, 1000000000"
    })
    @DisplayName("Тест на сложение")
    void testPlus(double first, double second, double expectedResult) {
        assertEquals(expectedResult, Calculator.plus(first, second),
                () -> first + " + " + second + " should equal " + expectedResult);
    }

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
            "3.0, 2.0, 1.0",
            "-2.0, 1.0, -3.0"
    })
    @DisplayName("Тест на вычитание")
    void testMinus(double first, double second, double expectedResult) {
        assertEquals(expectedResult, Calculator.minus(first, second));
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
            "4.0, 2.0, 2.0",
            "4.0, 0.0, 0.0"  // Здесь ожидается исключение
    })
    @DisplayName("Тест на деление")
    void testShare(double first, double second, double expectedResult) {
        if (second == 0.0) {
            assertThrows(IllegalArgumentException.class, () -> Calculator.share(first, second));
        } else {
            assertEquals(expectedResult, Calculator.share(first, second));
        }
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
            "2.0, 3.0, 6.0",
            "-2.0, 3.0, -6.0"
    })
    @DisplayName("Тест на умножение")
    void testMultiply(double first, double second, double expectedResult) {
        assertEquals(expectedResult, Calculator.multiply(first, second));
    }

    // Остальные тесты можно оставить как есть или аналогично преобразовать в параметризованные
    @Test
    @DisplayName("1 = 01")
    public void testToBinary() {
        assertEquals(101, Calculator.to_binary(5.0));
        assertEquals(0, Calculator.to_binary(0.0));
    }

    @Test
    @DisplayName("01 = 1")
    public void testFromBinary() {
        assertEquals(5, Calculator.from_binary(101));
        assertEquals(0, Calculator.from_binary(0));
    }

    @ParameterizedTest(name = "sqrt({0}) = {1}")
    @CsvSource({
            "4.0, 2.0",
            "-1.0, 0.0"  // Здесь ожидается исключение
    })
    @DisplayName("Тест на квадратный корень")
    void testSqrt(double value, double expectedResult) {
        if (value < 0) {
            assertThrows(IllegalArgumentException.class, () -> Calculator.sqrt(value));
        } else {
            assertEquals(expectedResult, Calculator.sqrt(value));
        }
    }

    @ParameterizedTest(name = "abs({0}) = {1}")
    @CsvSource({
            "-5.0, 5.0",
            "5.0, 5.0"
    })
    @DisplayName("Тест на модуль")
    void testAbs(double value, double expectedResult) {
        assertEquals(expectedResult, Calculator.abs(value));
    }

    @ParameterizedTest(name = "pow({0}, {1}) = {2}")
    @CsvSource({
            "2.0, 3, 8.0",
            "5.0, 0, 1.0"
    })
    @DisplayName("Тест на возведение в степень")
    void testPow(double base, int exponent, double expectedResult) {
        assertEquals(expectedResult, Calculator.pow(base, exponent));
    }

    @ParameterizedTest(name = "{0}% = {1}")
    @CsvSource({
            "50.0, 0.5",
            "100.0, 1.0"
    })
    @DisplayName("Тест на проценты")
    void testPercentages(double percent, double expectedResult) {
        assertEquals(expectedResult, Calculator.percentages(percent));
    }
}