package ru.alfabank.datatypes;

import java.math.BigDecimal;

/**
 * Created by Alex on 31.01.2017.
 */
public class DataTypesTest {

    public static void main(String[] args) {

        BigDecimal a = BigDecimal.valueOf(2.0).subtract(BigDecimal.valueOf(1.1));
        System.out.println(a);
    }
}
