package ru.myapp.test2;

public class Element {
    private Element next;
    private final String value;

    public Element(String value) {
        this.value = value;
        this.next = null;
    }

    public Element getNext() {
        return this.next;
    }

    void setNext(Element next) {
        this.next = next;
    }

    public String getValue() {
        return value;
    }

    public static Element reverseRecursively(Element element) {
        // Базовый случай - разделяем список на первый узел и оставшиеся узлы
        if (element == null || element.next == null) {
            return element;
        }

        // Получаем последний элемент списка
        Element rest = reverseRecursively(element.next);
        // Разворачиваем список, делая элемент, который был предпоследним, следующим (next) для последнего
        element.next.next = element;

        // Обнуляем предпоследний элемент, так как мы уже сделали его следующим на предыдущем шаге
        element.next = null;
        // Возвращаем развёрнутый в обратном порядке список
        return rest;
    }

    public static Element reverseIteratively(Element element) {
        Element prev = null;
        Element current = element; // Текущий элемент вычисляем сразу, далее он будет последним
        Element next = null;
        // Перебираем элементы до тех пор пока не дойдём до конца списка
        while (current != null) {
            // Получаем оставшуюся часть списка
            next = current.next;
            // Обнуляем последующие элементы относительно текущего, так как текущий элемент далее должен стать последним
            current.next = prev;
            // Делаем текущий элемент последним
            prev = current;
            // Оставшуюся часть списка ставим после последнего элемента
            current = next;
        }
        element = prev;
        return element;
    }
}