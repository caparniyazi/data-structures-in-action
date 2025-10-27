package com.caparniyazi.ds.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * The type parameter E is specified when we create a new BinarySearchTree
 * and E must implement the Comparable interface.
 * Recursive definition of a binary search tree:
 * A set of nodes T is a binary search tree if either of the following is true:
 * • T is empty.
 * • If T is not empty, its root node has two subtrees, TL and TR, such that TL and TR are
 * binary search trees, and the value in the root node of T is greater than all values in TL
 * and is less than all values in TR.
 * <p>
 * Note that the class extends BinaryTree.
 * <p/>
 * The performance (time required to find, insert, or remove an item)
 * of a binary search tree is proportional to the total height
 * of the tree, where we define the height of a tree as the maximum number of nodes along
 * a path from the root to a leaf.
 * A full binary tree of height k can hold 2^k – 1 items.
 * Thus, if the binary search tree were full and contained n items,
 * the expected performance would be O(log n).
 * <p/>
 *
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> implements SearchTree<E> {
    // Data fields
    /*
    These data fields are used to store a second result from the recursive add and delete
    methods of this class. Neither result can be returned directly from the recursive add or delete method
    because they return a reference to a tree node affected by the insertion or deletion operation.
    Besides these data fields shown, class inherits the data field root from class BinaryTree
    (declared as protected) and the inner class Node<E>.
     */
    protected boolean addReturn;    // Return value from the public add method.
    protected E deleteReturn; // Return value from the public delete method.


    /**
     * Starter method add.
     * <p/>
     * Inserting an item into a binary search tree follows a similar algorithm as searching for the
     * item because we are trying to find where in the tree the item would be, if it were there. In
     * searching, a result of null is an indicator of failure; in inserting, we replace this null with a
     * new leaf that contains the new item. If we reach a node that contains the object we are trying
     * to insert, then we can’t insert it (duplicates are not allowed), so we return false to indicate
     * that we were unable to perform the insert.
     *
     * @param item The item being inserted.
     * @return true if the object is inserted, false if the object already exists in the tree.
     * @pre The item to insert must implement the Comparable interface.
     */
    @Override
    public boolean add(E item) {
        root = add(root, item);
        return addReturn;
    }

    /**
     * Recursive add method.
     * <p/>
     *
     * @param localRoot The local root of the subtree.
     * @param item      The item to be inserted.
     * @return The new local root that contains the inserted item.
     * @post The data field addReturn is set true if the item is added to the tree,
     * false if the item is already in the tree.
     */
    private Node<E> add(Node<E> localRoot, E item) {
        if (localRoot == null) {    // Item is not in the tree, insert it.
            addReturn = true;
            return new Node<>(item);
        } else if (item.compareTo(localRoot.data) == 0) {
            // The item is reached. The item is already in the tree, do nothing.
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // Item is less than localRoot.data
            // Attempt to insert item in the left subtree of the local root.
            // After returning from the call, this left subtree is set to reference the modified subtree,
            // or the original subtree if there is no insertion.
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else {
            // Item is greater than localRoot.data
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /**
     * Determine if an item is in the tree.
     *
     * @param target The target item being searched for.
     * @return true if the item is in the tree, false otherwise.
     * @throws ClassCastException if target is not Comparable.
     */
    @Override
    public boolean contains(E target) {
        return find(target) != null;
    }

    /**
     * Starter method find.
     *
     * @param target The Comparable item being sought.
     * @return The item, if found, otherwise null.
     * @pre The target object must implement the Comparable interface.
     */
    @Override
    public E find(E target) {
        return find(root, target);
    }

    /**
     * Recursive find method.
     *
     * @param localRoot The local subtree's root.
     * @param target    The item being sought.
     * @return The item, if found, otherwise null.
     */
    private E find(Node<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }

        // Compare the target with the data field at the root.
        int compResult = target.compareTo(localRoot.data);

        if (compResult == 0) {
            return localRoot.data;
        } else if (compResult < 0) {
            return find(localRoot.left, target);
        } else {
            return find(localRoot.right, target);
        }
    }

    /**
     * Removal also follows the search algorithm except that when the item is found, it is removed.
     * If the item is a leaf node, then its parent's reference to it is set to null, thereby removing
     * the leaf node.
     * <p>
     * If the item has only a left or right child, then the grandparent references the remaining child
     * instead of the child's parent (the node we want to remove).
     * </p>
     * <p>
     * A complication arises when the item we wish to remove has two children. In this case, we
     * need to find a replacement parent for the children.Remember that the parent must be larger
     * than all of the data fields in the left subtree and smaller than all of the data fields in the right
     * subtree.If we take the largest item in the left subtree and promote it to be the parent, then all
     * of the remaining items in the left subtree will be smaller.This item is also less than the items
     * in the right subtree.This item is also known as the inorder predecessor of the item being
     * removed.
     * </p>
     *
     * @param target The item to be deleted.
     * @return The item deleted from the tree or null if the item was not in the tree.
     * @throws ClassCastException if target does not implement Comparable interface.
     * @post The item is not in the tree.
     */
    @Override
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    /**
     * Recursive delete method.
     *
     * @param localRoot The root of the current subtree.
     * @param item      The item to be deleted.
     * @return The modified localRoot that does not contain the item.
     * @post The item is not in the tree; deleteReturn is equal to the deleted item
     * as it was stored in the tree or null if the item was not found.
     */
    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {    // Item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }

        // Search for the item to delete.
        int compResult = item.compareTo(localRoot.data);

        if (compResult < 0) {
            // Item is smaller than localRoot.data
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (compResult > 0) {
            //  Item is larger than localRoot.data
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            // Item is at localRoot
            deleteReturn = localRoot.data;

            // If the node has no children, we need not find a replacement, e.g., replace
            // this node with null (Set the parent of the local root to reference null.)
            if (localRoot.left == null) {
                // If there is no left child, return right child which can also be null.
                return localRoot.right;
            } else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return localRoot.left;
            } else {
                // The Node being deleted has two children, replace the data with inorder predecessor.
                if (localRoot.left.right == null) {
                    // The left child has no right child
                    // Replace the data with the data in the left child.
                    localRoot.data = localRoot.left.data;
                    // Replace the left child with its left child.
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    // Search for the inorder predecessor and
                    // replace deleted node's data with its inorder predecessor.
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    /**
     * Find the node that is the inorder predecessor and replace it with its left child (if any).
     *
     * @param parent The parent of possible inorder predecessor (ip).
     * @return The data in the ip.
     * @post The inorder predecessor is removed from the tree.
     */
    private E findLargestChild(Node<E> parent) {
        // If the right child has no right child, it is the inorder predecessor.

        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }

    /**
     * Removes target from a tree.
     *
     * @param target The target item to be removed.
     * @return true if the item was in the tree, false otherwise.
     * @post Target is not in the tree.
     */
    @Override
    public boolean remove(E target) {
        return delete(target) != null;
    }

    /**
     * The method that returns the tree contents in ascending order (using an inorder traversal).
     *
     * @return the tree contents in ascending order (using an inorder traversal).
     */
    public List<E> toList() {
        final List<E> result = new ArrayList<>();
        inOrderTraverse((e, d) -> {
            if (e != null) {
                result.add(e);
            }
        });

        return result;
    }
}
