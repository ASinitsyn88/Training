package ru.alfabank.generics.listing1;

/** Пример создания обобщённого класса */
public class Gen<T> {

    private T ob;

    // Конструктор
    Gen(T o) {

        ob = o;
    }

    /**
     * Вернуть объект
     * @return T
     */
    public T getob() {

        return ob;
    }

    /**
     * Показать тип объекта
     */
    public void showType() {

        System.out.println("Тип T это: " + ob.getClass().getName());
    }
}
