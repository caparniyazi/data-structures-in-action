package com.caparniyazi.ds.lambda;

// Our own functional interface.
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);

    /**
     * A function that takes a square and returns the length of the square.
     * R is the Double type.
     *
     *
     * @param before The before function
     * @param <V> The parameter
     * @return The new function.
     */
    default <V> Function<V, R> compose(Function<V, T> before) {
        return (V v) -> apply(before.apply(v));
    }
}
