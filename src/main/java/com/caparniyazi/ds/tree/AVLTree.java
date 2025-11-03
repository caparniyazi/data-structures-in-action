package com.caparniyazi.ds.tree;

import lombok.Getter;

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
    // Flag to indicate whether the current subtree height has increased as a result of the insertion.
    private boolean increase;
    // Flag to indicate whether the current subtree height has decreased as a result of the deletion.
    private boolean decrease;

    /**
     * Node class for an AVL tree.
     *
     * @param <E>
     */
    @Getter
    protected static class AVLNode<E> extends Node<E> {
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

    private void decrementBalance(AVLNode<E> node) {
        node.balance--;

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
            // Adjust the balances to be their new values after the rotations are performed.

            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else if (leftRightChild.balance > AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else {
                leftChild.balance = AVLNode.BALANCED;
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

    // Re-balance right-heavy tree.
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
            } else if (rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            } else {
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform right operation
            localRoot.right = rotateRight(rightChild);
        } else {    // Right-Right case.
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }

        // Now rotate the local root left.
        return (AVLNode<E>) rotateLeft(localRoot);
    }

    /**
     * Starter method for removing an item from an AVL tree.
     * <p/>
     * When we remove an item from a left subtree, the balance of the local root is increased,
     * and when we remove an item from the right subtree, the balance of the local root is decreased.
     *
     * @param target The target item to be deleted.
     * @return The item removed from the tree or null if the item was not in the tree.
     */
    @Override
    public E delete(E target) {
        decrease = false;
        root = delete((AVLNode<E>) root, target);
        return deleteReturn;
    }

    /**
     * We can adapt the algorithm for removal from a binary search tree to become
     * an algorithm for removal from an AVL tree.
     * We need to maintain a data field decrease that tells the previous level in the recursion
     * that there was a decrease in the height of the subtree that was just returned from.
     *
     * @param localRoot The local root.
     * @param item      The target item to be deleted.
     * @return The modified localRoot that does not contain the item.
     */
    private AVLNode<E> delete(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            deleteReturn = null;
            decrease = false;
            return null;
        }
        int comp = item.compareTo(localRoot.data);

        if (comp < 0) {
            localRoot.left = delete((AVLNode<E>) localRoot.left, item);

            if (decrease) {
                return rebalanceRightAfterDelete(localRoot);
            }

            return localRoot;
        } else if (comp > 0) {
            localRoot.right = delete((AVLNode<E>) localRoot.right, item);

            if (decrease) {
                return rebalanceLeftAfterDelete(localRoot);
            }

            return localRoot;
        } else {
            // Item is at local root.
            deleteReturn = localRoot.data;
            decrease = true;
            return removeNode(localRoot);
        }
    }

    private AVLNode<E> removeNode(AVLNode<E> node) {
        if (node.left == null) {
            return (AVLNode<E>) node.right;
        } else if (node.right == null) {
            return (AVLNode<E>) node.left;
        } else {
            // Node with two children
            if (node.left.right == null) {
                node.data = node.left.data;
                node.left = (AVLNode<E>) node.left.left;
                return node;
            } else {
                node.data = findLargestChild(node.left);

                if (decrease) {
                    return rebalanceRightAfterDelete(node);
                }

                return node;
            }
        }
    }

    private E findLargestChild(Node<E> parent) {
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            decrease = true;
            return returnValue;
        } else {
            E returnValue = findLargestChild((AVLNode<E>) parent.right);

            if (decrease) {
                parent = rebalanceLeftAfterDelete((AVLNode<E>) parent);
            }

            return returnValue;
        }
    }

    private AVLNode<E> rebalanceRightAfterDelete(AVLNode<E> localRoot) {
        switch (localRoot.balance) {
            case AVLNode.LEFT_HEAVY:
                localRoot.balance = 0;
                break;
            case AVLNode.BALANCED:
                localRoot.balance = 1;
                decrease = false;
                break;
            case 1:
                AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;

                if (rightChild.balance >= 0) {
                    localRoot = (AVLNode<E>) rotateLeft(localRoot);

                    if (rightChild.balance == AVLNode.BALANCED) {
                        localRoot.balance = AVLNode.LEFT_HEAVY;
                        ((AVLNode<E>) localRoot.left).balance = AVLNode.RIGHT_HEAVY;
                        decrease = false;
                    } else {
                        localRoot.balance = AVLNode.BALANCED;
                        ((AVLNode<E>) localRoot.left).balance = AVLNode.BALANCED;
                    }
                } else {
                    localRoot = (AVLNode<E>) rotateRightLeft(localRoot);
                }
                break;
        }
        return localRoot;
    }

    private AVLNode<E> rebalanceLeftAfterDelete(AVLNode<E> localRoot) {
        switch (localRoot.balance) {
            case AVLNode.RIGHT_HEAVY:
                localRoot.balance = AVLNode.BALANCED;
                break;
            case AVLNode.BALANCED:
                localRoot.balance = AVLNode.LEFT_HEAVY;
                decrease = false;
                break;
            case AVLNode.LEFT_HEAVY:
                AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;

                if (leftChild.balance <= 0) {
                    localRoot = (AVLNode<E>) rotateRight(localRoot);

                    if (leftChild.balance == 0) {
                        localRoot.balance = 1;
                        ((AVLNode<E>) localRoot.right).balance = AVLNode.LEFT_HEAVY;
                        decrease = false;
                    } else {
                        localRoot.balance = 0;
                        ((AVLNode<E>) localRoot.right).balance = AVLNode.BALANCED;
                    }
                } else {
                    localRoot = (AVLNode<E>) rotateLeftRight(localRoot);
                }
                break;
        }
        return localRoot;
    }
}
