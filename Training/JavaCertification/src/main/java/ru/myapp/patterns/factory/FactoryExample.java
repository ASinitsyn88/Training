package ru.myapp.patterns.factory;

public class FactoryExample {

    public static void main(String[] args) {
        Car toyota = CarFactory.create("Toyota");
        Car audi = CarFactory.create("Audi");
        toyota.drive();
        audi.drive();
    }

    public static class CarFactory {
        public static Car create(String typeOfCar) {
            switch (typeOfCar) {
                case "Toyota" : return new Toyota();
                case "Audi" : return new Audi();
                default: throw new IllegalArgumentException("Type " + typeOfCar + " doesn't exist");
            }
        }
    }

    public interface Car {
        void drive();
    }

    public static class Toyota implements Car {
        @Override public void drive() {
            System.out.println("drive Toyota");
        }
    }

    public static class Audi implements Car {
        @Override public void drive() {
            System.out.println("drive Audi");
        }
    }
}