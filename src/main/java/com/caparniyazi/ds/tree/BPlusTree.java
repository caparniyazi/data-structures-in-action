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
    private static final int ORDER = 4; // Max. number of children per internal node.
    private static final int MAX_KEYS = ORDER - 1;  // Max. keys per leaf.
    private static final int MIN_KEYS = (MAX_KEYS + 1) / 2; // ceil((ORDER-1) / 2)
    private Node<K, V> root;

    // Constructors
    public BPlusTree() {
        root = new LeafNode<>();
    }


    // Public API.

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
            newRoot.children.add(root);
            newRoot.children.add(newChild.newNode);

            // Refresh keys from children.
            newRoot.refreshKeys();
            root = newRoot;
        }
    }

    public boolean delete(K key) {
        boolean deleted = root.delete(key);

        // Shrink root if necessary.
        if (root instanceof InternalNode<K, V> internal && internal.children.size() == 1) {
            root = internal.children.get(0); // shrink root
        }

        return deleted;
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
        LeafNode<K, V> currentLeaf = findLeaf(root, fromKey);

        while (currentLeaf != null) {
            for (int i = 0; i < currentLeaf.keys.size(); i++) {
                K key = currentLeaf.keys.get(i);

                if (key.compareTo(fromKey) >= 0 && key.compareTo(toKey) <= 0) {
                    result.add(currentLeaf.values.get(i));
                } else if (key.compareTo(toKey) > 0) {
                    return result;  // Stop early.
                }
            }
            currentLeaf = currentLeaf.next;
        }

        return result;
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


    // Abstract Node.
    private static abstract class Node<K extends Comparable<K>, V> {
        protected List<K> keys = new ArrayList<>();

        abstract V search(K key);

        abstract SplitResult<K, V> insert(K key, V value);

        abstract boolean delete(K key);

        abstract boolean isUnderflow();

        abstract K getFirstLeafKey();


    }

    private record SplitResult<K extends Comparable<K>, V>(K newKey, Node<K, V> newNode) {
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
        final List<V> values = new ArrayList<>();
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
        boolean delete(K key) {
            int index = Collections.binarySearch(keys, key);

            if (index >= 0) {
                keys.remove(index);
                values.remove(index);
                return true;
            }
            return false;
        }

        boolean isOverflow() {
            return keys.size() > MAX_KEYS;
        }

        @Override
        boolean isUnderflow() {
            return keys.size() < MIN_KEYS;
        }

        SplitResult<K, V> split() {
            int mid = (keys.size() + 1) / 2;  // Ceil half.
            LeafNode<K, V> sibling = new LeafNode<>();

            sibling.keys.addAll(keys.subList(mid, keys.size()));
            sibling.values.addAll(values.subList(mid, values.size()));

            keys.subList(mid, keys.size()).clear();
            values.subList(mid, values.size()).clear();

            // Link the siblings.
            sibling.next = this.next;
            this.next = sibling;

            // Promote the first key of the right sibling.
            return new SplitResult<>(sibling.keys.get(0), sibling);
        }

        @Override
        K getFirstLeafKey() {
            return keys.get(0);
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
                int pos = findChildIndex(newChild.newKey);
                children.add(pos + 1, newChild.newNode);
                keys.add(index, newChild.newKey);

                if (isOverflow()) {
                    return split();
                }
            } else {
                // The child changed, there is no split. Still need to refresh separators.
                refreshKeys();
            }
            return null;
        }

        @Override
        boolean delete(K key) {
            int index = findChildIndex(key);
            boolean deleted = children.get(index).delete(key);

            // After child delete, handle underflow if any.
            if (children.get(index).isUnderflow()) {
                rebalance(index);
            } else if (children.get(index).isUnderflow()) {
                // Even if no underflow, child's first key may have changed (lef delete),
                // So, refresh separators to reflect children.
                refreshKeys();
            }

            return deleted;
        }

        /**
         * Re-balance children around index child (child at index may underflow).
         *
         * @param index The index of the child.
         */
        void rebalance(int index) {
            // Attempt to borrow from the left sibling.
            if (index > 0) {
                Node<K, V> left = children.get(index - 1);
                Node<K, V> current = children.get(index);

                if (left.keys.size() > MIN_KEYS) {
                    // Borrow last key from left into current.
                    if (left instanceof LeafNode<K, V> leftLeaf && current instanceof LeafNode<K, V> currentLeaf) {
                        // move last key/value of the left to front of cur
                        int lk = leftLeaf.keys.size() - 1;
                        currentLeaf.keys.add(0, leftLeaf.keys.remove(lk));
                        currentLeaf.values.add(0, leftLeaf.values.remove(lk));
                        // refresh separators
                        refreshKeys();
                        return;
                    } else if (left instanceof InternalNode<K, V> leftInternal && current instanceof InternalNode<K, V> currentInternal) {
                        // Borrow child from left internal.
                        // Move the last child of left internal to front of current internal.
                        Node<K, V> movedChild = leftInternal.children.remove(leftInternal.children.size() - 1);
                        currentInternal.children.add(0, movedChild);
                        // update keys: The key between left and current becomes moved child's first key.
                        refreshKeys();
                        return;
                    }
                }
            }

            // Attempt to borrow from the right sibling.
            if (index + 1 < children.size()) {
                Node<K, V> right = children.get(index + 1);
                Node<K, V> current = children.get(index);

                if (right.keys.size() > MIN_KEYS) {
                    if (right instanceof LeafNode<K, V> rightLeaf && current instanceof LeafNode<K, V> currentLeaf) {
                        // move first key/value from right into the end of current.
                        currentLeaf.keys.add(rightLeaf.keys.remove(0));
                        currentLeaf.values.add(rightLeaf.values.remove(0));
                        // refresh separators
                        refreshKeys();
                        return;
                    } else if (right instanceof InternalNode<K, V> rightIn && current instanceof InternalNode<K, V> curIn) {
                        // move the first child of rightIn to the end of curIn.
                        Node<K, V> movedChild = rightIn.children.remove(0);
                        curIn.children.add(movedChild);
                        refreshKeys();
                        return;
                    }
                }
            }

            // If neither sibling can lend, merge with a sibling.
            if (index > 0) {
                mergeChildren(index - 1);
            } else {
                mergeChildren(index);
            }
            refreshKeys();
        }

        /**
         * Merge children at index and index + 1.
         *
         * @param index The index.
         */
        void mergeChildren(int index) {
            Node<K, V> left = children.get(index);
            Node<K, V> right = children.get(index + 1);

            if (left instanceof LeafNode<K, V> leftLeaf && right instanceof LeafNode<K, V> rightLeaf) {
                // Append right keys/values into the left
                leftLeaf.keys.addAll(rightLeaf.keys);
                (leftLeaf.values).addAll(rightLeaf.values);
                // link left to right.next
                leftLeaf.next = rightLeaf.next;
            } else if (left instanceof InternalNode<K, V> leftIn && right instanceof InternalNode<K, V> rightIn) {
                // Bring separator (already stored as key[index]) as a key in left?
                // In B+ tree internals are routing only,
                // we simply concatenate right keys/children into the left
                // (separator keys are computed from children).
                leftIn.children.addAll(rightIn.children);
            } else {
                // mixing types shouldn't occur
                throw new IllegalStateException("Unexpected node type");
            }

            // remove right child and corresponding separator key
            children.remove(index + 1);

            if (index < keys.size()) {
                keys.remove(index);
            }
        }

        boolean isOverflow() {
            return children.size() > ORDER;
        }

        @Override
        boolean isUnderflow() {
            return children.size() < (ORDER + 1) / 2;
        }

        /**
         * Canonical split promoting the first key of the right sibling.
         *
         * @return The sibling.
         */
        SplitResult<K, V> split() {
            int midChild = children.size() / 2; // split children around midChild
            InternalNode<K, V> sib = new InternalNode<>();
            // move children[midChild ... end] to sibling
            sib.children.addAll(children.subList(midChild, children.size()));
            // remove moved children from this
            children.subList(midChild, children.size()).clear();
            // refresh keys on both nodes
            this.refreshKeys();
            sib.refreshKeys();
            // promote first key of sibling (its first leaf key)
            K promoteKey = sib.getFirstLeafKey();
            return new SplitResult<>(promoteKey, sib);
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
        K getFirstLeafKey() {
            return children.get(0).getFirstLeafKey();
        }


        /**
         * Make keys equal to the first-key-of-each-right-child convention:
         * keys[i] = children[i+1].getFirstLeafKey() for i = 0...children.size()-2
         */
        void refreshKeys() {
            keys.clear();

            for (int i = 1; i < children.size(); i++) {
                keys.add(children.get(i).getFirstLeafKey());
            }
        }

        @Override
        public String toString() {
            return "Internal" + keys;
        }
    }
}
