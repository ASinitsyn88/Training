package ru.myapp.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinExample {
    private static final int MAX = 16;

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new MyAction(MAX));
        // FINISH не будет выведен до тех пор пока обработка всех действий внутри MyAction не завершатся
        System.out.println("FINISH");
    }
}

// Класс определяет рекурсивно-выполняемые в отдельных потоках действия не требующие возврата результата
class MyAction extends RecursiveAction {
    private final int value;

    public MyAction(int value) {
        this.value = value;
    }

    @Override
    protected void compute() {
        if (value > 4) {
            // --- Базовый случай (условие, которое позволяет алгоритму остановить рекурсию) ---
            // Делим задачу на 2 подзадачи и отправляем их в "очередь", которая обрабатывается другими потоками
            MyAction m1 = new MyAction(value / 2);
            MyAction m2 = new MyAction(value / 2);
            invokeAll(m1, m2);
        } else {
            System.out.println("Before processing: " + "Thread № " + Thread.currentThread().getId() + " Value " + value);
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("After processing: " + "Thread № " + Thread.currentThread().getId() + " Value " + value);
        }
    }
}