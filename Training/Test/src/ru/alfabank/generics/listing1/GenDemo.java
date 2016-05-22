package ru.alfabank.generics.listing1;

public class GenDemo {

    public static void main(String args[]) {

        Gen<Integer> iOb = new Gen<Integer>(88);
        iOb.showType();
        int v = iOb.getob();
        System.out.println("значение: " + v);
        System.out.println();

        Gen<String> strOb = new Gen<String>("Тестовая строка");
        strOb.showType();
        String str = strOb.getob();
        System.out.println("значение: " + str);
    }
}
