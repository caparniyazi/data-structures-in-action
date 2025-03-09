package com.caparniyazi.ds.lambda;

// A generic interface.
@FunctionalInterface
public interface FunctionalGenerics<T, R> {
    R execute(T t);
}
