package ru.alfabank.statictest;

public class Test {

    public static void main(String[] args) {

        StaticTest staticTest1 = new StaticTest();
        StaticTest staticTest2 = new StaticTest();

        System.out.println("Изначальное значение статической переменной \"a\" и \"b\":");
        System.out.println("staticTest1 static variable \"a\": " + staticTest1.a);
        System.out.println("staticTest1 \"b\": " + staticTest1.getB());
        System.out.println("staticTest2 static variable \"a\": " + staticTest2.a);
        System.out.println("staticTest2 \"b\": " + staticTest2.getB() + "\n");

        staticTest1.a = 40;
        staticTest1.setB(12);
        System.out.println("Статическая переменная \"a\" и \"b\" после изменения в одном из объектов:");
        System.out.println("staticTest1 static variable \"a\": " + staticTest1.a);
        System.out.println("staticTest1 \"b\": " + staticTest1.getB());
        System.out.println("staticTest2 static variable \"a\": " + staticTest2.a);
        System.out.println("staticTest1 \"b\": " + staticTest2.getB() + "\n");
    }
}
