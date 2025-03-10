package com.caparniyazi.ds.lambda;

import java.util.stream.Stream;

public class FilterOp {
    public static void main(String[] args) {
        Stream.of(34, 678, 89, 4, 52, 325, 6)
                .filter(e -> e % 2 == 0)
                .forEach(System.out::println);
    }
}
