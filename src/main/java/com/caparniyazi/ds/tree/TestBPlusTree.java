package com.caparniyazi.ds.tree;

public class TestBPlusTree {
    public static void main(String[] args) {
        BPlusTree<Integer, String> tree = new BPlusTree<>();
//        Integer[] keys = {5, 7, 8, 10, 13, 15, 17, 18, 20, 22, 26, 27, 30, 32, 35, 38, 40, 42, 46};
        Integer[] keys = {30, 40, 15, 25, 90, 80, 70, 85, 15, 72};

        for (int key : keys) {
            tree.insert(key, "V" + key);
        }

        System.out.println("=== B+ Tree Structure ===");
        tree.printTree();

        System.out.println("\nSearch 22 → " + tree.search(22));
        System.out.println("Search 25 → " + tree.search(25));

        System.out.println("\nRange [15, 32]:");
        System.out.println(tree.findRange(15, 32));
        System.out.println("\nRange [1, 100]:");
        System.out.println(tree.findRange(1, 100));
    }
}
