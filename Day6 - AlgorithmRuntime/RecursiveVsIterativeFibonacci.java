public class RecursiveVsIterativeFibonacci {
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void main(String[] args) {
        int n = 30;

        long start = System.nanoTime();
        int resultRec = fibonacciRecursive(n);
        long timeRec = System.nanoTime() - start;

        start = System.nanoTime();
        int resultItr = fibonacciIterative(n);
        long timeItr = System.nanoTime() - start;

        System.out.println("Recursive Fibonacci of " + n + " = " + resultRec);
        System.out.println("Time taken (Recursive): " + timeRec / 1e6 + " ms");

        System.out.println("Iterative Fibonacci of " + n + " = " + resultItr);
        System.out.println("Time taken (Iterative): " + timeItr / 1e6 + " ms");
    }
}