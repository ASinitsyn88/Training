package ru.myapp.concurrency;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new PutThread(exchanger)).start();
        new Thread(new GetThread(exchanger)).start();
    }
}

class PutThread implements Runnable {
    private final Exchanger<String> exchanger;
    private String message;

    public PutThread(Exchanger<String> ex) {
        this.exchanger = ex;
        this.message = "Hello Java!";
    }

    @Override
    public void run() {
        try {
            this.message = exchanger.exchange(message);
            System.out.println("PutThread has received: " + message);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

class GetThread implements Runnable{
    private final Exchanger<String> exchanger;
    private String message;

    public GetThread(Exchanger<String> ex){
        this.exchanger = ex;
        this.message = "Hello World!";
    }

    @Override
    public void run() {
        try {
            message = exchanger.exchange(message);
            System.out.println("GetThread has received: " + message);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}