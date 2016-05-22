package ru.alfabank.interfaces;

public interface B {

    default void doSomething() {

        System.out.println("This is default implementation from B");
    }
}
