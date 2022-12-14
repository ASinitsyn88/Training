package ru.myapp.concurrency;

import java.util.concurrent.Semaphore;

// Демонстрируем, что не более 2ух человек одновременно могут находиться за столиками
public class SemaphoreExample {
    public static void main(String[] args) {
        // Доступные столики в ресторане (2 шт)
        Semaphore table = new Semaphore(2);

        // Посетители (7 чел)
        new Person(table).start();
        new Person(table).start();
        new Person(table).start();
        new Person(table).start();
        new Person(table).start();
        new Person(table).start();
        new Person(table).start();
    }
}

class Person extends Thread {
    private final Semaphore table;

    public Person(Semaphore table) {
        this.table = table;
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " waiting for a table");
        try {
            table.acquire();
            System.out.println(this.getName() + " eats at the table");
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(this.getName() + " releases the table");
            table.release();
        }
    }
}