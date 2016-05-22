package ru.alfabank.interfaces;

public class InterfaceTest {

    public static void main(String[] args) {

        Implementator1 imp1 = new Implementator1();
        imp1.doSomething();

        Implementator2 imp2 = new Implementator2();
        imp2.doSomething();

        Implementator3 imp3 = new Implementator3();
        imp3.outerMethod();
        imp3.innerMethod();

        Outer.staticMethod();
    }
}
