package com.caparniyazi.ds.sealed;

public sealed class Fish permits Trout, Bass {
    public String getName() {
        return "Fish";
    }
}
