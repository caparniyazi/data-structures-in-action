package com.caparniyazi.ds.tree;

/**
 * The binary search tree is a data structure that enables
 * efficient insertion, search, and retrieval of information (best case is O(log(n)).
 *
 * @param <E> The type parameter.
 */
public interface SearchTree<E> {
    /**
     * Inserts item where it belongs in the tree. Returns true if item is inserted;
     * false if it isn't (already in tree).
     *
     * @param item The item being inserted.
     * @return true if item is inserted; false if it isn't (already in tree).
     */
    boolean add(E item);

    /**
     * Returns true if target is found in the tree.
     *
     * @param target The target item being searched for.
     * @return true if target is found in the tree.
     */
    boolean contains(E target);

    /**
     * Returns a reference to the data item in the node that is equal to the target.
     * If no such node is found, returns null.
     *
     * @param target The target item being searched for.
     * @return a reference to the data item in the node that is equal to the target, or null.
     */
    E find(E target);

    /**
     * Removes target (if found) from tree and returns it; otherwise, returns null.
     *
     * @param target The target item to be deleted.
     * @return target (if found) from tree and returns it; otherwise, returns null.
     */
    E delete(E target);

    /**
     * Removes target (if found) from tree and returns true, otherwise, returns false.
     *
     * @param target The target item to be deleted.
     * @return true if deleted from tree, otherwise, returns false.
     */
    boolean remove(E target);
}
