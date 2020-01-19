package ru.myapp.exercise;

import ru.myapp.entity.Artist;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Exercise2 {

	public static void main(String[] args) {
		Artist artist1 = new Artist("Corey Taylor", "USA");
		Artist artist2 = new Artist("Joey Jordison", "England");
		Artist artist3 = new Artist("Filipp Kirkorov", "Russia");
		List<Artist> artistList1 = new ArrayList<>();
		artistList1.add(artist1);
		artistList1.add(artist2);
		List<Artist> artistList2 = new ArrayList<>();
		artistList2.add(artist3);
		Artist group1 = new Artist("Slipknot", artistList1, "USA");
		Artist group2 = new Artist("Russian music King", artistList2, "Russia");
		List<Artist> musicalGroupList = new ArrayList<>();
		musicalGroupList.add(group1);
		musicalGroupList.add(group2);

		System.out.println(getTotalArtistCountFromMusicalGroups(musicalGroupList));
		System.out.println(getBiggestGroup(musicalGroupList));
		System.out.println(getAvg(Arrays.asList(1, 2, 3)));
	}

	/**
	 * Функция возвращает сумму всех артистов из списка групп
	 */
	public static int getTotalArtistCountFromMusicalGroups(List<Artist> artistList) {
		return artistList.stream()
				.map(artist -> artist.getMembers().count())
				.reduce(0L, (acc, element) -> acc + element)
				.intValue();
	}

	/**
	 * Найти группу с наибольшим количеством участников
	 */
	public static Optional<Artist> getBiggestGroup(List<Artist> musicalGroupList) {
		// Функция, которая считает кол-во участников в группе
		Function<Artist, Long> getCount = group -> group.getMembers().count();
		// comparing принимает функцию, преобразующую каждый элемент потока в число участников,
		// перед тем как вычислить максимальный результат
		return musicalGroupList.stream().max(Comparator.comparing(getCount));
	}

	/**
	 * Вычислить среднее арифметическое значение из списка чисел
	 */
	public static double getAvg(List<Integer> intList) {
		return intList.stream().collect(Collectors.averagingInt(number -> number));
	}
}
