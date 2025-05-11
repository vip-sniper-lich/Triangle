package org.example;

public class Utilities
{
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    // Метод для вычисления факториала
    public static long factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Число не должно быть отрицательным");
        }
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    // Метод для проверки, является ли число простым
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Метод для конвертации градусов в радианы
    public static double degreesToRadians(double degrees) {
        return degrees * (Math.PI / 180);
    }

    // Метод для округления числа до N знаков после запятой
    public static double roundToNDecimalPlaces(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException("Количество знаков после запятой не может быть отрицательным");
        }
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}