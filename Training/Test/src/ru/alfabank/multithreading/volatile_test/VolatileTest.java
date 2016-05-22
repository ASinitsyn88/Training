package ru.alfabank.multithreading.volatile_test;

public class VolatileTest {

    private static volatile int MY_INT = 0;

    // Запуск потоков
    public static void main(String[] args) {

        new ChangeListener().start();
        new ChangeMaker().start();
    }

    // Поток,читающий состояние переменной
    static class ChangeListener extends Thread {

        @Override
        public void run() {
            int local_value = MY_INT;
            while (local_value < 5) {
                if (local_value != MY_INT) {
                    System.out.println("Получено значение: " + MY_INT);
                    local_value= MY_INT;
                }
            }
        }
    }

    // Поток,изменяющий состояние переменной
    static class ChangeMaker extends Thread {

        @Override
        public void run() {

            int local_value = MY_INT;
            while (MY_INT < 5) {
                int a = local_value + 1;
                System.out.println("Установлено значение: " + a);
                MY_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
