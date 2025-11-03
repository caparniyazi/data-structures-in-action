package com.caparniyazi.ds.tree;

public class TestRedBlackTree {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        int[] values = {10, 20, 30, 15, 25, 5, 1};

        for (int v : values) {
            tree.add(v);
        }

        System.out.println("Inorder traversal (with colors):");
        System.out.println(tree);
        System.out.println("Tree size = " + tree.size());
        System.out.println(tree.toList());
    }
}
