package ru.myapp.concurrency;

import java.util.concurrent.locks.Lock;

public class ReentrantLockExample {
    public static void main(String[] args) {
        Lock lock = new java.util.concurrent.locks.ReentrantLock();
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                counter.inc(); // Counter является общим ресурсом
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                counter.dec(); // Counter является общим ресурсом
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        try {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Counter value " + counter.getCounter());
    }

    public static class Counter {
        private Integer counter = 0;

        public Counter() {

        }

        public void inc() {
            this.counter++;
        }

        public void dec() {
            this.counter--;
        }

        public Integer getCounter() {
            return this.counter;
        }
    }
}