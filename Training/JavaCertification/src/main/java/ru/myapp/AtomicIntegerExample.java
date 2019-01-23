package ru.myapp;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Тестовые примеры по работе с AtomicInteger (потокобезопасный Integer)
 */
public class AtomicIntegerExample {

	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger(15);

		// Сравнивает текущее значение и значение первого аргумента,
		// если равны - то выставляет значение второго аргумента
		atomicInteger.compareAndSet(15, 1);
		System.out.println("AtomicInteger: " + atomicInteger.get());
	}
}
