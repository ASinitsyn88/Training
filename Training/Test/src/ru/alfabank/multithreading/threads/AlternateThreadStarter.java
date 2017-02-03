package ru.alfabank.multithreading.threads;

/**
 * Created by Alex on 02.02.2017.
 * Запуск побочных потоков
 */
public class AlternateThreadStarter {

    public static void main(String[] args) {

        AlternateThreadR alternateThreadR = new AlternateThreadR();
        new Thread(alternateThreadR).start();

        AlternateThreadT alternateThreadT = new AlternateThreadT();
        alternateThreadT.start();
    }
}
