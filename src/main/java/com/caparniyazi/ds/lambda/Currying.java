package com.caparniyazi.ds.lambda;

public class Currying {
    public static void main(String[] args) {
        Function<Integer, Function<Integer, Integer>> fun1 = u -> v -> u + v;
//        Function<Integer, Integer> fun2 = fun1.apply(1);
//        Integer sum = fun2.apply(2);
//        System.out.println(sum);

        Integer sum  = fun1.apply(1).apply(2);
        System.out.println(sum);
    }
}
