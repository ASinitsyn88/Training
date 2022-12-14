package ru.myapp.concurrency;

import java.util.concurrent.Phaser;

public class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();

        new Thread(new CookingThread(phaser, "pot")).start();
        new Thread(new CookingThread(phaser, "knife")).start();
        new Thread(new CookingThread(phaser, "rope")).start();
    }

    static class CookingThread implements Runnable {
        private final Phaser phaser;
        private final String name;

        public CookingThread(Phaser phaser, String name) {
            this.phaser = phaser;
            this.name = name;
            // Для того чтобы поток принимал участие в работе Phaser его нужно зарегистрировать
            // Благодаря этому Phaser знает сколько потоков в нём участвует
            // и сколько потоков должны дойти до конца определённой фазы чтобы можно было начинать следующую фазу
            phaser.register();
        }

        @Override
        public void run() {
            System.out.println(name + " is executing phase " + phaser.getPhase());
            // В этом месте поток сообщает, что завершил фазу 0 и ожидает пока все остальные потоки не завершат фазу 0
            phaser.arriveAndAwaitAdvance();

            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

            System.out.println(name + " is executing phase " + phaser.getPhase());
            // В этом месте поток сообщает, что завершил фазу 1 и ожидает пока все остальные потоки не завершат фазу 1
            phaser.arriveAndAwaitAdvance();

            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

            System.out.println(name + " is executing phase " + phaser.getPhase());
            // В этом месте поток сообщает, что завершил фазу 2 и ожидает пока все остальные потоки не завершат фазу 2
            phaser.arriveAndDeregister();
        }
    }
}