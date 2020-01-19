package ru.myapp;

public class PolymorphismExample1 {

	interface Office {
		static String getAddress() {
			return "101 Smart Str";
		}
	}

	static class HomeOffice implements Office {

	}

	public static void main(String[] args) {
		Office off = new HomeOffice();
		// System.out.println(off.getAddress());
		// Вызов статического метода возможен только через имя класса
		System.out.println(Office.getAddress());
	}
}
