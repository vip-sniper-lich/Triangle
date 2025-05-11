package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    // Тесты конструктора
    @DisplayName("Тестирование создания валидных треугольников")
    @ParameterizedTest(name = "Валидный треугольник со сторонами {0}, {1}, {2}")
    @CsvSource({
            "3, 4, 5",
            "5, 5, 5",
            "5, 5, 8",
            "0.5, 0.6, 0.7"
    })
    void testConstructorValid(double a, double b, double c) {
        Triangle triangle = new Triangle(a, b, c);
        assertAll(
                () -> assertEquals(a, triangle.getA()),
                () -> assertEquals(b, triangle.getB()),
                () -> assertEquals(c, triangle.getC())
        );
    }

    @DisplayName("Тестирование создания невалидных треугольников")
    @ParameterizedTest(name = "Невалидный треугольник со сторонами {0}, {1}, {2}")
    @CsvSource({
            "1, 2, 3",
            "0, 4, 5",
            "-1, 2, 2",
            "10, 1, 1"
    })
    void testConstructorInvalid(double a, double b, double c) {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(a, b, c));
    }

    // Тесты сеттеров
    @DisplayName("Тестирование валидного изменения стороны A")
    @ParameterizedTest(name = "Изменение стороны A на {1} в треугольнике {0}, {2}, {3}")
    @CsvSource({
            "3, 4, 4, 5",
            "5, 6, 5, 8"
    })
    void testSetAValid(double initialA, double newA, double b, double c) {
        Triangle triangle = new Triangle(initialA, b, c);
        triangle.setA(newA);
        assertEquals(newA, triangle.getA());
    }

    @DisplayName("Тестирование невалидного изменения стороны A")
    @ParameterizedTest(name = "Попытка установить недопустимое значение стороны A: {1} в треугольнике {0}, {2}, {3}")
    @CsvSource({
            "3, 10, 4, 5",
            "5, -1, 5, 8"
    })
    void testSetAInvalid(double initialA, double newA, double b, double c) {
        Triangle triangle = new Triangle(initialA, b, c);
        assertThrows(IllegalArgumentException.class, () -> triangle.setA(newA));
    }

    // Тесты площади и периметра
    @DisplayName("Тестирование расчета периметра и площади")
    @ParameterizedTest(name = "Треугольник {0}, {1}, {2} имеет периметр {3} и площадь ~{4}")
    @CsvSource({
            "3, 4, 5, 12, 6",
            "5, 5, 5, 15, 10.825",
            "5, 5, 8, 18, 12"
    })
    void testPerimeterAndArea(double a, double b, double c, double perimeter, double expectedArea) {
        Triangle triangle = new Triangle(a, b, c);
        assertEquals(perimeter, triangle.perimeter());
        assertEquals(expectedArea, triangle.square(), 0.001);
    }

    // Тесты определения типа треугольника
    @DisplayName("Тестирование определения типа треугольника по сторонам")
    @ParameterizedTest(name = "Треугольник {0}, {1}, {2} является {3}")
    @CsvSource({
            "5, 5, 5, 'равносторонний'",
            "5, 5, 8, 'равнобедренный'",
            "3, 4, 5, 'разносторонний'"
    })
    void testGetTypeBySides(double a, double b, double c, String expectedType) {
        Triangle triangle = new Triangle(a, b, c);
        assertEquals(expectedType, triangle.getTypeBySides());
    }

    @DisplayName("Тестирование определения типа треугольника по углам")
    @ParameterizedTest(name = "Треугольник {0}, {1}, {2} является {3} по углам")
    @CsvSource({
            "3, 4, 5, 'прямоугольный'",
            "5, 5, 5, 'остроугольный'",
            "5, 5, 8, 'тупоугольный'"
    })
    void testGetTypeByAngles(double a, double b, double c, String expectedType) {
        Triangle triangle = new Triangle(a, b, c);
        assertEquals(expectedType, triangle.getTypeByAngles());
    }

    // Тесты сравнения треугольников
    @DisplayName("Тестирование сравнения треугольников")
    @ParameterizedTest(name = "Треугольники {0},{1},{2} и {3},{4},{5} равны: {6}")
    @CsvSource({
            "3, 4, 5, 3, 4, 5, true",
            "3, 4, 5, 4, 3, 5, true",
            "3, 4, 5, 6, 8, 10, false"
    })
    void testEquals(double a1, double b1, double c1, double a2, double b2, double c2, boolean expected) {
        Triangle t1 = new Triangle(a1, b1, c1);
        Triangle t2 = new Triangle(a2, b2, c2);
        assertEquals(expected, t1.equals(t2));
    }

    @DisplayName("Тестирование сравнения площадей треугольников")
    @ParameterizedTest(name = "Треугольники {0},{1},{2} и {3},{4},{5} имеют равную площадь: {6}")
    @CsvSource({
            "3, 4, 5, 6, 8, 10, true",
            "5, 5, 5, 10, 10, 10, true",
            "5, 5, 5, 6, 8, 10, false"
    })
    void testEqualsArea(double a1, double b1, double c1, double a2, double b2, double c2, boolean expected) {
        Triangle t1 = new Triangle(a1, b1, c1);
        Triangle t2 = new Triangle(a2, b2, c2);
        assertEquals(expected, t1.equalsArea(t2));
    }

    // Тесты высоты треугольника
    @DisplayName("Тестирование расчета высоты треугольника")
    @ParameterizedTest(name = "Высота к стороне {3} в треугольнике {0},{1},{2} равна ~{4}")
    @CsvSource({
            "3, 4, 5, 3, 4",
            "5, 5, 5, 5, 4.330"
    })
    void testCalculateHeight(double a, double b, double c, double side, double expectedHeight) {
        Triangle triangle = new Triangle(a, b, c);
        assertEquals(expectedHeight, triangle.calculateHeight(side), 0.001);
    }

    @DisplayName("Тестирование невалидного расчета высоты")
    @ParameterizedTest(name = "Некорректный расчет высоты для стороны {3} в треугольнике {0},{1},{2}")
    @CsvSource({
            "3, 4, 5, 10",
            "5, 5, 5, -1"
    })
    void testCalculateHeightInvalid(double a, double b, double c, double side) {
        Triangle triangle = new Triangle(a, b, c);
        assertThrows(IllegalArgumentException.class, () -> triangle.calculateHeight(side));
    }

    // Тест сортировки сторон
    @DisplayName("Тестирование сортировки сторон треугольника")
    @ParameterizedTest(name = "Сортировка сторон треугольника {0},{1},{2} -> {3},{4},{5}")
    @CsvSource({
            "3, 4, 5, 3, 4, 5",
            "5, 3, 4, 3, 4, 5",
            "7, 5, 6, 5, 6, 7"
    })
    void testSort(double a, double b, double c, double sortedA, double sortedB, double sortedC) {
        Triangle triangle = new Triangle(a, b, c);
        triangle.sort();
        assertAll(
                () -> assertEquals(sortedA, triangle.getA()),
                () -> assertEquals(sortedB, triangle.getB()),
                () -> assertEquals(sortedC, triangle.getC())
        );
    }

    // Тест конструктора по умолчанию
    @DisplayName("Тестирование конструктора по умолчанию")
    @Test
    void testDefaultConstructor() {
        Triangle triangle = new Triangle();
        assertAll(
                () -> assertEquals(1, triangle.getA()),
                () -> assertEquals(1, triangle.getB()),
                () -> assertEquals(1, triangle.getC())
        );
    }
}