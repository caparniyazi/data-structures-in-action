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

        String[] arr = "Now is the time for all good people to come to the aid of others".split(" ");
        var bst2 = new BinarySearchTreeWithRotate<String>();

        for (String word : arr) {
            bst2.add(word);
        }
        System.out.println(bst2);
        int height = bst2.height();
        // log(13) < log 2^4   // 13 is the number of distinct words in this sentence and 4 is the smallest integer greater than log13.
        System.out.println("13 is the number of distinct words in this sentence and 4 is the smallest integer greater than log13.");
        System.out.println("The height of this tree is " + height + ", which is " + (height - 4) + " greater than 4.");
    }
}
