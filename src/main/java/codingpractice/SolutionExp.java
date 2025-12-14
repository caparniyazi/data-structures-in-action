package codingpractice;

import java.util.*;

/**
 * The class to compute the power of a number by implementing a calculator.
 * Create a class MyCalculator which consists of a single method long power(int, int).
 * This method takes two integers, n and p, as parameters and finds n^p.
 * If either n or p is negative, then the method must throw an exception which says "n or p should not be negative."
 * Also, if both n and p are zero, then the method must throw an exception which says "n and p should not be zero."
 */
public class SolutionExp {
    static class Calculator {
        static long pow(int n, int p) throws Exception {
            if (n < 0 || p < 0) {
                throw new Exception("n or p should not be negative.");
            } else if (n == 0 && p == 0) {
                throw new Exception("n and p should not be zero.");
            } else {
                return (long) Math.pow(n, p);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {

            while (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                int p = scanner.nextInt();

                try {
                    System.out.println(Calculator.pow(n, p));
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
