package ru.alfabank.lambda.listing11;

public class MethodRefDemo {

    // Первым параметром передаётся объект функционального интерфейса,
    // который затем можно реализовать в виде ссылки на метод,
    // главное,чтобы ссылка на метод возвращала совместимый с интерфейсом тип
    static String stringOp(StringFunc sf, String s) {

        return sf.func(s);
    }

    public static void main(String args[]) {

        MyStringOps myStringOps = new MyStringOps();
        String inStr = "Lambdas add power to Java";
        String outStr = "";

        outStr = stringOp(myStringOps :: strReverse, inStr);

        System.out.println("Original string: " + inStr);
        System.out.println("String reversed: " + outStr);
    }
}
