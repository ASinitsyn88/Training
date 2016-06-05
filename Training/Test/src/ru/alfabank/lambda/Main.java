package ru.alfabank.lambda;

public class Main {

    public static void main(String[] args) {

        Informational info = (str) -> {
            String s = str;
            System.out.println(s.toUpperCase());
            return s;
        };

        print(info);
    }

    public static void print(Informational info) {

        info.showInfo("Info");
    }
}
