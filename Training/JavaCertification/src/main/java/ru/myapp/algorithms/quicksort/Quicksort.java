package ru.myapp.algorithms.quicksort;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class Quicksort {

    /**
     * Быстрая сортировка
     * Сортирует список по возрастанию
     * Принцип работы:
     * 1. Выбрать опорный элемент
     * 2. Разделить массив на 2 подмассива: элементы, меньше опорного, и элементы, больше опорного
     * 3. Рекурсивно разделять эти массивы на другие подмассивы до тех пор пока в каждом из них не останется по одному элементу (базовый случай)
     * Time complexity: O(n log n)
     * @param inputList - неотсортированный список целых чисел
     * @return отсортированный по возрастанию список целых чисел
     */
    public static List<Integer> quicksort(List<Integer> inputList) {
        // --- Базовый случай ---
        if (inputList == null || inputList.size() < 2) {
            // Пустой массив и массив с одним элементом не нуждаются в сортировке
            return inputList;
        }

        // --- Рекурсивный случай ---
        // Вычисляем опорный элемент (для упрощения это всегда первый элемент)
        Integer pivot = inputList.get(0);

        // Подмассив всех элементов, которые меньше чем опорный элемент
        List<Integer> less = inputList.stream().skip(1).filter(el -> el <= pivot).collect(toList());

        // Подмассив всех элементов, которые больше чем опорный элемент
        List<Integer> greater = inputList.stream().skip(1).filter(el -> el > pivot).collect(toList());

        return Stream.of(
                        // Рекурсивно выполняем быструю сортировку для меньшего подмассива (пока не останется базовый случай)
                        quicksort(less).stream(),
                        // Присоединяем опорный элемент
                        Stream.of(pivot),
                        // Рекурсивно выполняем быструю сортировку для большего подмассива (пока не останется базовый случай)
                        quicksort(greater).stream())
                .flatMap(Function.identity()).collect(toList());
    }
}