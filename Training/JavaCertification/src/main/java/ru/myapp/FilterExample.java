package ru.myapp;

import java.util.ArrayList;
import java.util.List;

/**
 * filter - отфильтровывает записи, возвращает только записи, соответствующие условию
 */
public class FilterExample {

	public static void main(String[] args) {
		List<String> cityList = new ArrayList<>();
		cityList.add("Moscow");
		cityList.add("Moscow");
		cityList.add("Volgograd");
		cityList.add("Novosibirsk");
		cityList.add("London");

		System.out.println(cityList.stream()
				// Добавить условие по которому отбираются только элементы со значением Moscow
				.filter(city -> city.equals("Moscow"))
				// Посчитать кол-во элементов
				.count());
	}
}
