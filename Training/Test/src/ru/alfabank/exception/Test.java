package ru.alfabank.exception;

public class Test {

    public static void main(String[] args) {

        int total = 0;
        ChainException ce = new ChainException();
        try {
            total = ce.div("6", null);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getCause());
        }

        System.out.println("Total: " + total);
    }
}
