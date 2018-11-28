package ru.myapp;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Класс вычисляет сумму всех чисел в переданном массиве при помощи механизма Fork/Join.
 * Использование данного механизма позволяет ускорить параллельную обработку данных, так как
 * спящие потоки не простаивают, а берутся за выполнение доступных задач.
 *
 * Для выполнения задачи необходимо наследоваться от класса RecursiveAction или RecursiveTask.
 * RecursiveAction - наследоваться, когда нужно выполнить некоторое действие, без возврата значения.
 * RecursiveTask - наследоваться, когда нужно выполнить некоторое действие и вернуть значение.
 */
public class ComplicatedTask extends RecursiveTask<Integer> {

	// Массив чисел, сумму которых нужно вычислить
	private int[] ia;
	// Начальный индекс массива
	private int from;
	// Конечный индекс массива
	private int to;
	// Пороговое значение
	private static final int THRESHOLD = 3;

	public ComplicatedTask(int[] ia, int from, int to) {
		this.ia = ia;
		this.from = from;
		this.to = to;
	}

	private int transform(int t){
		//this can be a CPU intensive operation that
		//transforms t and returns the value
		//For now, just return t
		return t;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		// Если начальный индекс + пороговое значение больше конечного индекса - то суммируем элементы списка
		if (from + THRESHOLD > to) {
			for(int i = from; i <= to; i++) {
				sum = sum + transform(ia[i]);
			}
		// Если начальный индекс + пороговое значение меньше конечного индекса - то разбиваем обработку массива на 2 подзадачи
		} else {
			int mid = (from + to) / 2;
			ComplicatedTask newtask1 = new ComplicatedTask(ia, from, mid);
			ComplicatedTask newtask2 = new ComplicatedTask(ia, mid + 1, to);
			// Асинхронный запуск задачи №2
			newtask2.fork();
			// Задача №2 дожидается выполнения задачи №1(метод join), после чего результаты обоих задач суммируются
			sum = newtask1.compute() + newtask2.join();
		}
		return sum;
	}

	public static void main(String[] args) {
		int ia[] = new int[] {1, 2, 3, 4, 5, 6, 7};
		// Инициализируем пул потоков
		ForkJoinPool fjp = new ForkJoinPool();
		// Инициализируем объект задачи
		ComplicatedTask st = new ComplicatedTask(ia, 0, 6);
		// Запуск задачи
		System.out.println("sum = " + fjp.invoke(st));
	}
}
