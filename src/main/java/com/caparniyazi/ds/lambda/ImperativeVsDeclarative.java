package com.caparniyazi.ds.lambda;

import java.util.stream.IntStream;

public class ImperativeVsDeclarative {
    public static void main(String[] args) {
        // Imperative
        int sumOfEvens = 0;

        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                sumOfEvens += i;
            }
        }
        System.out.println(sumOfEvens);

        // Declarative or Functional style is more readable and concise.
        sumOfEvens = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0).reduce(0, Integer::sum);
        System.out.println(sumOfEvens);
    }
}
