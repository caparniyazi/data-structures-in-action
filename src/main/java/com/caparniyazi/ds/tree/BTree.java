package com.caparniyazi.ds.tree;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the B-tree.In the B-tree, the maximum number of children is the "order"
 * of the B-tree. Other than the root, each node has between (order/2) and (order) children.
 * <p>
 * A B-tree is a search tree in which each node contains n data items where n is between (order-1)/2 and order-1.
 * (For the root, n may be between 1 and order-1). Each node not a leaf has n+1 children.
 * The tree is always balanced in that all leaves are on the same level.
 * For example, the length of the path from the root to a leaf is constant.
 *
 * @param <E> The type parameter.
 */
public class BTree<E extends Comparable<E>> {
    // Data fields
    private Node<E> root;   // The root node.
    private final int ORDER;  // The max. number of children of a node.
    @Getter
    private int size;

    /**
     * A Node represents a node in a B-tree.
     * <p>
     * The nested node class holds up to (order-1) data items(keys) and (order) references to children.
     * </p>
     *
     * @param <E> The type parameter.
     */
    private static class Node<E> {
        // Data fields
        private final List<E> keys;   // The information.

        /**
         * The links to the children. Child[0] refers to the subtree of children < data[i] for i < size and
         * to the subtree of children > data[size-1], for i == size.
         */
        private final List<Node<E>> children;

        public Node() {
            keys = new ArrayList<>();
            children = new ArrayList<>();
        }

        public boolean isLeaf() {
            return children.isEmpty();
        }

        @Override
        public String toString() {
            return keys.toString();
        }
    }

    // Constructors

    public BTree() {
        this(3);    // Default order is 3 (2-3 tree).
    }

    /**
     * Construct a B-tree with node size "order".
     *
     * @param order The size of a node.
     */
    public BTree(int order) {
        if (order < 3) {
            throw new IllegalArgumentException("Order must be at least 3");
        }
        this.ORDER = order;
    }

    // Methods

    /**
     * Insert an item into the tree.
     *
     * @param key The key being inserted.
     * @return true if the key was inserted.
     */
    public boolean add(E key) {
        if (root == null) {
            root = new Node<>();
            root.keys.add(key);
            size = 1;
            return true;
        }

        // Call recursive insertion with duplicate check.
        InsertResult<E> result = insertUnique(root, key);

        if (result == null) {   // Duplicate detected.
            return false;
        }

        if (result.split != null) {
            // The root was split, so create the new root.
            Node<E> newRoot = new Node<>();
            newRoot.keys.add(result.split.promotedKey);
            newRoot.children.add(result.split.left);
            newRoot.children.add(result.split.right);
            root = newRoot;
        }
        size++;
        return true;
    }

    /**
     * The recursive insert with split propagation.
     *
     * @param node The local root of the subtree.
     * @param key  The item to be inserted.
     * @return The InsertResult that contains the inserted item.
     */
    private InsertResult<E> insertUnique(Node<E> node, E key) {
        // Leaf node
        if (node.isLeaf()) {
            // check duplicates
            int pos = Collections.binarySearch(node.keys, key);

            if (pos >= 0) {
                return null; // Duplicate found.
            }

            node.keys.add(-pos - 1, key);   // Insert it to the point which the key should be.

            if (node.keys.size() < ORDER) {
                return new InsertResult<>(true, null);
            }
            return new InsertResult<>(true, split(node));
        }

        // Internal node, navigate to the correct child.
        int i;
        for (i = 0; i < node.keys.size(); i++) {
            int cmp = key.compareTo(node.keys.get(i));
            if (cmp == 0) return null; // duplicate found
            if (cmp < 0) break;
        }

        InsertResult<E> result = insertUnique(node.children.get(i), key);
        if (result == null) {
            return null; // duplicate found
        }

        if (result.split == null) {
            return result; // no split propagated
        }

        // Child was split, promote its median.
        node.keys.add(i, result.split.promotedKey);
        node.children.set(i, result.split.left);
        node.children.add(i + 1, result.split.right);

        if (node.keys.size() < ORDER) {
            return new InsertResult<>(true, null);
        }

        return new InsertResult<>(true, split(node));
    }

    private static class InsertResult<E> {
        boolean inserted;           // true if key was added
        SplitResult<E> split;       // non-null if node split occurred

        InsertResult(boolean inserted, SplitResult<E> split) {
            this.inserted = inserted;
            this.split = split;
        }
    }

    /**
     * Helper class for handling node splits.
     */
    private static class SplitResult<E> {
        E promotedKey;
        Node<E> left, right;

        SplitResult(E key, Node<E> left, Node<E> right) {
            promotedKey = key;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Split a full node (ORDER keys: 1 promoted key + (ORDER-1) new children)
     *
     * @param node The node to be split.
     * @return the new split.
     */
    private SplitResult<E> split(Node<E> node) {
        int mid = node.keys.size() / 2;
        E median = node.keys.get(mid);

        Node<E> left = new Node<>();
        Node<E> right = new Node<>();

        left.keys.addAll(node.keys.subList(0, mid));
        right.keys.addAll(node.keys.subList(mid + 1, node.keys.size()));

        if (!node.isLeaf()) {
            left.children.addAll(node.children.subList(0, mid + 1));
            right.children.addAll(node.children.subList(mid + 1, node.children.size()));
        }

        return new SplitResult<>(median, left, right);
    }

    /**
     * Searching a B-tree is very similar to searching a binary search tree.
     *
     * @param key The key being sought.
     * @return true, if B-tree contains the key, otherwise false.
     */
    public boolean contains(E key) {
        return find(root, key);
    }

    /**
     * Recursive search.
     *
     * @param node The local root.
     * @param key  The key being sought.
     * @return true, if found, otherwise false.
     */
    private boolean find(Node<E> node, E key) {
        if (node == null) {
            return false;
        }

        for (int i = 0; i < node.keys.size(); i++) {
            int cmp = key.compareTo(node.keys.get(i));

            if (cmp == 0) {
                return true;
            }

            if (cmp < 0) {

                if (i < node.children.size()) {
                    return find(node.children.get(i), key);
                }

                return false;
            }
        }

        // Search rightmost child.
        if (!node.children.isEmpty()) {
            return find(node.children.get(node.children.size() - 1), key);
        }
        return false;
    }

    /**
     * Pretty-print.
     */
    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node<E> node, int level) {
        if (node == null) {
            return;
        }

        // Print rightmost child first
        if (!node.isLeaf()) {
            printTree(node.children.get(node.children.size() - 1), level + 1);
        }

        System.out.println("    ".repeat(level) + node.keys);

        // Print other children (left to right)
        for (int i = node.children.size() - 2; i >= 0; i--) {
            printTree(node.children.get(i), level + 1);
        }
    }
}
