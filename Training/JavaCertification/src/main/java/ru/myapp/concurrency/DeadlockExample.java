package ru.myapp.concurrency;

public class DeadlockExample {
    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (lockA) {
                threadSleep(100); // Ждём 100 миллисекунд, чтобы второй поток успел захватить lockB
                synchronized (lockB) {

                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (lockB) {
                synchronized (lockA) {

                }
            }
            System.out.println(Thread.currentThread().getName() + " end");
        }, "Thread2");

        thread1.start();
        thread2.start();
    }

    private static void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}