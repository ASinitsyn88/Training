package ru.alfabank.multithreading.threads;

/**
 * Created by Alex on 02.02.2017.
 * Объект побочного потока
 */
public class AlternateThreadR implements Runnable {

    @Override
    public void run() {

        Thread thread = Thread.currentThread();
        thread.setName("поток 1");
        System.out.println("Запущен: " + Thread.currentThread().getName());
    }
}
