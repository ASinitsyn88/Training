package ru.myapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * flatMap - преобразует 1 элемент в несколько элементов другого типа
 */
public class FlatMapExample {

	public static void main(String[] args) {
		// Задача: объединить 3 списка в 1 общий список
		List<Integer> numberList1 = Arrays.asList(1, 2, 3);
		List<Integer> numberList2 = Arrays.asList(4, 5, 6);
		List<Integer> numberList3 = Arrays.asList(7, 8, 9);

		// Создать из списков Stream<List<Integer>>
		List<Integer> resultList = Stream.of(numberList1, numberList2, numberList3)
				// Преобразовать Stream<List<Integer>> во множество элементов Stream<Integer>
				.flatMap(list -> list.stream())
				// Каждый Integer добавить в список
				.collect(Collectors.toList());

		resultList.stream().forEach(el -> System.out.println(el));
	}
}
