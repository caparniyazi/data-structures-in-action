package com.caparniyazi.ds.tree;

public class TestTwoThreeTree {
    public static void main(String[] args) {
        TwoThreeTree<Integer> tree = new TwoThreeTree<>();

        int[] values = {3, 7, 11, 15, 17, 5, 10, 20, 13};

        for (int v : values) {
            System.out.println("Adding " + v);
            tree.add(v);
            tree.printTree();
        }
        tree.printTree();

        System.out.println(tree.contains(14));
    }
}
