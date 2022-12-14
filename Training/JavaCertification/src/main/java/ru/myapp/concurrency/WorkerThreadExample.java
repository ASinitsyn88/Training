package ru.myapp.concurrency;

import java.util.ArrayList;
import java.util.List;

public class WorkerThreadExample {
    public static void main(String[] args) {
        BlockingQueue queue = new BlockingQueue();

        // Consumer
        new Thread(new Runnable() {
            @Override public void run() {
                while (true) {
                    Runnable task = queue.take();
                    task.run();
                }
            }
        }, "workerThread").start();

        // Producer
        for (int i = 0; i < 10; i++) {
            queue.put(getTask());
        }
    }

    // Задача, имитирующая деятельность (выполняется 1 сек.)
    public static Runnable getTask() {
        return new Runnable() {
            @Override public void run() {
                System.out.println("Task started: " + Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println("Task finished: " + Thread.currentThread().getName());
            }
        };
    }

    static class BlockingQueue {
        private final List<Runnable> tasks = new ArrayList<>();

        // В качестве монитора многопоточности выступает текущий экзмепляр BlockingQueue
        public synchronized Runnable take() {
            while (tasks.isEmpty()) {
                // Отправляем в ожидание / блокируем текущий поток до тех пор пока в очереди не появится задача
                try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }
            }
            Runnable task = tasks.get(0);
            tasks.remove(task);
            return task;
        }

        // В качестве монитора многопоточности выступает текущий экзмепляр BlockingQueue
        public synchronized void put(Runnable task) {
            tasks.add(task);
            // Пробуждаем / разблокируем поток, который находится в ожидании в методе get(), чтобы он мог обработать добавленную в очередь задачу
            notify();
        }
    }
}