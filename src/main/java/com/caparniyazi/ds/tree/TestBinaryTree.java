package com.caparniyazi.ds.tree;

import java.io.*;

public class TestBinaryTree {
    @SuppressWarnings("unchecked")
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
        System.out.println(binaryTree.size());
        System.out.println("Cloned tree:-----");
        System.out.println(binaryTree.clone());
        System.out.println("-----------------");

        // Write out the initial tree to a file.
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("BinaryTreeTestFile"))) {
            out.writeObject(binaryTree);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        // Read the file back(revive).
        BinaryTree<String> redux = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("BinaryTreeTestFile"))) {
            redux = (BinaryTree<String>) in.readObject();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.out.println(redux);  // Prove that the file was read back in correctly.
    }
}
