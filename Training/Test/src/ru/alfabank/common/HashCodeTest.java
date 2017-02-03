package ru.alfabank.common;

/**
 * Created by Alex on 02.02.2017.
 */
public class HashCodeTest {

    public static void main(String[] args) {

        Dog dog1 = new Dog(4, true);
        Dog dog2 = new Dog(4, true);
        System.out.println("dog1 equals dog2: " + dog1.equals(dog2));
        System.out.println("dog1 hashcode: " + dog1.hashCode());
        System.out.println("dog2 hashcode: " + dog2.hashCode());
    }
}
