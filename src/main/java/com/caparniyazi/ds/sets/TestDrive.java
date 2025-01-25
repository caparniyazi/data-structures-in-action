package com.caparniyazi.ds.sets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestDrive {
    public static void main(String[] args) {
        Set<String> setA = new HashSet<>();
        Set<String> setB = new HashSet<>();
        String[] listA = {"Ann", "Sally", "Jill", "Sally"};
        String[] listB = {"Bob", "Bill", "Ann", "Jill"};

        // Load sets from arrays.
        for (String s : listA) {
            setA.add(s);
        }

        for (String s : listB) {
            setB.add(s);
        }

        System.out.println("The two sets are: " + "\n" + setA + "\n" + setB);
        var setAcopy = new HashSet<>(setA);
        setA.addAll(setB);  // setA is union

        setAcopy.retainAll(setB);   // setAcopy is intersection.
        System.out.println("Items in set union are: " + setA);
        System.out.println("Items in set intersection are: " + setAcopy);

        // Sets created using Set.of are immutable.
        setB = Set.of("Bob", "Bill", "Ann", "Jill");

        // Creates a new modifiable set whose elements correspond to the elements of the list.
        setA = new HashSet<>(List.of("Ann", "Sally", "Jill", "Sally"));

        // Accessing the items in a set. The elements will be accessed in arbitrary order.
        Iterator<String> iterator = setA.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
        }

        // Simplified form of accessing items in a set
        for (String s : setB) {
            System.out.println(s);
        }
    }
}
