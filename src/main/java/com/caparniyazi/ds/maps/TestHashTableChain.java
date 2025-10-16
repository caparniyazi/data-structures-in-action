package com.caparniyazi.ds.maps;

public class TestHashTableChain {
    @SuppressWarnings("CastCanBeRemovedNarrowingVariableType")
    public static void main(String[] args) {
        KWHashMap<String, Integer> map = new HashtableChain<>();

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);

        ((HashtableChain<String, Integer>) map).printTable();

        System.out.println("Get C: " + map.get("C"));
        System.out.println("Remove B: " + map.remove("B"));
        ((HashtableChain<String, Integer>) map).printTable();

        System.out.println("Add E, F, G");
        map.put("E", 5);
        map.put("F", 6);
        map.put("G", 7);
        ((HashtableChain<String, Integer>) map).printTable();

        System.out.println("Final size: " + map.size());
    }
}
