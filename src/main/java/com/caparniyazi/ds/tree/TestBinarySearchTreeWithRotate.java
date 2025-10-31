package com.caparniyazi.ds.tree;

import java.util.Arrays;

public class TestBinarySearchTreeWithRotate {
    public static void main(String[] args) {
        int[] data = Arrays.stream("20 10 40 5 15 7".split(" ")).mapToInt(Integer::parseInt).toArray();
        var bst = new BinarySearchTreeWithRotate<Integer>();

        for (int i : data) {
            bst.add(i);
        }
        System.out.println("Original tree: \n" + bst);
        var root = bst.rotateRight(bst.getRoot());
        bst.setRoot(root);
        System.out.println("More balanced tree after rotation right: \n" + bst);
    }
}
