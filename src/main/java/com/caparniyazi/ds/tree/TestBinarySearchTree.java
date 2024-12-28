package com.caparniyazi.ds.tree;

/**
 * Show the tree that would be formed for the following data items.
 * Exchange the first and last items in each list and
 * rebuild the tree that would be formed if the items were inserted in the new order.
 * a. happy, depressed, manic, sad, ecstatic
 * b. 45, 30, 15, 50, 60, 20, 25, 90
 */
public class TestBinarySearchTree {
    public static void main(String[] args) {
        String[] data = {"happy", "depressed", "manic", "sad", "ecstatic"};
        BinarySearchTree<String> bst = new BinarySearchTree<>();

        for (String s : data) {
            bst.add(s);
        }
        System.out.println(bst);
        System.out.println("********************************************");

        data = new String[]{"ecstatic", "depressed", "manic", "sad", "happy"};
        bst = new BinarySearchTree<>();

        for (String s : data) {
            bst.add(s);
        }
        System.out.println(bst);
        System.out.println("********************************************");

        Integer[] data3 = {45, 30, 15, 50, 60, 20, 25, 90};
        BinarySearchTree<Integer> bstInt = new BinarySearchTree<>();

        for (Integer i : data3) {
            bstInt.add(i);
        }
        System.out.println(bstInt);
        System.out.println("********************************************");

        data3 = new Integer[]{90, 30, 15, 50, 60, 20, 25, 45};
        bstInt = new BinarySearchTree<>();

        for (Integer i : data3) {
            bstInt.add(i);
        }
        System.out.println(bstInt);
        System.out.println(bstInt.toString());

        System.out.println("********************************************");

        data3 = new Integer[]{60, 25, 85, 15, 40, 70, 95, 5, 20, 35, 50, 65, 80, 90, 10, 30, 45, 55, 75, 81, 17};
        bstInt = new BinarySearchTree<>();

        for (Integer i : data3) {
            bstInt.add(i);
        }
        System.out.println(bstInt);
        System.out.println(bstInt.toString());
        System.out.println(bstInt.toList());
    }
}
