package ru.alfabank.multithreading.listing8;

/** Синхронизированная очередь */
class Q {

    // Элемент очереди
    private int n = 0;
    // Добавлен ли элемент в очередь
    private boolean isValueSet = false;

    /**
     * Получить элемент очереди
     * @return int
     */
    public synchronized int get() {

        while (isValueSet == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Получено: " + n);
        isValueSet = false;
        notify();
        return n;
    }

    /**
     * Положить элемент в очередь
     * @param n
     */
    public synchronized void put(int n) {

        while (isValueSet == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.n = n;
        isValueSet = true;
        System.out.println("Отправлено: " + n);
        notify();
    }
}
