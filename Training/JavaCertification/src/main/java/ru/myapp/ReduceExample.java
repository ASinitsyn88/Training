package ru.myapp;

import java.util.stream.Stream;

/**
 * reduce - используется когда имеется коллекция значений, а нужно получить 1 значение в качестве результата
 */
public class ReduceExample {

	public static void main(String[] args) {
		// Создать Stream<Integer>
		int sum = Stream.of(1, 2, 3)
				// Присвоить аккумулятору начальное значение 0. Затем сложить аккумулятор с каждым элементом
				.reduce(0, (acc, element) -> acc + element);

		System.out.println(sum);
	}
}
