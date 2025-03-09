package com.caparniyazi.ds.lambda;

public interface Configurator<T, R> {
    R configure(T t);
}
