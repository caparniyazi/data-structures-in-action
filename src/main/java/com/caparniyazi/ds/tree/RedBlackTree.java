package com.caparniyazi.ds.tree;

/**
 * Class to represent Red-Black Tree.
 * <p/>
 * Red-Black Tree is another approach to keeping a tree balanced.
 * Rudolf Bayer developed the Red-Black tree as a special case of B-tree.
 * Leo Guibas and Robert Sedgewick refined the concept and introduced the color convention.
 * <p/>
 * A Red–Black tree maintains the following invariants:
 * <p>
 * 1. A node is either red or black.
 * 2. The root is always black.
 * 3. A red node always has black children.
 * (A null reference is considered to refer to a black node.)
 * 4. The number of black nodes in any path from the root to a leaf is the same.
 *
 * @param <E>
 */
public class RedBlackTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {
    // Data fields
    private int size = 0;

    /**
     * Nested class to represent a Red-Black node.
     */
    protected static class RedBlackNode<E> extends Node<E> {
        // Additional data members
        /**
         * The Color indicator, true if red, false if black.
         */
        private boolean isRed;
        // Constructor

        /**
         * Create a RedBlackNode with the default color of red and the given data field.
         *
         * @param item The data field
         */
        public RedBlackNode(E item) {
            super(item);
            isRed = true;
        }
        // Methods

        /**
         * Return a string representation of this object.
         * The color (red or black) precedes the node's contents.
         *
         * @return String representation of this object
         */
        @Override
        public String toString() {
            return (isRed ? "R:" : "B:") + super.toString();
        }
    }


    /**
     * Insert an item into the tree. This is the starter method of a recursive process.
     *
     * @param item - The item to be inserted
     * @return true if item inserted, false if item already in the tree.
     */
    @Override
    public boolean add(E item) {
        int oldSize = size;
        root = add((RedBlackNode<E>) root, item);
        // Since the root of a Red-Black tree is always black, we set the newly inserted node to black.
        // The cast is necessary because root is a data field that was inherited from BinaryTree and is therefore of type Node.
        ((RedBlackNode<E>) root).isRed = false;
        return size > oldSize;
    }


    /**
     * Recursive add method with balancing.
     *
     * @param localRoot - The root of the subtree
     * @param item      - The item to be inserted
     * @return updated local root of the subtree that now contains the inserted item.
     * @post The data field addReturn is set to true if the insert method succeeded and to
     * false if the item is already in the subtree.
     */
    private Node<E> add(RedBlackNode<E> localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            size++;
            return new RedBlackNode<>(item);
        }
        int comp = item.compareTo(localRoot.data);

        if (comp == 0) { // The item is already in the tree.
            addReturn = false;
            return localRoot;   // Duplicate key.
        } else if (comp < 0) { // item < localRoot.data.
            localRoot.left = add((RedBlackNode<E>) localRoot.left, item);
        } else { // item > localRoot.data
            localRoot.right = add((RedBlackNode<E>) localRoot.right, item);
        }

        // Perform re-balancing
        return rebalance(localRoot);
    }

    /**
     * Rebalance the tree after insertion.
     */
    private RedBlackNode<E> rebalance(RedBlackNode<E> localRoot) {
        // Case 1: Right child red, left child black → rotate left
        if (isRed(localRoot.right) && !isRed(localRoot.left)) {
            localRoot = rotateLeft(localRoot);
        }

        // Case 2: Left child and left-left grandchild are red → rotate right
        if (isRed(localRoot.left) && isRed(localRoot.left.left)) {
            localRoot = rotateRight(localRoot);
        }

        // Case 3: Both children are red → color flip
        if (isRed(localRoot.left) && isRed(localRoot.right)) {
            swapColors(localRoot);
        }

        return localRoot;
    }

    private boolean isRed(Node<E> node) {
        return node != null && ((RedBlackNode<E>) node).isRed;
    }

    private void swapColors(RedBlackNode<E> node) {
        node.isRed = true;
        ((RedBlackNode<E>) node.left).isRed = false;
        ((RedBlackNode<E>) node.right).isRed = false;
    }

    private RedBlackNode<E> rotateLeft(RedBlackNode<E> node) {
        RedBlackNode<E> temp = (RedBlackNode<E>) node.right;
        node.right = temp.left;
        temp.left = node;

        // Swap colors
        temp.isRed = node.isRed;
        node.isRed = true;
        return temp;
    }

    private RedBlackNode<E> rotateRight(RedBlackNode<E> node) {
        RedBlackNode<E> temp = (RedBlackNode<E>) node.left;
        node.left = temp.right;
        temp.right = node;

        temp.isRed = node.isRed;
        node.isRed = true;
        return temp;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Pretty string.
     *
     * @return The string representation of the tree.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringHelper((RedBlackNode<E>) root, sb, 1);
        return sb.toString();
    }

    /**
     * Pretty string helper.
     *
     * @param node  The local root.
     * @param sb    The StringBuilder object.
     * @param depth The depth.
     */
    private void toStringHelper(RedBlackNode<E> node, StringBuilder sb, int depth) {
        if (node == null) {
            sb.append("  ".repeat(depth)).append("null\n");
            return;
        }
        sb.append("  ".repeat(depth)).append(node).append("\n");
        toStringHelper((RedBlackNode<E>) node.left, sb, depth + 1);
        toStringHelper((RedBlackNode<E>) node.right, sb, depth + 1);
    }
}

