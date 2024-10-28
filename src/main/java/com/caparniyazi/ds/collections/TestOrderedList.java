package com.caparniyazi.ds.collections;

import java.util.Random;

/**
 * Class that tests our implementation of the OrderedList.
 */
public class TestOrderedList {
    public static void traverseAndShow(OrderedList<Integer> list) {
        int prevItem = list.get(0);

        for (Integer thisItem : list) {
            System.out.println(thisItem);

            if (prevItem > thisItem) {
                System.out.println("Failed! value is: " + thisItem);
            }
            prevItem = thisItem;
        }
        System.out.println("*************************************");
    }

    public static void main(String[] args) {
        OrderedList<Integer> list = new OrderedList<>();
        final int MAX_INT = 500;
        final int START_SIZE = 10;

        Random rand = new Random();
        for (int i = 0; i < START_SIZE; i++) {
            int anInt = rand.nextInt(MAX_INT);
            list.add(anInt);
        }

        // Add to the beginning and end of the list.
        list.add(-1);
        list.add(MAX_INT + 1);
        traverseAndShow(list);

        // Remove first, last, and middle elements.
        Integer first = list.get(0);
        Integer last = list.get(list.size() - 1);
        Integer middle = list.get(list.size() / 2);
        list.remove(first);
        list.remove(last);
        list.remove(middle);
        traverseAndShow(list);
    }
}
