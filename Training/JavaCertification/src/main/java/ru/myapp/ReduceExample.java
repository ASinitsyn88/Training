package ru.myapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * reduce - используется когда имеется коллекция значений, а нужно получить 1 значение в качестве результата
 */
public class ReduceExample {

	public static void main(String[] args) {
		// Пример редукции на основе массива
		Integer[] intArr = {1, 2, 3};
		// Создать Stream<Integer>
		int sum = Stream.of(intArr)
				// Присвоить аккумулятору начальное значение 0. Затем сложить аккумулятор с каждым элементом
				.reduce(0, (acc, element) -> acc + element);
		System.out.println(sum);

		// Пример редукции на основе списка
		List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3));
		// Создать Stream<Integer>
		int sum2 = Stream.of(intList.toArray(new Integer[0]))
				// Присвоить аккумулятору начальное значение 0. Затем сложить аккумулятор с каждым элементом
				.reduce(0, (acc, element) -> acc + element);
		System.out.println(sum2);
	}
}
