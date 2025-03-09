package com.caparniyazi.ds.lambda;

public class MyArrayList {
    Object[] elements = new Object[5];

    public MyArrayList(Object[] elements) {
        this.elements = elements;
    }

    public void forEach(Consumer<? super Object> action) {
        for (int i = 0; i < elements.length; i++) {
            action.accept(elements[i]);
        }
    }
}
