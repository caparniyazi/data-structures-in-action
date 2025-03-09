package com.caparniyazi.ds.lambda;

public class IteratorPattern {
    public static void main(String[] args) {
        MyArrayList list = new MyArrayList(new Object[]{1, 3, 5, 7, 9});
        list.forEach(System.out::println);
    }
}
