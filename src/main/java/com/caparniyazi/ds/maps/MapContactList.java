package com.caparniyazi.ds.maps;

import java.util.*;

public class MapContactList implements ContactListInterface {
    // Data fields
    Map<String, List<String>> contacts = new TreeMap<>();

    @Override
    public List<String> addOrChangeEntry(String name, List<String> newNumbers) {
        return contacts.put(name, newNumbers);
    }

    @Override
    public List<String> lookupEntry(String name) {
        return contacts.get(name);
    }

    @Override
    public List<String> removeEntry(String name) {
        return contacts.remove(name);
    }

    @Override
    public void display() {
        contacts.forEach((k, v) -> System.out.println(k + "\n" + v));
    }
}
