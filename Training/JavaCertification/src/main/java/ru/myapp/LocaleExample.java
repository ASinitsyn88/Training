package ru.myapp;

import java.text.NumberFormat;
import java.util.Locale;

public class LocaleExample {

	static Locale russiaLocale = new Locale("ru", "RU");
	static Locale germanyLocale = new Locale("de", "DE");

	public static void main(String[] args) {
		double amt = 123456.78;
		NumberFormat currGermanyFmt = NumberFormat.getCurrencyInstance(germanyLocale);
		System.out.println(currGermanyFmt.format(amt));

		NumberFormat currRussiaFmt = NumberFormat.getCurrencyInstance(russiaLocale);
		System.out.println(currRussiaFmt.format(amt));
	}
}
