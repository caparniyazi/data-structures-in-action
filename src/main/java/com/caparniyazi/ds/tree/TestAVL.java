package com.caparniyazi.ds.tree;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        int[] values = {30, 20, 10, 25, 40, 50, 35};

        for (int val : values) {
            tree.add(val);
        }

        System.out.println("AVL Tree (in-order):");
        System.out.println(tree);
        System.out.println(tree.toList());
    }
}
