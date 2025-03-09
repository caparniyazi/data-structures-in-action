package com.caparniyazi.ds.lambda;

import java.util.function.Supplier;

public class SupplierApp {
    public static void main(String[] args) {
        Supplier<String> stringSupplier = () -> "Hello World";
        System.out.println(stringSupplier.get());

        Supplier<Double> randomNumber = Math::random;
        System.out.println(randomNumber.get());
    }
}
