package ru.myapp.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Work(countDownLatch).start();
        }
        // Код не продолжит свою работу пока счётчик countDownLatch не дойдёт до 0
        countDownLatch.await();
        System.out.println("all jobs done");
    }
}

class Work extends Thread {
    private final CountDownLatch countDownLatch;

    public Work(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try { sleep(3000); } catch (Exception e) { e.printStackTrace(); }
        System.out.println("job is done");
        countDownLatch.countDown();
    }
}