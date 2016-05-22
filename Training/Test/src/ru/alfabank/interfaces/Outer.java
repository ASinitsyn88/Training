package ru.alfabank.interfaces;

public interface Outer {

    public static void staticMethod() {

        System.out.println("staticMethod");
    }

    public void outerMethod();

    // Вложенный интерфейс
    public interface Inner {

        public void innerMethod();
    }
}
