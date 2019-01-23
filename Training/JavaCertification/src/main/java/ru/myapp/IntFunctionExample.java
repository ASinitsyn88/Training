package ru.myapp;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntFunctionExample {

	public static void main(String[] args) {

		// Преобразует список чисел в список, где каждое число умножено на 2
		IntFunction<Integer> intFunction = i -> i * 2;
		List<Integer> multipliedIntList = Stream.of(1, 2, 3, 4).map(intFunction::apply).collect(Collectors.toList());
		multipliedIntList.forEach(System.out::println);
	}
}
