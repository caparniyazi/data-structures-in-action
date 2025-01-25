package com.caparniyazi.ds.maps;

import java.util.HashMap;
import java.util.Map;

public class TestDrive {
    public static void main(String[] args) {
        Map<String, String> aMap = new HashMap<>();
        aMap.put("J", "Jane");
        aMap.put("B", "Bill");
        aMap.put("S", "Sam");
        aMap.put("B1", "Bob");
        aMap.put("B2", "Bill");
        System.out.println("B1 maps to " + aMap.get("B1"));
        System.out.println("Bill maps to " + aMap.get("Bill"));

        aMap = Map.of(
                "J", "Jane",
                "B", "Bill",
                "S", "Sam",
                "B1", "Bob",
                "B2", "Bill");
        System.out.println(aMap);
    }
}
