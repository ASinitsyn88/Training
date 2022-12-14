package ru.myapp.concurrency;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        // Указываем кол-во потоков, которое мы ожидаем у барьера (3)
        // Передаём объект, код которого должен сработать после слома барьера
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Run());
        new Sportsman(cyclicBarrier, "Thread1").start();
        new Sportsman(cyclicBarrier, "Thread2").start();
        new Sportsman(cyclicBarrier, "Thread3").start();
    }

    static class Run extends Thread {
        @Override
        public void run() {
            System.out.println("The race has begun");
        }
    }

    static class Sportsman extends Thread {
        CyclicBarrier cyclicBarrier;

        public Sportsman(CyclicBarrier cyclicBarrier, String threadName) {
            this.cyclicBarrier = cyclicBarrier;
            this.setName(threadName);
        }

        @Override
        public void run() {
            try {
                System.out.println("I am at the barrier and ready to overcome " + currentThread().getName());
                cyclicBarrier.await();
                System.out.println("After breaking the barrier " + currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}