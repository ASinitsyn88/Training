package ru.myapp.test2;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibonacci(100));
    }

//    public static long fibonacci(long n){
//        ///f(i) = f(i-1) + f(i-2);
//        if (n == 0) {
//            return 0;
//        }
//        if (n == 1) {
//            return 1;
//        }
//        long result = fibonacci(n - 1) + fibonacci(n - 2);
//        return result;
//    }

    public static long fibonacci(long n){
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long prev1 = 1;
        long prev2 = 0;
        long counter = 2;
        long res = 0L;
        while (counter <= n) {
            counter = ++counter;
            res = prev1 + prev2;
            prev2 = prev1;
            prev1 = res;
        }
        return res;
    }
}