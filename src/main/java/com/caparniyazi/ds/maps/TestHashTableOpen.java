package com.caparniyazi.ds.maps;

public class TestHashTableOpen {
    public static void main(String[] args) {
        HashtableOpen<String, Integer> table = new HashtableOpen<>();

        table.put("Ahmet", 10);
        table.put("Bahri", 20);
        table.put("Cahit", 30);
        table.printTable();
        table.put("Ahmet", 44);

        System.out.println("Bahri = " + table.get("Bahri"));
        System.out.println("Removing Bahri: " + table.remove("Bahri"));
        System.out.println("Size = " + table.size());

        System.out.println("Table contents: " + table);
    }
}
