package com.caparniyazi.ds.tree;

/**
 * This class extends the BinarySearchTree by adding the rotate operations.
 * Rotation will change the balance of a search tree while preserving the search tree property.
 * Used for a common base class for self-balancing trees.
 *
 * @param <E> The Element type parameter, must implement Comparable.
 */
public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends BinarySearchTree<E> {
    // Methods

    /**
     * Method that performs a right rotation.
     *
     * @param root The root of the binary tree to be rotated.
     * @return The new root of the rotated tree.
     * @pre root is the root of a binary search tree.
     * @post <pre>
     *     root.right is the root of a binary search tree,
     *     root.right.right is raised one level,
     *     root.right.left does not change
     * </pre>
     */
    protected Node<E> rotateRight(Node<E> root) {
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }

    protected Node<E> rotateLeft(Node<E> root) {
        Node<E> temp = root.right;
        root.right = temp.left;
        temp.left = root;
        return temp;
    }
}
