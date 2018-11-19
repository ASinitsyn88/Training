package ru.myapp.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercise3 {

	public static void main(String[] args) {
		System.out.println(getUppercaseString(Arrays.asList("a", "b", "hello")));
		System.out.println(countWords(Arrays.asList("aaas", "bbb")));
	}

	public static List<String> getUppercaseString(List<String> strList) {
		return strList.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());
	}

	public static Map<String, Long> countWords(List<String> words) {
		return words.stream().collect(Collectors.groupingBy(name -> name, Collectors.counting()));
	}
}
