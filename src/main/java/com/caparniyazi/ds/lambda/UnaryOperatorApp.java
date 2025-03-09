package com.caparniyazi.ds.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class UnaryOperatorApp {
    public static void main(String[] args) {
        List<Integer> list = List.of(10, 20, 30, 40, 50);
        UnaryOperator<Integer> unaryOperator = i -> i * 100;
        List<Integer> newList = mapper(list, unaryOperator);
        System.out.println(newList);
    }

    private static <T> List<T> mapper(List<T> list, UnaryOperator<T> unaryOperator) {
        List<T> newList = new ArrayList<>();

        for (T t : list) {
            newList.add(unaryOperator.apply(t));
        }

        return newList;
    }
}
