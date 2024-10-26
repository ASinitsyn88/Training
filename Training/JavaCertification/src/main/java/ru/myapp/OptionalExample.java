package ru.myapp;

import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {
        // throws NPE
        //Optional.of(null);

        // returns Optional.empty
        System.out.println(Optional.ofNullable(null));

        // parameter is evaluated, even when having a non-empty Optional
        System.out.println(Optional.ofNullable(null).orElse("123"));

        // parameter is only executed when an Optional value isn’t present
        System.out.println(Optional.ofNullable(null).orElseGet(() -> "123"));
    }
}