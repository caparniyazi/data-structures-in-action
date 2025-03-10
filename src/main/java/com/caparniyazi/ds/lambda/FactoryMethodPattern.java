package com.caparniyazi.ds.lambda;

public class FactoryMethodPattern {
    public static void main(String[] args) {
        Flooring floor = FlooringFactory.getFlooring(-1, 18);
        floor.installation();
    }
}
