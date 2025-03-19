package com.caparniyazi.ds.lambda;

public class MapFunApp {
    public static void main(String[] args) {
        MapFun<Integer, String> map = new MapFun<>(5);
        map.put(1, "Ahmet");
        map.put(2, "Ali");
        map.put(3, "Ayşe");
        map.put(5, "Erva");
        map.put(100, "Hasan");

        map.display();
    }
}
