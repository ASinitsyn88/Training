package ru.alfabank.lambda;

import java.util.function.Function;

/**
 * Created by Alex on 01.10.2016.
 */
public class FunctionTest {

    public static void main(String[] args) {

        Function<Integer, String> functionLambda = (x) -> String.valueOf(x);
        System.out.println(functionLambda.apply(5));
    }
}
