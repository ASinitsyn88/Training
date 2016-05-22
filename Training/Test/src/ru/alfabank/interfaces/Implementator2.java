package ru.alfabank.interfaces;

public class Implementator2 extends Implementator1 implements A, B {

    @Override
    public void doSomething() {

        System.out.println("Method doSomething() from Implementator2");
    }
}
