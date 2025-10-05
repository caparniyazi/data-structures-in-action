package com.caparniyazi.ds.tree;

import java.io.*;

public class TestBinaryTree {
    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
        BinaryTree<Integer> tree = new BinaryTree<>(1, null, null);
        tree.root.left = new BinaryTree.Node<>(2);
        tree.root.right = new BinaryTree.Node<>(3);
        tree.root.left.left = new BinaryTree.Node<>(4);
        tree.root.left.right = new BinaryTree.Node<>(5);

        System.out.println(tree);
        System.out.println("Tree size: " + tree.size());
        System.out.println("Tree height: " + tree.height());

        BinaryTree<String> binaryTree = new BinaryTree<>("root",
                new BinaryTree<>("Left", null, null),
                new BinaryTree<>("Right", null, null)
        );

        System.out.println(binaryTree);
        System.out.println("Cloned tree:-----");
        System.out.println(binaryTree.clone());
        System.out.println("-----------------");

        System.out.println("Saving the tree to the file system.");
        // Write out the initial tree to a file.
        BinaryTree.saveBinaryTree(binaryTree, "BinaryTreeTestFile");

        // Read the file back(revive).
        System.out.println("Loading the tree from the file system.");
        BinaryTree<String> redux = BinaryTree.loadBinaryTree(new File("BinaryTreeTestFile"));
        System.out.println(redux);  // Prove that the file was read back in correctly.
    }
}
