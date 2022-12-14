package ru.myapp.algorithms.recursion;

import java.util.ArrayList;
import java.util.List;
import static java.util.Collections.emptyList;

public class RecursionExample {

    public int sum(List<Integer> inputList) {
        // Копируем список чтобы не модифицировать оригинальный
        return sumRecursively(new ArrayList<>(inputList == null ? emptyList() : inputList));
    }

    private int sumRecursively(List<Integer> inputList) {
        // --- Базовый случай ---
        if (inputList == null || inputList.isEmpty()) {
            return 0;
        }

        // --- Рекурсивный случай ---
        int head = inputList.get(0);
        inputList.remove(0);
        return head + sumRecursively(inputList);
    }

    public int count(List<Integer> inputList) {
        // Копируем список чтобы не модифицировать оригинальный
        return countRecursively(new ArrayList<>(inputList == null ? emptyList() : inputList));
    }

    private int countRecursively(List<Integer> inputList) {
        // Базовый случай
        if (inputList == null || inputList.isEmpty()) {
            return 0;
        }

        // Рекурсивный случай
        inputList.remove(0);
        return 1 + countRecursively(inputList);
    }
}