package ru.alfabank.interfaces;

public class Implementator3 implements Outer, Outer.Inner {

    @Override
    public void outerMethod() {

        System.out.println("outerMethod");
    }

    @Override
    public void innerMethod() {

        System.out.println("innerMethod");
    }
}
