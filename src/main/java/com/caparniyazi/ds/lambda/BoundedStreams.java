package com.caparniyazi.ds.lambda;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BoundedStreams {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 4, 7, 9, 4);
        Stream<Integer> stream = list.stream();
        Map<Integer, String> map = Map.of(1, "one", 2, "two", 3, "three", 4, "four");
        Stream<Map.Entry<Integer, String>> stream1 = map.entrySet().stream();
        Stream<String> stream2 = map.values().stream();
        Stream<Integer> stream3 = map.keySet().stream();

        Stream<String> stream4 = Stream.of("one", "two", "three", "four");
        Integer[] intArray = {1, 4, 7, 9, 4};
        Stream<Integer> stream5 = Arrays.stream(intArray);

        int[] intArr = {1, 4, 7, 9, 4};
        IntStream stream6 = Arrays.stream(intArr);

        Stream.Builder<Integer> builder = Stream.builder();
        builder.add(1);
        builder.add(4);
        builder.build().forEach(System.out::println);
    }
}
