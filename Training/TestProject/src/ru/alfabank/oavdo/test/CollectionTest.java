package ru.alfabank.oavdo.test;

import java.util.*;

public class CollectionTest {

    public static void main(String[] args) {

        Set<String> hashSet = new HashSet<String>();
        hashSet.add("test1");
        hashSet.add("test2");
        hashSet.add("test2");

        Iterator<String> iterator = hashSet.iterator();

        if (iterator.hasNext()) {
            System.out.println("Условие 1");
            System.out.println(iterator.next());
            System.out.println("\n");
        }

        while (iterator.hasNext()) {
            System.out.println("Условие 2");
            System.out.println(iterator.next());
            System.out.println("\n");
        }
    }
}