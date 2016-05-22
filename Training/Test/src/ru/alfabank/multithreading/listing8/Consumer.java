package ru.alfabank.multithreading.listing8;

/** Поток-потребитель элементов очереди */
class Consumer implements Runnable {

    // Объект очереди
    private Q q;

    // Конструктор
    Consumer(Q q) {

        this.q = q;
        new Thread(this, "Потребитель").start();
    }

    // Логика потока
    public void run() {

        while(true) {
            q.get();
        }
    }
}