package com.caparniyazi.ds.lambda;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class MapFunctionalOperations {
    public static void main(String[] args) {
        Map<String, String> contacts = new HashMap<>();
        contacts.put("1237589020", "Ahmet");
        contacts.put("1237009020", "Ahmet");
        contacts.put("7890291111", "Mehmet");
        contacts.put("2647210290", "Ali");
        contacts.put("9999999999", "Ekrem");
        contacts.put("9081234567", "Uğur");

        // Traversal (imperative)
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("------------------------------------------");

        // Functional-style
        contacts.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("------------------------------------------");

        // Filter
        contacts.entrySet()
                .stream()
                .filter(e -> e.getValue().equalsIgnoreCase("Ahmet"))
                .forEach(System.out::println);
        System.out.println("------------------------------------------");

        // Map
        contacts.entrySet().stream()
                .distinct()
                .map(Map.Entry::getValue).forEach(System.out::println);
        System.out.println("------------------------------------------");

        // Sorting
        LinkedHashMap<String, String> sortedMap = contacts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
        sortedMap.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("------------------------------------------");

        // Reduce
        Map<String, Double> marks = new HashMap<>();
        marks.put("Science", 99.00);
        marks.put("Maths", 89.00);
        marks.put("English", 83.0);

        OptionalDouble average = marks.values().stream().mapToDouble(d -> d).average();
        System.out.println(average.getAsDouble());
    }
}
