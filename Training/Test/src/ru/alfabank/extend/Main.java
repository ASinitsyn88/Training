package ru.alfabank.extend;

/**
 * Created by Alex on 31.01.2017.
 */
public class Main {

    public static void main(String[] args) {

        try {
            int a = 5 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Исключение обработано");
        }
        System.out.println("После блока catch");
    }
}
