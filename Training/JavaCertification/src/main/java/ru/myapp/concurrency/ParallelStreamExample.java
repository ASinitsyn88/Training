package ru.myapp.concurrency;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;

public class ParallelStreamExample {
    private static final int MAX = 16;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list = IntStream.range(1, MAX + 1).boxed().collect(toList());

        // Количество потоков определяется автоматически ForkJoinPool'ом работающим под капотом
        list.parallelStream().forEach(i -> {
            System.out.println("Before processing: " + "Thread № " + Thread.currentThread().getId() + " Value " + i);
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("After processing: " + "Thread № " + Thread.currentThread().getId() + " Value " + i);
        });
        System.out.println("FINISH 1");

        System.out.println("----------------------------------------------------");

        // Количество потоков задаётся через ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool(MAX);
        pool.submit(() -> {
            list.parallelStream().forEach(i -> {
                System.out.println("Before processing: " + "Thread № " + Thread.currentThread().getId() + " Value " + i);
                try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("After processing: " + "Thread № " + Thread.currentThread().getId() + " Value " + i);
            });
        }).get();
        System.out.println("FINISH 2");
    }
}