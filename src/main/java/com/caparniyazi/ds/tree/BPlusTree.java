package com.caparniyazi.ds.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of B+Tree.
 * <p/>
 * All (K, V) pairs are stored in leaf nodes only.
 * Internal nodes contain keys only for routing.
 * Leaf nodes are linked together to enable sequential/range access and are at the same level.
 * The linked list of leaves is referred to as the sequence set (Douglas Comer).
 * Searching proceeds from the root of a B+Tree through the index to a leaf.
 * Since all keys reside in the leaves, it does not matter what values are encountered as the search
 * progresses as long as the path leads to the correct leaf.
 * <p>
 * Anyone who works with large collections of data needs to know how to make file operations efficient.
 * <p>
 * The number of records which can fit in a leaf node is denoted by bucket factor.
 * The leaf nodes are the buckets of the file and may contain several blocks.
 * The records take up more space than the keys and addresses.
 * In fact, this is why B+Tree is so efficient. Since the internal nodes only have the addresses and keys,
 * they tend to have a very large number of children.
 * The average number of children of an internal node in a B+Tree is called the fan-out.
 * A B+Tree with data in the leaves is a sparse (clustering/primary) index. (Just consider the internal nodes the index.)
 *
 * @param <K> The key type parameter.
 * @param <V> The value type parameter.
 */
public class BPlusTree<K extends Comparable<K>, V> {
    // Data fields
    // Each node can have <= 4 children or <=3 keys since ORDER is set to 4 in this case.
    private static final int ORDER = 4; // Max. number of children per node.
    private Node<K, V> root;

    // Abstract Node.
    private static abstract class Node<K extends Comparable<K>, V> {
        protected List<K> keys = new ArrayList<>();

        abstract V search(K key);

        abstract SplitResult<K, V> insert(K key, V value);

        abstract K getFirstLeafKey();
    }

    private static class SplitResult<K extends Comparable<K>, V> {
        K newKey;
        Node<K, V> newNode;

        SplitResult(K newKey, Node<K, V> newNode) {
            this.newKey = newKey;
            this.newNode = newNode;
        }
    }

    // Leaf Node.

    /**
     * When > 3 keys split in half, right sibling gets upper half.
     * Promote the first key of the right sibling.
     *
     * @param <K> The key type parameter.
     * @param <V> The value type parameter.
     */
    private static class LeafNode<K extends Comparable<K>, V> extends Node<K, V> {
        // Data fields
        private final List<V> values = new ArrayList<>();
        LeafNode<K, V> next;    // Linked list for range queries.

        // Methods

        @Override
        V search(K key) {
            int index = Collections.binarySearch(keys, key);

            if (index >= 0) {
                return values.get(index);
            }
            return null;
        }

        @Override
        SplitResult<K, V> insert(K key, V value) {
            int index = Collections.binarySearch(keys, key);

            if (index >= 0) {
                // Replace existing value.
                values.set(index, value);
                return null;
            } else {
                index = -index - 1;
                keys.add(index, key);
                values.add(index, value);
            }

            if (isOverflow()) {
                return split();
            }
            return null;
        }

        @Override
        K getFirstLeafKey() {
            return keys.get(0);
        }

        private boolean isOverflow() {
            return keys.size() > ORDER - 1;
        }

        SplitResult<K, V> split() {
            int mid = keys.size() / 2;
            LeafNode<K, V> sibling = new LeafNode<>();

            sibling.keys.addAll(keys.subList(mid, keys.size()));
            sibling.values.addAll(values.subList(mid, values.size()));

            keys.subList(mid, keys.size()).clear();
            values.subList(mid, values.size()).clear();

            sibling.next = this.next;
            this.next = sibling;

            // Promote the first key of the right sibling.
            return new SplitResult<>(sibling.keys.get(0), sibling);
        }

        @Override
        public String toString() {
            return "Leaf" + keys;
        }
    }

    // Internal Node.

    /**
     * When > 4 children split in half, promote the first key of the right sibling.
     *
     * @param <K> The key type parameter.
     * @param <V> The value type parameter.
     */
    private static class InternalNode<K extends Comparable<K>, V> extends Node<K, V> {
        // Data fields
        final List<Node<K, V>> children = new ArrayList<>();

        @Override
        V search(K key) {
            int index = findChildIndex(key);
            return children.get(index).search(key);
        }

        @Override
        SplitResult<K, V> insert(K key, V value) {
            int index = findChildIndex(key);

            SplitResult<K, V> newChild = children.get(index).insert(key, value);

            if (newChild != null) {
                keys.add(index, newChild.newKey);
                children.add(index + 1, newChild.newNode);

                if (isOverflow()) {
                    return split();
                }
            }
            return null;
        }

        @Override
        K getFirstLeafKey() {
            return children.get(0).getFirstLeafKey();
        }

        private boolean isOverflow() {
            return children.size() > ORDER;
        }

        /**
         * Canonical split promoting the first key of the right sibling.
         *
         * @return The sibling.
         */
        SplitResult<K, V> split() {
            int mid = keys.size() / 2;
            K promote = keys.get(mid);  // First key of the right sibling.
            InternalNode<K, V> sibling = new InternalNode<>();

            // Keys to move: Everything "after" mid.
            sibling.keys.addAll(keys.subList(mid + 1, keys.size()));
            sibling.children.addAll(children.subList(mid + 1, children.size()));

            // Shrink current node.
            keys.subList(mid, keys.size()).clear();
            children.subList(mid + 1, children.size()).clear();

            return new SplitResult<>(promote, sibling);
        }

        private int findChildIndex(K key) {
            int index = Collections.binarySearch(keys, key);

            if (index >= 0) {
                return index + 1;
            } else {
                return -index - 1;
            }
        }

        @Override
        public String toString() {
            return "Internal" + keys;
        }
    }

    // Constructors
    public BPlusTree() {
        root = new LeafNode<>();
    }

    // Methods

    /**
     * Inserts a (key, value) pair.
     *
     * @param key   The key to be inserted.
     * @param value The value to be inserted.
     */
    public void insert(K key, V value) {
        SplitResult<K, V> newChild = root.insert(key, value);

        if (newChild != null) { // Root split.
            InternalNode<K, V> newRoot = new InternalNode<>();
            newRoot.keys.add(newChild.newKey);
            newRoot.children.add(root);
            newRoot.children.add(newChild.newNode);
            root = newRoot;
        }
    }

    public V search(K key) {
        return root.search(key);
    }

    /**
     * Ranged query method using linked leaf nodes.
     *
     * @param fromKey The starting key.
     * @param toKey   The ending key.
     * @return The list of all values for keys where fromKey <= key <= toKey.
     * O(n) = O(log n + k).
     */
    public List<V> findRange(K fromKey, K toKey) {
        List<V> result = new ArrayList<>();
        LeafNode<K, V> current = findLeaf(root, fromKey);

        while (current != null) {
            for (int i = 0; i < current.keys.size(); i++) {
                K key = current.keys.get(i);

                if (key.compareTo(fromKey) >= 0 && key.compareTo(toKey) <= 0) {
                    result.add(current.values.get(i));
                } else if (key.compareTo(toKey) > 0) {
                    return result;  // Stop early.
                }
            }
            current = current.next;
        }

        return result;
    }

    /**
     * Helper method to find the leaf node where a given key belongs.
     *
     * @param node The local root.
     * @param key  The key being sought
     * @return The leaf node where a given key belongs.
     * O(n) = O(Log n) to locate the first leaf.
     */
    private LeafNode<K, V> findLeaf(Node<K, V> node, K key) {
        if (node instanceof LeafNode<K, V> leafNode) {
            return leafNode;
        }

        InternalNode<K, V> internal = (InternalNode<K, V>) node;
        int index = internal.findChildIndex(key);
        return findLeaf(internal.children.get(index), key);
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Node<K, V> node, int level) {
        System.out.println("  ".repeat(level) + node);

        if (node instanceof InternalNode<K, V> internal) {

            for (Node<K, V> child : internal.children) {
                printTree(child, level + 1);
            }
        }
    }
}
