package com.caparniyazi.ds.tree;

import java.io.Serializable;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.function.BiConsumer;

/**
 * Class for binary tree that stores type E objects.
 *
 * @param <E> The type
 */
public class BinaryTree<E> implements Serializable {
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
    protected static class Node<E> implements Serializable {
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
        if (root != null) {
            return root.data;
        } else {
            return null;
        }
    }

    /**
     * Determines whether this tree is a leaf.
     *
     * @return true if the root has no children.
     */
    public boolean isLeaf() {
        return (root == null || (root.left == null && root.right == null));
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
        stringJoiner.add(node.toString());

        if (node.right != null) {
            stringJoiner.add(inOrderToString(node.right));
        }

        return stringJoiner.toString();
    }

    /**
     * Method to return the postorder traversal of the binary tree as
     * a sequence of strings each seperated by a space.
     *
     * @return A postorder traversal as a string.
     */
    public String postOrderToString() {
        return postOrderToString(root);
    }

    private String postOrderToString(Node<E> node) {
        StringJoiner sj = new StringJoiner(" ");

        if (node.left != null) {
            sj.add(postOrderToString(node.left));
        }

        if (node.right != null) {
            sj.add(postOrderToString(node.right));
        }
        sj.add(node.toString());

        return sj.toString();
    }

    public void postOrderTraverse(BiConsumer<E, Integer> consumer) {
        postOrderTraverse(root, 1, consumer);
    }

    private void postOrderTraverse(Node<E> node, int depth, BiConsumer<E, Integer> consumer) {
        if (node == null) {
            consumer.accept(null, depth);
        } else {
            postOrderTraverse(node.left, depth + 1, consumer);
            postOrderTraverse(node.right, depth + 1, consumer);
            consumer.accept(node.data, depth);
        }
    }

    /**
     * Performs an inorder traversal of the tree passing each node and
     * the node's depth to the consumer function.
     *
     * @param consumer The consumer of each node.
     */
    public void inOrderTraverse(BiConsumer<E, Integer> consumer) {
        inOrderTraverse(root, 1, consumer);
    }

    /**
     * Helper method that performs an inorder traversal.
     *
     * @param node     The local root.
     * @param depth    The depth.
     * @param consumer The consumer of each node.
     */
    private void inOrderTraverse(Node<E> node, int depth, BiConsumer<E, Integer> consumer) {
        if (node == null) {
            consumer.accept(null, depth);
        } else {
            inOrderTraverse(node.left, depth + 1, consumer);
            consumer.accept(node.data, depth);
            inOrderTraverse(node.right, depth + 1, consumer);
        }
    }

    /**
     * Starter method for preorder traversal.
     *
     * @param consumer An object that instantiates the BiConsumer interface.
     */
    public void preOrderTraverse(BiConsumer<E, Integer> consumer) {
        preOrderTraverse(root, 1, consumer);
    }

    /**
     * Performs a recursive preorder traversal of the tree,
     * applying the action specified in the consumer object.
     *
     * @param node     The local root.
     * @param depth    The depth.
     * @param consumer The consumer object.
     */
    private void preOrderTraverse(Node<E> node, int depth, BiConsumer<E, Integer> consumer) {
        if (node == null) {
            consumer.accept(null, depth);
        } else {
            consumer.accept(node.data, depth);
            preOrderTraverse(node.left, depth + 1, consumer);
            preOrderTraverse(node.right, depth + 1, consumer);
        }
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
        /*
         * The preOrderTraverse method visits each node in preorder applying the statement block
         * specified in the lambda expression passed as ana argument to the preorder traversal methods.
         */
        preOrderTraverse((e, d) -> {
            sb.append("  ".repeat(Math.max(0, d - 1)));
            sb.append(e);
            sb.append("\n");
        });
        return sb.toString();
    }

    /**
     * The method that returns the total number of nodes.
     * Empty tree has size=0 & height=0
     * 1-node tree has size=1 & height=1
     *
     * @return The total number of nodes.
     */
    public int size() {
        return size(this.root);
    }

    private int size(Node<E> node) {
        if (node == null) {
            return 0;
        }

        return 1 + size(node.left) + size(node.right);
    }

    /**
     * The method that returns the height of the tree.
     * If T is empty, its height is 0.
     * If T is not empty, its height is the max. depth of its nodes.
     *
     * @return The height of the tree.
     */
    public int height() {
        return height(this.root);
    }

    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }

        int hL = height(node.left);
        int hR = height(node.right);
        return 1 + Math.max(hL, hR);
    }
}
