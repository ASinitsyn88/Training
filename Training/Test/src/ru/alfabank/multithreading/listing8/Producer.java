package ru.alfabank.multithreading.listing8;

/** Поток-поставщик элементов в очередь */
class Producer implements Runnable {

    // Объект очереди
    private Q q;

    // Конструктор
    Producer(Q q) {

        this.q = q;
        new Thread(this, "Поставщик").start();
    }

    // Логика потока
    public void run() {

        int i = 0;

        while(true) {
            q.put(i++);
        }
    }
}
