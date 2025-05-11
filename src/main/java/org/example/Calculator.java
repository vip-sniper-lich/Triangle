package org.example;

public class Calculator
{
    public static double plus(double number1, double number2)
    {return number1 + number2;}

    public static double minus(double number1, double number2)
    {return number1 - number2;}

    public static double share(double number1, double number2) {
        if (number2 != 0) return number1 / number2;
        else throw new IllegalArgumentException("Делитель не должен быть равен 0!!!");
    }

    public static double multiply (double number1, double number2)
    {return number1 * number2;}

    public static double to_binary (double number1)
    {return Integer.parseInt(Integer.toBinaryString((int) number1));}

    public static int from_binary (int number1)
    {
        int ans = 0, rem = 0;
        int i = 0;
        while (number1 != 0) {

            // remainder when num is
            // divided by 10
            rem = number1 % 10;

            // quotient
            number1 /= 10;

            // Calculating decimal number
            ans += rem * Math.pow(2, i);
            i++;
        }
        return ans;
    }

    public static double sqrt (double number1)
    {
        if (number1 > 0)return Math.sqrt(number1);
        else throw new IllegalArgumentException("отрицательное число не должно быть в корне");
    }

    public static double abs (double number1)
    {return Math.abs(number1);}

    public static double pow (double number1, int degree)
    {return Math.pow(number1, degree);}

    public static double percentages (double number1)
    {return number1/100;}
}