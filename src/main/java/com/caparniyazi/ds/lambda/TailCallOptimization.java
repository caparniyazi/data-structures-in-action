package com.caparniyazi.ds.lambda;

public class TailCallOptimization {
    public static void main(String[] args) {

    }

    public static long fact(int n) {
        if (n <= 1) {
            return 1;
        } else {

            return n * fact(n - 1);
        }
    }

    public static long tailRecursiveFact(int n, int acc) {
        if (n <= 1) {
            return acc;
        } else {
            return n * tailRecursiveFact(n - 1, acc);
        }
    }
}
