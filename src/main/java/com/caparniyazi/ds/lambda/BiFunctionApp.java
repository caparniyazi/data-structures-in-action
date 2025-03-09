package com.caparniyazi.ds.lambda;

import java.util.function.BiFunction;

public class BiFunctionApp {
    public static void main(String[] args) {
        BiFunction<String, String, Integer> biFunc = (a, b) -> (a + b).length();
        System.out.println(biFunc.apply("Hello", "World"));
    }
}
