package com.caparniyazi.ds.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

public class Characteristics {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(4);
        list.add(7);
        list.add(9);
        list.add(0);
        list.add(1);

        Stream<Integer> stream = list.stream();
        Spliterator<Integer> spliterator = stream.spliterator();
        int bits = spliterator.characteristics();
        System.out.println(Integer.bitCount(bits)); // result = 3 => means three properties are set.
        System.out.println(bits & 0x00000010);

        System.out.println(spliterator.hasCharacteristics(0x00000010));
        System.out.println(spliterator.hasCharacteristics(Spliterator.ORDERED));
    }
}
