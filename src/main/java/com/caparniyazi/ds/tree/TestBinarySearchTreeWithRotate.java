package com.caparniyazi.ds.tree;

public class TestBinarySearchTreeWithRotate {
    public static void main(String[] args) {
        String[] data = "1 2 3 4 5".split(" ");
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        for (String s : data) {
            bst.add(s);
        }
        System.out.println(bst);
    }
}
