package ru.alfabank.multithreading.listing8;

/** Класс-стартер */
class PC {

    public static void main(String args[]) {

        Q q = new Q();
        new Producer(q);
        new Consumer(q);
    }
}
