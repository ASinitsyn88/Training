package ru.alfabank.collections;

import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentModification {

    public static void main(String[] args) {

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        list.add("Первый");
        list.add("Второй");
        list.add("Третий");
        list.add("Четвёртый");
        list.add("Пятый");
        list.add("Шестой");

        for(String str : list) {
            if (str.equals("Шестой")) {
                list.remove(str);
            }
        }

        for(String str : list) {
            System.out.println(str);
        }
    }
}
