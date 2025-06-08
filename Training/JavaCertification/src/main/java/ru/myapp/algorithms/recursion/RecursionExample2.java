package ru.myapp.algorithms.recursion;

public class RecursionExample2 {
    public static int factorial(int n) { // Let's assume the input is 5
        // Base case
        if (n == 0) {
            return 1;
        }
        // Dive
        // 5: 5 * factorial(5 - 1)
        // 4: 4 * factorial(4 - 1)
        // 3: 3 * factorial(3 - 1)
        // 2: 2 * factorial(2 - 1)
        // 1: 1 * factorial(1 - 1)
        // 0: 1

        // Unwind
        // 0: 1
        // 1: 1 * factorial(0) = 1 * 1 = 1    // this is 1 * factorial from 0 (which is 1)
        // 2: 2 * factorial(1) = 2 * 1 = 2    // this is 2 * factorial from 1 (which is 1)
        // 3: 3 * factorial(2) = 3 * 2 = 6    // this is 3 * factorial from 2 (which is 2)
        // 4: 4 * factorial(3) = 4 * 6 = 24   // this is 4 * factorial from 3 (which is 6)
        // 5: 5 * factorial(4) = 5 * 24 = 120 // this is 5 * factorial from 4 (which is 24)
        return n * factorial(n - 1);
    }

    public static int fibonacci(int n) { // Let's assume the input is 5
        // Base case
        if (n == 0 || n == 1) {
            return n;
        }
        // Dive
        // 5: fibonacci(5-1) + fibonacci(5-2)
        // 4: fibonacci(4-1) + fibonacci(4-2)
        // 3: fibonacci(3-1) + fibonacci(3-2)
        // 2: fibonacci(2-1) + fibonacci(2-2)
        // 1: 1
        // 0: 0

        // Unwind
        // 0: 0
        // 1: 1
        // 2: fibonacci(1) + fibonacci(0) = 1 + 0 = 1 // this is fibonacci from 1 (which is 1) and fibonacci from 0 (which is 0)
        // 3: fibonacci(2) + fibonacci(1) = 1 + 1 = 2 // this is fibonacci from 2 (which is 1) and fibonacci from 1 (which is 1)
        // 4: fibonacci(3) + fibonacci(2) = 2 + 1 = 3 // this is fibonacci from 3 (which is 2) and fibonacci from 2 (which is 1)
        // 5: fibonacci(4) + fibonacci(3) = 3 + 2 = 5 // this is fibonacci from 4 (which is 3) and fibonacci from 3 (which is 2)
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int power(int x, int n) { // Let's assume the input is 2, 5
        // Base Case
        if (n == 0) {
            return 1;
        }
        // Dive
        // 5: 2 * power(2, 5 - 1)
        // 4: 2 * power(2, 4 - 1)
        // 3: 2 * power(2, 3 - 1)
        // 2: 2 * power(2, 2 - 1)
        // 1: 2 * power(2, 1 - 1)
        // 0: 1

        // Unwind
        // 0: 1
        // 1: 2 * power(2, 0) = 2 * 1 = 2   // this is 2 * power from 0 (which is 1)
        // 2: 2 * power(2, 1) = 2 * 2 = 4   // this is 2 * power from 1 (which is 2)
        // 3: 2 * power(2, 2) = 2 * 4 = 8   // this is 2 * power from 2 (which is 4)
        // 4: 2 * power(2, 3) = 2 * 8 = 16   // this is 2 * power from 3 (which is 8)
        // 5: 2 * power(2, 4) = 2 * 16 = 32   // this is 2 * power from 4 (which is 16)
        return x * power(x, n - 1);
    }
}