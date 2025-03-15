package com.caparniyazi.ds.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;

public class CustomCollector {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(2, 6, 8, 9, 0, 1, 52, 5, 61, 8, 9, 96, 0, 18, 23);
        Collector<Integer, List<Integer>, List<Integer>> toList = Collector.of(
                ArrayList::new, // Supplier
                List::add,  // Accumulator (BiConsumer)
                (list1, list2) -> { // Combiner (BiFunction)
                    list1.addAll(list2);
                    return list1;
                },   // Characteristics
                Collector.Characteristics.IDENTITY_FINISH
        );

        List<Integer> evenNumbers = numbers.stream().filter(number -> number % 2 == 0)
                .collect(toList);
        evenNumbers.forEach(System.out::println);
        System.out.println("---");

        Collector<Integer, List<Integer>, List<Integer>> toSortedList = Collector.of(
                ArrayList::new,
                List::add,
                (list1, list2) -> { // Combiner (BiFunction)
                    list1.addAll(list2);
                    return list1;
                },
                (list) -> {
                    Collections.sort(list);
                    return list;
                },
                Collector.Characteristics.UNORDERED
        );

        List<Integer> sortedList = numbers.stream().collect(toSortedList);
        System.out.println("List elements sorted in natural order:");
        sortedList.forEach(System.out::println);
    }
}
