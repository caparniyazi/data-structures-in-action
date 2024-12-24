package com.caparniyazi.ds.tree;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Class for binary tree that stores type E objects.
 *
 * @param <E> The type
 */
public class BinaryTree<E> {
    // Data fields
    /**
     * It has protected visibility, because we will need to access it in the subclass BinarySearchTree.
     */
    protected Node<E> root; // Reference to the root of the tree.

    /**
     * Class to encapsulate a tree node.
     *
     * @param <E> The type.
     */
    protected static class Node<E> {
        // Data fields
        protected E data;   // The information stored in this node.
        protected Node<E> left; // Reference to the left child
        protected Node<E> right;    // Reference to the right child.

        // Constructors

        /**
         * Construct a node with given data and no children.
         * The constructor for class Node<E> creates a leaf node (both left and right are null).
         *
         * @param data The data to store in this node.
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        /**
         * Return a string representation of the node.
         *
         * @return a string representation of data.
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }

    // Constructor

    /**
     * Constructs an empty binary tree.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Constructs a binary tree with the given node as the root.
     * It has protected visibility, because client classes do not know
     * about the Node class. This constructor can be used only by methods
     * internal to the BinaryTree class and its subclasses.
     *
     * @param root The root node.
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root leftTree
     * as its left subtree and rightTree as its right subtree.
     *
     * @param data      The data at the root.
     * @param leftTree  The left subtree.
     * @param rightTree The right subtree.
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<>(data);

        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }

        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /**
     * Returns the left subtree
     *
     * @return The left subtree or null if either the root or the left subtree is null.
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Returns the right subtree.
     *
     * @return The right subtree or null if either the root or the right subtree is null.
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Returns the data in the root.
     *
     * @return The data in the root.
     */
    public E getData() {
        return root.data;
    }

    /**
     * Determines whether this tree is a leaf.
     *
     * @return true if the root has no children.
     */
    public boolean isLeaf() {
        return root.left == null && root.right == null;
    }

    /**
     * Returns a string representation of the BinaryTree for display purposes.
     * The string representation is a preorder traversal in which each local root is indented
     * a distance proportional to its depth. If a subtree is empty, the string "null" is displayed.
     *
     * @return a string representation of the tree.
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        toString(root, 1, sb);
        return sb.toString();
    }

    /**
     * Converts a subtree to a string. Performs a preorder traversal.
     * It begins by appending a string of spaces proportional to the level so that
     * all nodes at a particular level will be indented to the same point in the tree display.
     * Then, if the node is null, the string "null\n" is appended to the StringBuilder.
     * Otherwise, the string representation of the node
     * is appended to the StringBuilder and the method is recursively called on the left and right
     * subtrees.
     *
     * @param node  The local root.
     * @param depth The depth.
     * @param sb    The StringBuilder to save the output.
     */
    private void toString(Node<E> node, int depth, StringBuilder sb) {
        sb.append(" ".repeat(Math.max(0, depth - 1)));

        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node);
            sb.append('\n');
            toString(node.left, depth + 1, sb);
            toString(node.right, depth + 1, sb);
        }
    }


    /**
     * Method to read a BinaryTree.
     *
     * @param scanner The Scanner attached to the input file.
     * @return The Binary Tree.
     * @pre The input consists of a preorder traversal of the binary tree. The line "null"
     * indicates a null tree.
     */
    public static BinaryTree<String> readBinaryTree(Scanner scanner) {
        // Read a line and trim leading & trailing spaces.
        String data = scanner.nextLine().trim();

        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scanner);
            BinaryTree<String> rightTree = readBinaryTree(scanner);

            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }

    /**
     * Method to return the preorder traversal of the binary tree as
     * a sequence of strings each seperated by a space.
     *
     * @return A preorder traversal as a string.
     */
    public String preOrderToString() {
        return preOrderToString(root);
    }

    private String preOrderToString(Node<E> node) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        stringJoiner.add(node.toString());

        if (node.left != null) {
            stringJoiner.add(preOrderToString(node.left));
        }

        if (node.right != null) {
            stringJoiner.add(preOrderToString(node.right));
        }

        return stringJoiner.toString();
    }

    /**
     * A Method to display the inorder traversal of a binary tree placing a
     * left parenthesis before each subtree and a right parenthesis after each
     * subtree. For example, (x + y) * (a / b) would be represented as
     * (((x) + (y)) * ((a) / (b)))
     *
     * @return An inorder string representation of the tree.
     */
    public String inOrderToString() {
        return inOrderToString(root);
    }

    private String inOrderToString(Node<E> node) {
        StringJoiner stringJoiner = new StringJoiner(" ", "(", ")");

        if (node.left != null) {
            stringJoiner.add(inOrderToString(node.left));
        }

        if (node.right != null) {
            stringJoiner.add(inOrderToString(node.right));
        }

        return stringJoiner.toString();
    }
}
