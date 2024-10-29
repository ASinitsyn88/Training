package ru.myapp;

import java.util.ArrayDeque;
import java.util.Deque;

// Deque can act as FIFO and LIFO
public class DequeExample {
    public static void main(String[] args) {
        // FIFO methods offer(e) / add(e) - adding an element to the end or tail
        // FIFO methods poll(e) / remove(e) - removing an element from the front or head
        System.out.println("--- FIFO ---");
        Deque fifoDeque = new ArrayDeque();
        fifoDeque.offer(1);
        fifoDeque.offer(2);
        fifoDeque.add(3);
        System.out.println(fifoDeque.poll());
        System.out.println(fifoDeque.poll());
        System.out.println(fifoDeque.remove());

        // LIFO method push(e) - adds to the front
        // LIFO method pop(e) - removes from the front
        System.out.println("--- LIFO ---");
        Deque lifoDeque = new ArrayDeque();
        lifoDeque.push(1);
        lifoDeque.push(2);
        lifoDeque.push(3);
        System.out.println(lifoDeque.pop());
        System.out.println(lifoDeque.pop());
        System.out.println(lifoDeque.pop());
    }
}
