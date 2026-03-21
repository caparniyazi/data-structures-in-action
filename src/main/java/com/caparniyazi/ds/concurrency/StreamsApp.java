package com.caparniyazi.ds.concurrency;

import java.util.Arrays;

public class StreamsApp {
    public static void main(String[] args) {
        Arrays.asList("jackal", "kangaroo", "lemur")
                .parallelStream()
                .map(s->s.toUpperCase())
                .forEach(System.out::println);

        System.out.println(Arrays.asList(1,2,3,4,5,6).stream().findAny().get());
    }
}
