package com.caparniyazi.ds.lambda;

import java.util.Set;
import java.util.stream.Collectors;

public class SetFunctionalOperations {
    public static void main(String[] args) {
        Set<Integer> set = Set.of(3, 56, 7, 82, 39);

        // Traversal
        set.forEach(System.out::println);
        System.out.println("-------------------");

        // Filter
        set.stream()
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);
        System.out.println("-------------------");

        // Sorting
        set.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("-------------------");

        // Map
        Set<Double> hasSet = set.stream()
                .map(Double::valueOf)
                .collect(Collectors.toSet());
        hasSet.forEach(System.out::println);
        System.out.println("-------------------");

        // Reduce
        int sum = set.stream()
                .mapToInt(i -> i)
                .sum();
        System.out.println(sum);
    }
}
