package com.caparniyazi.ds.lambda;

import java.util.function.Function;

public class Chaining {
    public static void main(String[] args) {
        Consumer<String> consumer1 = System.out::println;
        Consumer<String> consumer2 = System.out::println;

        Consumer<String> consumer3 = s -> {
            consumer1.accept(s);
            consumer2.accept(s);
        };
        consumer3.accept("Hello");

//        Consumer<String> consumer4 = consumer1.thenAccept(null);
        //consumer4.accept("Istanbul");

        Function<Integer, Integer> f1 = s -> s + 2;
        Function<Integer, Integer> f2 = s -> s * 2;
        Function<Integer, Integer> f3 = f1.andThen(f2);
        System.out.println(f3.apply(10));
    }
}
