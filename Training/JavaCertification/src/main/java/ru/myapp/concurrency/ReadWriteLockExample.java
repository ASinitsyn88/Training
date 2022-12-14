package ru.myapp.concurrency;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private static final Map<String, String> map = new HashMap<>();
    private static final SafeDictionary safeDictionary = new SafeDictionary();

    public static void initMap() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
    }

    public static void main(String[] args) throws InterruptedException {
        initMap();
        Thread t1 = new Thread(new Reader(), "Reader 1");
        Thread t11 = new Thread(new Reader(), "Reader 2");
        Thread t12 = new Thread(new Reader(), "Reader 3");
        Thread t13 = new Thread(new Reader(), "Reader 4");
        Thread t14 = new Thread(new Reader(), "Reader 5");
        Thread t15 = new Thread(new Reader(), "Reader 6");
        Thread t2 = new Thread(new WriterA());
        Thread t3 = new Thread(new WriterB());
        t1.start();
        t11.start();
        t12.start();
        t13.start();
        t14.start();
        t15.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        t11.join();
        t12.join();
        t13.join();
        t14.join();
        t15.join();
    }

    private static class Reader implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " is reading value");
                System.out.println(safeDictionary.getAll());
                try { Thread.sleep((long) (Math.random() * 999) + 1000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    private static class WriterA implements Runnable {
        Random random = new Random();
        Object[] values = map.keySet().toArray(new String[0]);

        @Override
        public void run() {
            while (true) {
                if (values.length > 0) {
                    String randomKey = (String) values[random.nextInt(values.length)];
                    safeDictionary.put(randomKey, map.get(randomKey));
                    System.out.println("Added value: " + randomKey + " " + map.get(randomKey));
                }
                try { Thread.sleep((long) (Math.random() * 999) + 1000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    private static class WriterB implements Runnable {
        @Override
        public void run() {
            while (true) {
                safeDictionary.clear();
                System.out.println("Cleared all values");
                try { Thread.sleep((long) (Math.random() * 999) + 1000); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    // Допускает одновременное чтение из нескольких потоков, но запись только из одного
    private static class SafeDictionary {
        private final Map<String, String> map = new TreeMap<>();
        private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private final Lock readLock = readWriteLock.readLock();
        private final Lock writeLock = readWriteLock.writeLock();

        // Читать могут все потоки пока не занят замок на запись
        public String get(String key) {
            readLock.lock();
            try {
                return map.get(key);
            } finally {
                readLock.unlock();
            }
        }

        public Set<String> getAll() {
            readLock.lock();
            try {
                return map.keySet();
            } finally {
                readLock.unlock();
            }
        }

        // Нельзя занять замок на запись пока замок на чтение закрыт
        public String put(String key, String value) {
            writeLock.lock();
            try {
                return map.put(key, value);
            } finally {
                writeLock.unlock();
            }
        }

        // Замок на запись нужен потому что удаление записей - это тоже запись
        public void clear() {
            writeLock.lock();
            try {
                map.clear();
            } finally {
                writeLock.unlock();
            }
        }
    }
}