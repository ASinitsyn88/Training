package ru.myapp.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new MyRunnable());
        executorService.shutdown();
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println(1);
        }
    }
}