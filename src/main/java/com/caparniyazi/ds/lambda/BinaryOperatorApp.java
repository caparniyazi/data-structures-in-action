package com.caparniyazi.ds.lambda;

import java.util.function.BinaryOperator;

public class BinaryOperatorApp {
    public static void main(String[] args) {
        BinaryOperator<String> operator = (a, b) -> a + "." + b;
        String result = operator.apply("apricot", "world");
        System.out.println(result);
    }
}
