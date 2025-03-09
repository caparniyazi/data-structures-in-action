package com.caparniyazi.ds.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateApp {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("Basics");
        list.add("");
        list.add("Strong");
        list.add("");
        list.add("BasicsStrong");

        Predicate<String> predicate = s -> !s.isEmpty();
        List<String> newList = filterList(list, predicate);
        Predicate<String> filter = s -> s.contains("Basics");
        List<String> filteredList = filterList(list, filter);
        List<Integer> intList = List.of(1, 4, 6, 7, 8);
        Predicate<Integer> intFilter = i -> i % 2 == 0;
        List<Integer> evenNumbers = filterList(intList, intFilter);

        System.out.println(newList);
        System.out.println(filteredList);
        System.out.println(evenNumbers);
    }

    private static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> newList = new ArrayList<>();
        for (T s : list) {
            if (predicate.test(s)) {
                newList.add(s);
            }
        }

        return newList;
    }
}
