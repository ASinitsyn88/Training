package ru.alfabank.operators;

public class Ternary {

    public static void main(String[] args) {

        int a = 1;
        String b;

        b = a == 1 ? print1() : print2();
        System.out.println("Отработал метод " + b + "()");
    }

    private static String print1() {

        return "print1";
    }

    private static String print2() {

        return "print2";
    }
}
