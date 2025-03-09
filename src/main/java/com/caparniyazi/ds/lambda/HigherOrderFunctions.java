package com.caparniyazi.ds.lambda;

public class HigherOrderFunctions {
    public static void main(String[] args) {
        Factory<Integer> factory = createFactory(() -> Math.random() * 100, Double::intValue);
        Integer product = factory.create();
        System.out.println(product);
    }

    public static <T, R> Factory<R> createFactory(Producer<T> producer, Configurator<T, R> configurator) {
        return () -> {
            T product = producer.produce();
            return configurator.configure(product);
        };
    }
}
