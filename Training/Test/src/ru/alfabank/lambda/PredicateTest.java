package ru.alfabank.lambda;

import java.util.function.Predicate;

/**
 * Predicate - функция,которая принимает 1 аргумент,
 * а возвращает boolean.
 */
public class PredicateTest {

    public static void main(String[] args) {

        Predicate<Integer> atLeast5Lambda = x -> x > 5;
        System.out.println("atLeast5Lambda.test(6): " + atLeast5Lambda.test(6));
    }
}
