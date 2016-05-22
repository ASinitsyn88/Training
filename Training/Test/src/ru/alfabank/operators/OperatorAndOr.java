package ru.alfabank.operators;

public class OperatorAndOr {

    public static void main(String[] args) {

        operatorAndDiff();
        operatorOrDiff();
    }

    /** Сравнение операторов & и && */
    private static void operatorAndDiff() {

        String str = null;

        // Проверит условие 2 только в том случае,если условие 1 = true
        if (str != null && str.length() > 0) {
            System.out.println("Отработал оператор &&");
        }

        // Проверит условие 2 в любом случае
        if (str != null & str.length() > 0) {
            System.out.println("Отработал оператор &");
        }
    }

    /** Сравнение операторов | и || */
    private static void operatorOrDiff() {
        String str = null;

        // Проверит условие 2 только в том случае,если условие 1 = true
        if (str == null || str.length() == 0) {
            System.out.println("Отработал оператор ||");
        }

        // Проверит условие 2 в любом случае
        if (str == null | str.length() == 0) {
            System.out.println("Отработал оператор |");
        }
    }
}
