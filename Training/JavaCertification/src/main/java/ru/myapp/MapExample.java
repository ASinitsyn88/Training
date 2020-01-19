package ru.myapp;

import ru.myapp.entity.Dog;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * map - преобразует 1 элемент в 1 элемент другого типа
 */
public class MapExample {

	public static void main(String[] args) {
		List<String> nameList = new ArrayList<>();
		nameList.add("Bobik");
		nameList.add("Barbos");
		nameList.add("Rex");
		nameList.add("Sharik");
		nameList.add("John");

		List<Dog> resultList = nameList.stream()
				// Преобразовать каждый Stream<String> в Stream<Dog>
				.map(name -> new Dog(name))
				// Каждый Dog добавить в список
				.collect(Collectors.toList());

		resultList.stream().forEach(dog -> System.out.println(dog));
	}
}
