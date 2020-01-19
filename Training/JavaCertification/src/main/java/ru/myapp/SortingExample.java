package ru.myapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Person{
	private String name;
	private String dateOfBirth;
	public Person(String name, String dateOfBirth){
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", dateOfBirth='" + dateOfBirth + '\'' +
				'}';
	}
}

public class SortingExample {

	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("Paul", "01012000"));
		personList.add(new Person("Peter", "01011990"));
		personList.add(new Person("Patrick", "01012002"));

		Collections.sort(personList, new Comparator<Person>(){
			public int compare(Person o1, Person o2) {
				return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
			}
		});
		personList.forEach(System.out::println);
	}
}
