package com.caparniyazi.ds.lambda;

import java.util.Objects;

// Our own consumer
public interface Consumer<T> {
    void accept(T t);

    default Consumer<T> thenAccept(Consumer<T> next) {
        Objects.requireNonNull(next);
        return (T t) -> {
            this.accept(t);
            next.accept(t);
        };
    }
}
