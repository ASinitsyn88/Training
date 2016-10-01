package ru.alfabank.lambda;

import java.util.function.BinaryOperator;

/**
 * BinaryOperator - принимает два аргумента одного типа
 * и возвращает значение того же типа
 */
public class BinaryOperatorTest {

    public static void main(String[] args) {

        BinaryOperator<Long> addLongsLambda = (x, y) -> x + y;
        System.out.println(addLongsLambda.apply(5L, 5L));
    }
}
