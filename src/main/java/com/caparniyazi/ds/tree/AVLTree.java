package com.caparniyazi.ds.tree;

/**
 * Self-balancing binary search tree using algorithm defined by Adelson-Velskii and Landis.
 * <p/>
 * Since each subtree is kept as close to balanced as possible, one would expect that the AVL tree
 * provides the expected O(log n) performance. Each subtree is allowed to be out of balance by
 * ±1. Thus, the tree may contain some holes.
 * <p/>
 * It can be shown that in the worst case, the height of an AVL tree can be 1.44 times the height
 * of a full binary tree that contains the same number of items. However, this would still yield
 * O(log n) performance because we ignore constants.
 * <p/>
 * The worst-case performance is very rare. Empirical tests (see, e.g., Donald Knuth, The Art of
 * Computer Programming, Vol. 3: Searching and Sorting [Addison-Wesley, 1973], p. 460) show
 * that, on average, log n + 0.25 comparisons are required to insert the nth item into an
 * AVL tree. Thus, the average performance is very close to that of the corresponding complete
 * binary search tree.
 * <p/>
 * Rotations are O(1).
 * <pre>
 *     Four cases that need re-balancing and their remedies:
 *      • Left–Left (parent balance is −2, left child balance is −1): rotate right around parent.
 *      • Left–Right (parent balance is −2, left child balance is +1): rotate left around child, then rotate right around parent.
 *      • Right–Right (parent balance is +2, right child balance is +1): rotate left around parent.
 *      • Right–Left (parent balance is +2, right child balance is −1): rotate right around child, then rotate left around parent.
 * </pre>
 * <pre>
 *
 *     Tree Type	    Allowed Height Difference	                Balance Info Stored
 *     ---------         -------------------------                   -------------------
 *      AVL Tree	        ≤ 1 at every node	                    Balance factor (−1, 0, +1)
 *      Red-Black Tree	    ~2× of perfectly balanced	            Node color (red/black)
 *      B-tree	            Branching factors kept within range	    Node degree
 * </pre>
 *
 * @param <E> The type parameter.
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {
    // Data fields
    private boolean increase;   // Flag to indicate whether the current subtree height has increased as a result of the insertion.

    /**
     * Node class for an AVL tree.
     *
     * @param <E>
     */
    private static class AVLNode<E> extends Node<E> {
        // Data fields
        public static final int LEFT_HEAVY = -1;
        public static final int BALANCED = 0;
        public static final int RIGHT_HEAVY = 1;
        private int balance;    // Balance factor is the right subtree height - left subtree height.

        /**
         * Construct a node with the given item as the data field.
         *
         * @param item The data field.
         */
        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }

        // Methods
        // Return a string representation of this object.
        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }

    /**
     * Inserts an item into the tree.
     *
     * @param item The item being inserted.
     * @return true if the item is inserted; false if it already exists.
     */
    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    /**
     * Recursive add method. Inserts the given object into the tree.
     *
     * @param localRoot The local root of the subtree.
     * @param item      The object to be inserted.
     * @return The new local root of the subtree with the item inserted.
     * @post addReturn is set to true if the item is inserted, false if the item is already in the tree.
     */
    private AVLNode<E> add(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode<>(item);
        }

        if (item.compareTo(localRoot.data) == 0) {
            // Item is already in the tree.
            increase = false;
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {    // item < data
            localRoot.left = add((AVLNode<E>) localRoot.left, item);

            if (increase) {
                decrementBalance(localRoot);

                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }

            // Re-balance isn't needed.
            return localRoot;
        } else {    // item > data
            localRoot.right = add((AVLNode<E>) localRoot.right, item);

            if (increase) {
                incrementBalance(localRoot);

                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }

            // Re-balance isn't needed.
            return localRoot;
        }
    }


    private void incrementBalance(AVLNode<E> node) {
        node.balance++;

        if (node.balance == AVLNode.BALANCED) { // If now balanced, overall height has not increased.
            increase = false;
        }
    }

    /**
     * Method to re-balance left.
     *
     * @param localRoot Root of the AVL subtree that needs re-balancing.
     * @return a new localRoot.
     * @pre localRoot is the root of an AVL subtree that is critically left-heavy.
     */
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        // Get reference to the left child.
        var leftChild = (AVLNode<E>) localRoot.left;

        // See whether left-right heavy.
        if (leftChild.balance > AVLNode.BALANCED) {
            // Get a reference to left-right child.
            var leftRightChild = (AVLNode<E>) leftChild.right;
            // Adjust the balances.

            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform left operation
            localRoot.left = rotateLeft(leftChild);
        } else {    // Left-Left case.
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }

        // Now rotate the local root right.
        return (AVLNode<E>) rotateRight(localRoot);
    }

    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        // Get reference to the left child.
        var rightChild = (AVLNode<E>) localRoot.right;

        // See whether left-right heavy.
        if (rightChild.balance < AVLNode.BALANCED) {
            // Get a reference to right-left child.
            var rightLeftChild = (AVLNode<E>) rightChild.left;
            // Adjust the balances.

            if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.LEFT_HEAVY;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform right operation
            localRoot.right = rotateRight(rightChild);
        } else {    // Left-Left case.
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }

        // Now rotate the local root left.
        return (AVLNode<E>) rotateLeft(localRoot);
    }


    private void decrementBalance(AVLNode<E> node) {
        node.balance--;

        if (node.balance == AVLNode.BALANCED) { // If now balanced, overall height has not increased.
            increase = false;
        }
    }
}
