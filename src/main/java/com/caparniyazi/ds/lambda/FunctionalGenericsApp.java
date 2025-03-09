package com.caparniyazi.ds.lambda;

public class FunctionalGenericsApp {
    public static void main(String[] args) {
        FunctionalGenerics<String, String> fun = s -> s.substring(1, 5);
        System.out.println(fun.execute("Istanbul"));

        FunctionalGenerics<String, Integer> fun2 = String::length;
        System.out.println(fun2.execute("Istanbul"));
    }
}
