package ru.myapp.algorithms.selection_search;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort {

    /**
     * Сортировка выбором
     * Сортирует список по возрастанию
     * Принцип работы:
     * 1. Имеем оригинальный (переданный на вход) и новый (пустой) список элементов
     * 2. Проходимся по оргинальному списку и находим самый маленький элемент
     * 3. Добавляем найденный элемент в новый список, а из оригинального его удаляем
     * 4. Повторяем п.2 и 3 до тех пор пока в оригинальном списке не закончатся элементы
     * Time complexity: O(n * n) - потому как мы каждый раз заново линейно перебираем список
     * @param inputList - неотсортированный список целых чисел
     * @return отсортированный по возрастанию список целых чисел
     */
    public static List<Integer> selectionSort(List<Integer> inputList) {
        int size = inputList.size();

        List<Integer> newList = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            int smallest = findSmallest(inputList);
            newList.add(inputList.get(smallest));

            inputList.remove(smallest);
        }

        return newList;
    }

    private static int findSmallest(List<Integer> inputList) {
        int smallest = inputList.get(0);
        int smallestIndex = 0;
        for (int i = 0; i < inputList.size(); i++) {
            int potentiallySmallest = inputList.get(i);
            if (potentiallySmallest < smallest) {
                smallest = potentiallySmallest;
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }
}