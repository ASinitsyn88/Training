package ru.alfabank.multithreading.threads;

/**
 * Created by Alex on 02.02.2017.
 */
public class AlternateThreadT extends Thread {

    @Override
    public void run() {

        Thread thread = Thread.currentThread();
        thread.setName("поток 2");
        System.out.println("Запущен: " + Thread.currentThread().getName());
    }
}
