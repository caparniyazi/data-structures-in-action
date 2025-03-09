package com.caparniyazi.ds.lambda;

import lombok.val;

public class PureFunction {
    // Data fields
    private int value = 0;

    // Pure
    public int sum(int a, int b) {
        return a + b;
    }

    // Impure
    // Modifying the state.
    public int add(int nextVal) {
        this.value += nextVal;
        return this.value;
    }
}
