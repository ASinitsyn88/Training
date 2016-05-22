package ru.alfabank.enums;

public class Dog {

    // Порода
    private String breed;

    public enum Breed {

        // Овчарка
        Shepherd(11),
        // Бульдог
        Bulldog(),
        // Колли
        Collie(22),
        // Лабрадор
        Labrador();

        private int price = 0;

        Breed(int price) {

            this.price = price;
        }

        Breed() {

            this.price = 0;
        }

        public int getPrice() {

            return price;
        }
    }

    // Конструктор
    public Dog(Breed breed) {

        this.breed = breed.name();
    }

    /**
     * Получить породу
     * @return String
     */
    public String getBreed() {

        return breed;
    }
}
