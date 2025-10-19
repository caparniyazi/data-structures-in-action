package com.caparniyazi.ds.maps;

public class TestSkipList {
    public static void main(String[] args) {
        SkipList<Integer, String> sl = new SkipList<>();
        sl.put(5, "five");
        sl.put(1, "one");
        sl.put(3, "three");
        sl.put(2, "two");
        sl.put(4, "four");

        System.out.println("size: " + sl.size());
        System.out.println(sl);
        System.out.println(sl.toDebugString());

        System.out.println("get(3) = " + sl.get(3));
        System.out.println("contains 3? " + sl.containsKey(3));
        System.out.println("inserting: 6");
        sl.put(6, "six");
        System.out.println("inserting: 0");
        sl.put(0, "zero");
        System.out.println(sl);
        System.out.println(sl.toDebugString());
    }
}
