package org.example;

public class Triangle
{
    //поля
    private double a;
    private double b;
    private double c;
    //поля

    //конструктор
    public Triangle ()
    {
        a = 1;
        b = 1;
        c = 1;
    }
    public Triangle (double a, double b, double c)
    {
        if(!isTriangle(a, b, c))
        {
            throw new IllegalArgumentException("Треугольник с такими сторонами не может " +
                    "быть построен.");
        }
        else
        {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    private static boolean isTriangle (double a, double b, double c)
    {
        return (a > 0) && (b > 0) && (c > 0) && (a + b > c) && (a + c > b) && (b + c > a);
    }
    //конструктор

    //set
    public void setA (double a)
    {
        if(!isTriangle(a, b, c))
        {
            throw new IllegalArgumentException("Треугольник с такими сторонами не может " +
                    "быть построен.");
        }
        else
        {
            this.a = a;
        }
    }
    public void setB (double b)
    {
        if(!isTriangle(a, b, c))
        {
            throw new IllegalArgumentException("Треугольник с такими сторонами не может " +
                    "быть построен.");
        }
        else
        {
            this.b = b;
        }
    }
    public void setC (double c)
    {
        if(!isTriangle(a, b, c))
        {
            throw new IllegalArgumentException("Треугольник с такими сторонами не может " +
                    "быть построен.");
        }
        else
        {
            this.c = c;
        }
    }
    //set

    //get
    public double getA ()
    {
        return a;
    }
    public double getB ()
    {
        return b;
    }
    public double getC ()
    {
        return c;
    }
    //get

    //методы
    //площадь
    public double square ()
    {
        double s = perimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
    //периметр
    public double perimeter ()
    {
        return a + b + c;
    }

    // Определение вида треугольника по сторонам
    public String getTypeBySides()
    {
        if (a == b && b == c)
        {
            return "равносторонний";
        } else if (a == b || b == c || a == c)
        {
            return "равнобедренный";
        } else
        {
            return "разносторонний";
        }
    }

    // Определение вида треугольника по углам
    public String getTypeByAngles()
    {
        double aSquared = a * a;
        double bSquared = b * b;
        double cSquared = c * c;

        if (aSquared + bSquared > cSquared && aSquared + cSquared > bSquared && bSquared + cSquared > aSquared)
        {
            return "остроугольный";
        } else if (aSquared + bSquared == cSquared || aSquared + cSquared == bSquared || bSquared + cSquared == aSquared)
        {
            return "прямоугольный";
        } else {
            return "тупоугольный";
        }
    }

    // Вывод информации о треугольнике
    public void displayInfo()
    {
        System.out.println("Тип по сторонам: " + getTypeBySides());
        System.out.println("Тип по углам: " + getTypeByAngles());
    }

    // Сравнение двух треугольников на равенство
    public boolean equals(Triangle other)
    {
        System.out.println("Происходит сортировка сторон треугольникок по возрастанию.");
        sort();
        other.sort();
        return (a == other.a && b == other.b && c == other.c);
    }

    public boolean equalsArea(Triangle other)
    {
        return Math.abs(this.square() - other.square()) < 1e-9; // Учитываем возможные погрешности
    }

    public double calculateHeight(double side)
    {
        if (side <= 0 || (side != a && side != b && side != c))
        {
            throw new IllegalArgumentException("Основание должно быть положительным и равно одной из сторон треугольника.");
        }
        return (2 * square ()) / side; // Высота = (2 * Площадь) / Основание
    }

    public void sort()
    {
        if (a > b)
        {
            double temp = a; a = b; b = temp;
        }
        if (b > c)
        {
            double temp = b; b = c; c = temp;
        }
        if (a > b)
        {
            double temp = a; a = b; b = temp;
        }

    }
    //методы
}