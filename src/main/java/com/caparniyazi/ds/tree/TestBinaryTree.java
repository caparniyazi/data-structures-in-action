package com.caparniyazi.ds.tree;

import java.io.*;

public class TestBinaryTree {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws FileNotFoundException {
        BinaryTree<String> binaryTree = new BinaryTree<>("root",
                new BinaryTree<>("Left", null, null),
                new BinaryTree<>("Right", null, null)
        );

        System.out.println(binaryTree);

        // Write out the initial tree to a file.
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("BinaryTreeTestFile"))) {
            out.writeObject(binaryTree);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        // Read the file back.
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
