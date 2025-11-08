package com.caparniyazi.ds.tree;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * A 2-3 non-binary tree implementation with pretty-print visualization.
 * <p>
 * A 2–3 tree is made up of nodes designated as 2-nodes and/or 3-nodes.
 * A 2-node is the same as a binary search tree node:
 * It consists of a data field and references to two children,
 * one child containing values less than the data field
 * and the other child containing values greater than the data field.
 * </p>
 * <p>
 * A 3-node contains two data fields, ordered so that the first is less than the second, and references
 * to three children: one child containing values less than the first data field, one child
 * containing values between the two data fields, and one child containing values greater than
 * the second data field.
 * </p>
 * <pre>
 *                          (x)
 *                         /   \
 *                      (< x)   (> x)
 *
 *                         (x, y)
 *                        /   |    \
 *                 (< x)   (>x <y)   (> x)
 *  </pre>
 * A 2–3 tree has the additional property that all the leaves are at the lowest level.
 * In a 2–3 tree, no leaf is “closer” or “further” to the root than any other —
 * every path from the root to a leaf has the exact same length.
 * This property guarantees perfect balance. Every search follows the same number of levels.
 * The height of a 2-3 tree with n items is always O(log n) - no worse cases like in simple BSTs.
 * <pre>
 *     Example of a 2-3 tree:
 *         [17, 19]
 *     [11, 15]
 *         [13]sn
 *         [9]
 * [7]
 *         [5]
 *     [3]
 *         [1]
 * </pre>
 * <p/>
 * There are no rotations, as are needed to build the AVL and Red–Black trees.
 * The number of items that a 2–3 tree of height h can hold is between 2h − 1 (all 2-nodes) and 3h − 1 (all 3-nodes).
 * Therefore, the height of a 2-3 tree is between log3^n and log2^n. Thus, the search time is O(log n),
 * since logarithms are all related by a constant factor, and constant factors are ignored in big-O notation.
 *
 * @param <E> The parameter type.
 */
public class TwoThreeTree<E extends Comparable<E>> {
    // Data fields
    private Node<E> root;   // Root node of the tree.
    @Getter
    private int size;

    // Nested node class for 2-3 tree.
    private static class Node<E> {
        // Data fields
        List<E> keys = new ArrayList<>(2); // Up to 2 data elements.
        List<Node<E>> children = new ArrayList<>(3);    // Up to 3 children.

        public boolean isLeaf() {
            return children.isEmpty();
        }

        @Override
        public String toString() {
            return keys.toString();
        }
    }

    // Methods

    /**
     * Searching a 2-3 tree is very similar to searching a binary search tree.
     *
     * @param key The key being sought.
     * @return true, if the tree contains the key, otherwise false.
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
        if (node == null) { // If the local root is null, the key is not in the tree.
            return false;
        }

        // Check each key in this node.
        for (E k : node.keys) {
            int cmp = key.compareTo(k);

            if (cmp == 0) {
                return true;
            }

            if (cmp < 0) {
                // Search left/middle child.
                int idx = node.keys.indexOf(k);

                if (idx < node.children.size()) {
                    return find(node.children.get(idx), key);
                } else {
                    return false;
                }
            }
        }

        // Search rightmost child.
        if (!node.children.isEmpty()) {
            return find(node.children.get(node.children.size() - 1), key);
        }
        return false;
    }

    /**
     * Inserts a key into the 2-3 tree.
     * A 2-3 tree maintains balance by being built from the bottom up, not the top down.
     *
     * @param key The key being inserted.
     * @return true if the object is inserted, false if the object already exists in the tree.
     */
    public boolean add(E key) {
        if (root == null) {
            root = new Node<>();
            root.keys.add(key);
            size = 1;
            return true;
        }

        SplitResult<E> result = insert(root, key);

        if (result != null) {
            // The Row was split, so create new root.
            Node<E> newRoot = new Node<>();
            newRoot.keys.add(result.promotedKey);
            newRoot.children.add(result.left);
            newRoot.children.add(result.right);
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
     * @return The SplitResult that contains the inserted item.
     */
    private SplitResult<E> insert(Node<E> node, E key) {
        // Leaf node
        if (node.isLeaf()) {
            node.keys.add(key);
            node.keys.sort(null);

            if (node.keys.size() <= 2) {    // No split.
                return null;
            }

            return split(node);
        }

        // Internal node, navigate to the correct child.
        int i;

        for (i = 0; i < node.keys.size(); i++) {
            if (key.compareTo(node.keys.get(i)) < 0) {
                break;
            }
        }
        SplitResult<E> result = insert(node.children.get(i), key);
        if (result == null) {
            return null;
        }

        // Handle promoted key.
        node.keys.add(i, result.promotedKey);
        node.children.set(i, result.left);
        node.children.add(i + 1, result.right);

        if (node.keys.size() <= 2) {
            return null;
        }

        return split(node);
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
     * Split a full node (3 keys: 1 promoted key + 2 new children)
     *
     * @param node The node to be split.
     * @return the new split.
     */
    private SplitResult<E> split(Node<E> node) {
        E middleKey = node.keys.get(1);

        Node<E> left = new Node<>();
        left.keys.add(node.keys.get(0));

        Node<E> right = new Node<>();
        right.keys.add(node.keys.get(2));

        if (!node.isLeaf()) {
            left.children.add(node.children.get(0));
            left.children.add(node.children.get(1));
            right.children.add(node.children.get(2));
            right.children.add(node.children.get(3));
        }

        return new SplitResult<>(middleKey, left, right);
    }

    /**
     * Delete the key from a 2-3 tree.
     *
     * @param key The key to be deleted.
     * @return true if the key deleted, false otherwise.
     * @post All leaves remain at the same level.
     * Each node (except the root) has at least one key.
     */
    public boolean remove(E key) {
        if (root == null || !contains(key)) {   // We must first search for it.
            return false;
        }

        root = remove(root, key);
        size--;

        if (root != null && root.keys.isEmpty() && !root.isLeaf()) {
            root = root.children.get(0);    // Collapse root if it has become empty after merges.
        } else if (root != null && root.keys.isEmpty()) {
            // The tree became empty
            root = null;
        }

        return true;
    }

    /**
     * Remove key from subtree rooted at node, return possibly updated node.
     * Parent context handles underflow when the child becomes empty.
     *
     * @param node The local root.
     * @param key  The key to be deleted.
     * @return Possibly updated node.
     */
    @SuppressWarnings("DataFlowIssue")
    private Node<E> remove(Node<E> node, E key) {
        if (node == null) {
            return null;
        }

        // If the item to be deleted is a leaf node, we simply delete it.
        if (node.isLeaf()) {
            node.keys.remove(key);

            // Node may become empty, let parent handle underflow.
            return node;
        }

        // Internal node
        for (int i = 0; i < node.keys.size(); i++) {
            int cmp = key.compareTo(node.keys.get(i));

            if (cmp == 0) {
                // Replace it with the predecessor (max in left subtree).
                E pred = findMax(node.children.get(i));
                node.keys.set(i, pred);
                node.children.set(i, remove(node.children.get(i), pred));

                // If child became empty, fix it.

                if (node.children.get(i).keys.isEmpty()) {
                    node = handleUnderflow(node, i);
                }
                return node;
            } else if (cmp < 0) {
                // Descend to child i.
                node.children.set(i, remove(node.children.get(i), key));

                if (node.children.get(i).keys.isEmpty()) {
                    node = handleUnderflow(node, i);
                }
                return node;
            }
        }

        // Key greater than all keys, go to rightmost child.
        int last = node.children.size() - 1;
        node.children.set(last, remove(node.children.get(last), key));

        if (node.children.get(last).keys.isEmpty()) {
            node = handleUnderflow(node, last);
        }
        return node;
    }

    /**
     * Handle underflow at child index 'idx' of parent 'parent'.
     * Returns possibly updated parent (root of this subtree).
     */
    private Node<E> handleUnderflow(Node<E> parent, int idx) {
        Node<E> child = parent.children.get(idx);

        // Try to borrow from left sibling
        if (idx - 1 >= 0) {
            Node<E> left = parent.children.get(idx - 1);
            if (left.keys.size() == 2) {
                // borrow rightmost key from left sibling
                E parentKey = parent.keys.get(idx - 1);
                E borrowed = left.keys.remove(left.keys.size() - 1);
                parent.keys.set(idx - 1, borrowed);
                child.keys.add(0, parentKey);

                if (!left.isLeaf()) {
                    // move left's rightmost child to child's leftmost position
                    Node<E> movedChild = left.children.remove(left.children.size() - 1);
                    child.children.add(0, movedChild);
                }
                return parent;
            }
        }

        // Try to borrow from the right sibling
        if (idx + 1 < parent.children.size()) {
            Node<E> right = parent.children.get(idx + 1);
            if (right.keys.size() == 2) {
                // borrow leftmost key from right sibling
                E parentKey = parent.keys.get(idx);
                E borrowed = right.keys.remove(0);
                parent.keys.set(idx, borrowed);
                child.keys.add(parentKey);

                if (!right.isLeaf()) {
                    // move right's leftmost child to child's rightmost position
                    Node<E> movedChild = right.children.remove(0);
                    child.children.add(movedChild);
                }
                return parent;
            }
        }

        // No sibling to borrow from -> need to merge
        if (idx - 1 >= 0) {
            // merge with left sibling
            Node<E> left = parent.children.get(idx - 1);
            E parentKey = parent.keys.remove(idx - 1);
            // left absorbs parentKey and child's keys/children
            left.keys.add(parentKey);
            // append child's keys
            left.keys.addAll(child.keys);
            if (!child.isLeaf()) {
                left.children.addAll(child.children);
            }
            // remove child from parent's children
            parent.children.remove(idx);
            // if the parent becomes empty (no keys), caller will handle collapse
            return parent;
        } else {
            // merge with the right sibling (idx must be 0 in this branch)
            Node<E> right = parent.children.get(idx + 1);
            E parentKey = parent.keys.remove(idx);
            child.keys.add(parentKey);
            child.keys.addAll(right.keys);
            if (!right.isLeaf()) {
                child.children.addAll(right.children);
            }
            parent.children.remove(idx + 1);
            return parent;
        }
    }

    private E findMax(Node<E> node) {
        if (node.isLeaf()) {
            return node.keys.get(node.keys.size() - 1);
        }

        return findMax(node.children.get(node.children.size() - 1));
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node<E> node, int level) {
        if (node == null) {
            return;
        }

        // Print rightmost child first
        if (node.children.size() >= 2) {
            printTree(node.children.get(node.children.size() - 1), level + 1);
        }

        System.out.println("    ".repeat(level) + node.keys);

        // Print other children (left to right)
        for (int i = node.children.size() - 2; i >= 0; i--) {
            printTree(node.children.get(i), level + 1);
        }
    }
}
