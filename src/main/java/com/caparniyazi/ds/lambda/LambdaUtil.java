package com.caparniyazi.ds.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LambdaUtil {
    // Data fields
    /*
    lambda expression that takes a double argument (x) and an integer argument (n).
    The method result is the double value raised to the power n. Doing this using a Java
    API method and also using a loop.
     */
    static BiFunction<Double, Integer, Double> power = Math::pow;
    static BiFunction<Double, Integer, Double> power2 = (x, n) -> {
        double result = 1.0;

        for (int i = 0; i < n; i++) {
            result *= x;
        }

        return result;
    };

    public static void main(String[] args) {
        Function<Integer, Double> f1 = angle -> Math.sin(Math.toRadians(angle));
        Function<Integer, Double> f2 = angle -> Math.cos(Math.toRadians(angle));

        show(0, 360, 30, f1, f2);
        System.out.println(power.apply(3.0, 3));
        System.out.println(power2.apply(3.0, 3));
    }

    /**
     * Display values associated with function f in the range specified.
     *
     * @param low  The lower bound.
     * @param high The upper bound.
     * @param step The increment.
     * @param f1   A function to apply.
     * @param f2   A function to apply.
     */
    public static void show(int low,
                            int high,
                            int step, Function<Integer, Double> f1,
                            Function<Integer, Double> f2) {
        for (int i = low; i <= high; i += step) {
            System.out.printf("%3d%s %2.4f  %2.4f%n", i, '°', f1.apply(i), f2.apply(i));
        }
    }
}
