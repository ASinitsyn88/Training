package ru.myapp.inheritance;

public class Main {

    static class SuperClass {
        public String message = "SuperClass";

        public String msg() {
            return "Hello from " + message;
        }
    }

    static class SubClass extends SuperClass {
        public String message = "SubClass";

        @Override
        public String msg() {
            return "Hello from " + message;
        }

        public String msg2() {
            return "Hello from " + message;
        }
    }

    public static void main(String[] args) {
        SuperClass c = new SubClass();
        System.out.println(c.msg()); // Hello from SubClass
        System.out.println(c.message); // SuperClass
        //c.msg2(); // Doesn't compile
    }
}