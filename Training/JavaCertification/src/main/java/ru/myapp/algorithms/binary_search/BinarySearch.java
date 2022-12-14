package ru.myapp.algorithms.binary_search;

import java.util.List;

public class BinarySearch {

    /**
     * Бинарный поиск
     * Принцип работы:
     * 1. Массив разбивается на 2 части
     * 2. Определяем в какой части находится искомый элемент
     * 3. Разбиваем часть с искомым элементом ещё на 2 части
     * 4. Определяем в какой части находится искомый элемент
     * 5. Повторяем действия до тех пор пока не останется только искомый элемент
     * Time complexity: O(log n)
     * @param inputList - отсортированный массив целых чисел
     * @param targetItem - искомый элемент массива
     * @return индекс искомого элемента массива
     */
    public int binarySearch(List<Integer> inputList, int targetItem) {
        // Объявляем нижнюю и верхнюю границу той части списка, в которой выполняется поиск
        int lowIndex = 0;
        int highIndex = inputList.size() - 1;

        // Пока эта часть списка не сократится до одного элемента - проверяем средний элемент
        while (lowIndex <= highIndex) {
            int midIndex = (lowIndex + highIndex) / 2;
            int guess = inputList.get(midIndex);
            // Значение найдено
            if (guess == targetItem) {
                return midIndex;
            }
            if (guess > targetItem) {
                // Много
                highIndex = midIndex - 1;
            } else {
                // Мало
                lowIndex = midIndex + 1;
            }
        }
        // Значение не существует
        return -1;
    }
}