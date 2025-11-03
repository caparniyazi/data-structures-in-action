package com.caparniyazi.ds.tree;


public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        int[] values = {30, 20, 10, 25, 40, 50, 35};
        // int[] values = {10, 20, 25, 30, 35, 40, 50};

        for (int val : values) {
            tree.add(val);
            System.out.println("Added " + val + "\n" + tree);
            System.out.println("Balance is " + ((AVLTree.AVLNode<Integer>) tree.getRoot()).getBalance());
        }

        System.out.println("AVL Tree (in-order):");
        System.out.println(tree);
        System.out.println(tree.toList());
        System.out.println("Balance is " + ((AVLTree.AVLNode<Integer>) tree.getRoot()).getBalance());

        System.out.println("Deleting 20 and 10...");
        tree.delete(20);
        tree.delete(10);
        System.out.println(tree);
        System.out.println(tree.toList());
        System.out.println("Balance is " + ((AVLTree.AVLNode<Integer>) tree.getRoot()).getBalance());

        String[] lhArray = "50 25 60 20 30 10".split(" ");
        var lhBSTree = new BinarySearchTreeWithRotate<String>();
        for (String word : lhArray) {
            lhBSTree.add(word);
        }
        System.out.println(lhBSTree);
        System.out.println("Height is: " + lhBSTree.height());
        var root = lhBSTree.rotateRight(lhBSTree.getRoot());
        lhBSTree.setRoot(root);
        System.out.println(lhBSTree);
        System.out.println("Height is: " + lhBSTree.height());

        String[] arr = "The quick brown".split(" ");
        AVLTree<String> avlTree = new AVLTree<>();
        for (String word : arr) {
            avlTree.add(word);
            System.out.println(avlTree);
        }

        avlTree.add("fox");
        System.out.println(avlTree);
        avlTree.add("jumps");
        System.out.println(avlTree);
        avlTree.add("over");
        System.out.println(avlTree);
        avlTree.add("the");
        System.out.println(avlTree);
        avlTree.add("lazy");
        System.out.println(avlTree);
        avlTree.add("dog");
        System.out.println(avlTree);
    }
}
