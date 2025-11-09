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
 * <p/>
 * B-Tree was designed for building indexes to very large databases stored on a hard disk.
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
        this(3);    // The default order is 3 (2-3 Tree).
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
     * Delete the key from a B-Tree.
     * <pre>
     *      Deletes keys from leaves or internal nodes (replacing internal-key with a predecessor).
     * </pre>
     * <pre>
     *     If a child becomes empty, repairs parent, which
     *          1. tries to borrow from a sibling (left or right) that has 2 keys.
     *          2. otherwise, merges the child with a sibling and pulls a key down from a parent.
     * </pre>
     * <pre>
     *      Collapses the root if it becomes empty(so, the height may shrink).
     * </pre>
     *
     * @param key The key to be deleted.
     * @return true if the key deleted, false otherwise.
     * @post All leaves remain at the same level.
     * Each node (except the root) has at least one key.
     */
    public boolean remove(E key) {
        if (root == null) {
            return false;
        }
        boolean removed = remove(root, key);

        if (!removed) {
            return false;
        }

        // If the root became empty and not a leaf, promote its only child.
        if (root.keys.isEmpty() && !root.isLeaf()) {
            root = root.children.get(0);    // Collapse the root if it has become empty after merges.
        }

        size--;
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

    private boolean remove(Node<E> node, E key) {
        int index = Collections.binarySearch(node.keys, key);

        if (index >= 0) { // Key found in this node
            if (node.isLeaf()) {
                node.keys.remove(index);
                return true;
            } else { // Internal node: replace key with the predecessor or successor.
                Node<E> leftChild = node.children.get(index);
                Node<E> rightChild = node.children.get(index + 1);

                if (leftChild.keys.size() >= (ORDER / 2)) {
                    E predecessor = getMax(leftChild);
                    node.keys.set(index, predecessor);
                    return remove(leftChild, predecessor);
                } else if (rightChild.keys.size() >= (ORDER / 2)) {
                    E successor = getMin(rightChild);
                    node.keys.set(index, successor);
                    return remove(rightChild, successor);
                } else {
                    // Merge both children with key
                    mergeChildren(node, index);
                    return remove(leftChild, key);
                }
            }
        } else { // Not found — go into the appropriate child
            int childIndex = -index - 1;

            if (node.isLeaf()) {
                return false;
            }

            Node<E> child = node.children.get(childIndex);
            boolean result = remove(child, key);

            if (child.keys.size() < (ORDER / 2 - 1)) {
                fixUnderflow(node, childIndex);
            }

            return result;
        }
    }

    /**
     * Handle underflow by borrowing or merging.
     */
    private void fixUnderflow(Node<E> parent, int index) {
        Node<E> child = parent.children.get(index);

        // Try to borrow from left sibling
        if (index > 0) {
            Node<E> leftSibling = parent.children.get(index - 1);

            if (leftSibling.keys.size() > (ORDER / 2 - 1)) {
                // rotate right
                child.keys.add(0, parent.keys.get(index - 1));
                parent.keys.set(index - 1, leftSibling.keys.remove(leftSibling.keys.size() - 1));

                if (!leftSibling.isLeaf()) {
                    child.children.add(0, leftSibling.children.remove(leftSibling.children.size() - 1));
                }

                return;
            }
        }

        // Try to borrow from the right sibling
        if (index < parent.children.size() - 1) {
            Node<E> rightSibling = parent.children.get(index + 1);

            if (rightSibling.keys.size() > (ORDER / 2 - 1)) {
                // rotate left
                child.keys.add(parent.keys.get(index));
                parent.keys.set(index, rightSibling.keys.remove(0));

                if (!rightSibling.isLeaf()) {
                    child.children.add(rightSibling.children.remove(0));
                }

                return;
            }
        }

        // Merge with sibling if no borrowing possible.
        if (index > 0) {
            mergeChildren(parent, index - 1);
        } else {
            mergeChildren(parent, index);
        }
    }

    /**
     * Merge child[index] and child[index+1] with parent key[index]
     */
    private void mergeChildren(Node<E> parent, int index) {
        Node<E> left = parent.children.get(index);
        Node<E> right = parent.children.get(index + 1);

        left.keys.add(parent.keys.remove(index));
        left.keys.addAll(right.keys);
        left.children.addAll(right.children);

        parent.children.remove(index + 1);
    }

    private E getMax(Node<E> node) {
        if (node.isLeaf()) {
            return node.keys.get(node.keys.size() - 1);
        }
        return getMax(node.children.get(node.children.size() - 1));
    }

    private E getMin(Node<E> node) {
        if (node.isLeaf()) {
            return node.keys.get(0);
        }
        return getMin(node.children.get(0));
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
