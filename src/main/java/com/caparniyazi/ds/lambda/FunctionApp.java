package com.caparniyazi.ds.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionApp {
    public static void main(String[] args) {
        List<String> list = List.of("Istanbul", "Ankara", "Malatya");
        Function<String, Integer> func = String::length;
        List<Integer> newList = map(list, func);
        System.out.println(newList);
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> func) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(func.apply(t));
        }

        return result;
    }
}
