package ru.alfabank.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionFilterTest {

    public static void main(String[] args) {

        List<String> nameList = Arrays.asList("Маша", "Катя", "Петя", "Вася", "Вася");
        Long count = nameList
                .stream()
                .filter(s -> s.equalsIgnoreCase("Вася"))
                .count();
        System.out.println(count);

        List<Integer> digitLisst = Arrays.asList(1,2,3,4,5,6,7,8,9);
        int digit = digitLisst
                .stream()
                .filter(i -> i > 5)
                .findFirst()
                .get();
        System.out.println(digit);

        List<String> upperList1 = nameList
                .stream()
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(upperList1);
    }
}
