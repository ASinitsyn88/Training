package ru.myapp.bean;

/**
 * Структура данных студент
 */
public class Student {

    // Имя
    private String name;

    /**
     * Получить имя студента
     * @return
     */
    public String getName() {

        return name;
    }

    /**
     * Установить имя студента
     * @return
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Показать информацию о студенте
     */
    public void displayInfo() {

        System.out.println("Hello: " + name);
    }
}
