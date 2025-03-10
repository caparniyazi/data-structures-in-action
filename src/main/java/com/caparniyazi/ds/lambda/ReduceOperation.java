package com.caparniyazi.ds.lambda;

import java.util.stream.Stream;

public class ReduceOperation {
    public static void main(String[] args) {
        Integer sum = Stream.of(1, 2, 34, 56, 76, 87, 89, 90)
                .reduce(0, Integer::sum);// Get the sum of all elements.
        System.out.println(sum);
    }
}
