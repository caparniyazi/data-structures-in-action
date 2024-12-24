package com.caparniyazi.ds.lambda;

import java.util.function.Function;

public class LambdaUtil {
    public static void main(String[] args) {
        Function<Integer, Double> f = angle -> Math.cos(Math.toRadians(angle));
        show(0, 360, 30, f);
    }

    /**
     * Display values associated with function f in the range specified.
     *
     * @param low  The lower bound.
     * @param high The upper bound.
     * @param step The increment.
     * @param f    The function object.
     */
    public static void show(int low, int high, int step, Function<Integer, Double> f) {
        for (int i = low; i <= high; i += step) {
            System.out.println("" + i + '°' + " : " + f.apply(i));
        }
    }
}
