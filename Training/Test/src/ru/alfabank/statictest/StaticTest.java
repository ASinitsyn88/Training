package ru.alfabank.statictest;

public class StaticTest {

    static int a = 0;
    private int b = 4;

    /** Статический блок инициализации */
    static {

        a = 5;
    }

    /** Получить статическую переменную a */
    public static int getA() {

        return a;
    }

    /** Установить статическую переменную a */
    public static void setA(int a) {

        StaticTest.a = a;
    }

    /** Получить переменную b */
    public int getB() {

        return b;
    }

    /** Установить переменную b */
    public void setB(int b) {

        this.b = b;
    }
}
