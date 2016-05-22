package ru.alfabank.multithreading;

/** Реализация потока через наследование класса Thread */
public class ThreadT extends Thread {

    /** Кол-во секунд обратного отчёта */
    private int countdown;
    /** Используется ли счётчик */
    private boolean isCountdownActive = false;

    // Конструктор
    public ThreadT() {

        super("Побочный поток");
        System.out.println(this.getName() + " создан");
    }

    // Конструктор
    public ThreadT(String threadName) {

        super(threadName);
        System.out.println(this.getName() + " создан");
    }

    // Конструктор
    public ThreadT(String threadName, int countdown) {

        super(threadName);
        this.countdown = countdown;
        isCountdownActive = true;
        if (threadName == null || threadName.isEmpty()) {
            threadName = "Побочный поток";
        }
        System.out.println(this.getName() + " создан");
    }

    /**
     * Запуск потока
     */
    public void execute() {

        start();
    }

    /**
     * Реализация логики потока
     */
    @Override
    public void run() {

        try {
            if (isCountdownActive == true) {
                for (int i = countdown; i > 0; i--) {
                    System.out.println(this.getName() + ": " + i);
                    Thread.sleep(1000);
                }
            } else {
                for (int i = 10; i > 0; i--) {
                    System.out.println(this.getName() + ": " + i);
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this.getName() + " прерван.");
        }
        System.out.println(this.getName() + " завершён.");
    }
}
