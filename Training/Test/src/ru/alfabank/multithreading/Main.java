package ru.alfabank.multithreading;

public class Main {

    public static void main(String[] args) {

        // Запуск родительского побочного потока
        ThreadR threadR = new ThreadR("Родительский побочный поток", 7);
        threadR.execute();
        try {
            threadR.getThread().join();
        } catch (InterruptedException e) {
            System.out.println("Побочный поток прерван.");
        }

        // Запуск основного потока
        Thread mainThread = Thread.currentThread();
        for (int i = 3; i > 0; i--) {
            System.out.println("Главный поток: " + i);
            try {
                mainThread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Главный поток прерван.");
            }
        }
        System.out.println(mainThread.getName() + " завершён.");
    }
}
