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

        String[] arr = "The quick brown fox jumps over the lazy dog".split(" ");
        var tree2 = new TwoThreeTree<String>();

        for (String s : arr) {
            tree2.add(s);
        }
        tree2.printTree();
        System.out.println("-----------------------------------------------------");

        tree = new TwoThreeTree<Integer>();
        values = new int[]{7, 3, 11, 15, 1, 5, 9, 13, 17, 19};

        for (int v : values) {
            tree.add(v);
        }
        tree.printTree();
        System.out.println("-----------------------------------------------------");
        tree.remove(13);
        tree.printTree();

        System.out.println("-----------------------------------------------------");
        values = new int[]{3, 7, 11};
        tree = new TwoThreeTree<>();

        for (int v : values) {
            tree.add(v);
        }
        tree.printTree();
        System.out.println("-----------------------------------------------------");
        tree.add(2);
        tree.printTree();
        System.out.println("-----------------------------------------------------");
        tree.add(6);
        tree.printTree();
        System.out.println("-----------------------------------------------------");
        tree.add(10);
        tree.printTree();
        System.out.println("-----------------------------------------------------");

        tree = new TwoThreeTree<>();
        values = new int[]{3, 7, 11, 15};

        for (int v : values) {
            tree.add(v);
        }
        tree.printTree();
        System.out.println("-----------------------------------------------------");
        tree.add(8);
        tree.printTree();
        System.out.println("-----------------------------------------------------");
        tree.add(12);
        tree.printTree();
        System.out.println("-----------------------------------------------------");

        String[] myArray = "Now is the time for".split(" ");
        TwoThreeTree<String> myTree = new TwoThreeTree<>();
        for (String s : myArray) {
            myTree.add(s);
        }
        myTree.printTree();
        System.out.println("-----------------------------------------------------");
        myTree.add("all");
        myTree.printTree();
        System.out.println("-----------------------------------------------------");
        myTree.add("good");
        myTree.printTree();
        System.out.println("-----------------------------------------------------");
        myTree.add("people");
        myTree.printTree();
        System.out.println("-----------------------------------------------------");
    }
}
