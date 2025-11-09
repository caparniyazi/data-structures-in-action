package com.caparniyazi.ds.tree;

public class TestBTree {
    public static void main(String[] args) {
        BTree<Integer> btree = new BTree<>(4); // order 4 (2–3–4 tree)

        int[] data = {10, 20, 5, 6, 12, 30, 7, 17, 25, 40, 50, 60, 20};

        for (int i : data) {
            boolean added = btree.add(i);
            System.out.printf("Add %d → %s%n", i, added ? "inserted" : "duplicate");
        }

        btree.printTree();
        System.out.println("Size: " + btree.getSize());
        btree.remove(20);
        btree.printTree();
        System.out.println("Size: " + btree.getSize());
        System.out.println("==========================================================");

        btree = new BTree<>(5);
        data = new int[]{20, 30, 8, 10, 15, 18, 44, 26, 28, 23, 25, 43, 55, 36, 44, 39};

        for (int i : data) {
            btree.add(i);
        }
        btree.printTree();
        System.out.println("Size: " + btree.getSize());
        System.out.println("==========================================================");
    }
}
