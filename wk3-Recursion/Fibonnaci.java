import java.util.Arrays;
import java.lang.*;
import java.io.*;



public class Fibonnaci {
    
    static int fibonacciIterative(int n) {
        
        if (n <= 1) { return 1; }
        
        int fib = 1;
        int prevFib = 1;
        
        for (int i = 2; i < n; i++) {
            
            int temp = fib;
            fib = fib + prevFib;
            prevFib = temp;
        }
        
        return fib;
    }
    
    
    static int fibonacciRecursive(int x) {
        
        if (x <= 1) { return x; }
        
        return fibonacciRecursive(x - 1) + fibonacciRecursive(x - 2);
    }
    
    
    public static void main(String args[]) {
        
        int n = 30;
        Stopwatch timer1 = new Stopwatch();
        System.out.println(fibonacciIterative(n));
        StdOut.println("elapsed time = " + timer1.elapsedTime());
        Stopwatch timer2 = new Stopwatch();
        System.out.println(fibonacciRecursive(n));
        StdOut.println("elapsed time = " + timer2.elapsedTime());
    }
    
}
