package ru.alfabank.multithreading;

/** Реализация потока через интерфейс Runnable */
public class ThreadR implements Runnable {

    /** Объект потока */
    private Thread thread;
    /** Кол-во секунд обратного отчёта */
    private int countdown;
    /** Используется ли счётчик */
    private boolean isCountdownActive = false;

    // Конструктор
    public ThreadR() {

        thread = new Thread(this, "Побочный поток");
        System.out.println(thread.getName() + " создан");
    }

    // Конструктор
    public ThreadR(String threadName) {

        if (threadName == null || threadName.isEmpty()) {
            threadName = "Побочный поток";
        }
        thread = new Thread(this, threadName);
        System.out.println(thread.getName() + " создан");
    }

    // Конструктор
    public ThreadR(String threadName, int countdown) {

        this.countdown = countdown;
        isCountdownActive = true;
        if (threadName == null || threadName.isEmpty()) {
            threadName = "Побочный поток";
        }
        thread = new Thread(this, threadName);
        System.out.println(thread.getName() + " создан");
    }

    /**
     * Запуск потока
     */
    public void execute() {

        thread.start();
    }

    /**
     * Реализация логики потока
     */
    @Override
    public void run() {

        // Запуск дочернего побочного потока
        ThreadT threadT = new ThreadT("Дочерний побочный поток", 10);
        threadT.execute();
        try {
            threadT.join();
        } catch (InterruptedException e) {
            System.out.println("Дочерний побочный поток");
        }

        try {
            if (isCountdownActive == true) {
                for (int i = countdown; i > 0; i--) {
                    System.out.println(thread.getName() + ": " + i);
                    Thread.sleep(1000);
                }
            } else {
                for (int i = 10; i > 0; i--) {
                    System.out.println(thread.getName() + ": " + i);
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(thread.getName() + " прерван.");
        }
        System.out.println(thread.getName() + " завершён.");
    }

    /**
     * Получить объект потока
     * @return Thread
     */
    public Thread getThread() {

        return thread;
    }
}
