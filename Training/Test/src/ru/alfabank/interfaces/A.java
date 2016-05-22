package ru.alfabank.interfaces;

public interface A {

    Implementator1 impl = new Implementator1();

    default void doSomething() {

        System.out.println("This is default implementation from A");
    }
}
