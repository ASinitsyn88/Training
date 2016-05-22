package ru.alfabank.enums;

public class Main {

    public static void main(String[] args) {
        
        Dog dog = new Dog(Dog.Breed.Bulldog);
        System.out.println(dog.getBreed());
        System.out.println(Dog.Breed.Collie.getPrice());
    }
}
