package com.caparniyazi.ds.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A ModifiablePriorityQueue stores Comparable objects.
 * Items may be inserted in any order. They are removed in priority order, with the smallest being removed first,
 * based on the compareTo method.
 * If the item is already in the queue, then it is presumed that its priority has changed,
 * and it is removed and re-inserted in the heap.
 *
 * @param <E> Must be Comparable.
 */
public class ModifiablePriorityQueue<E extends Comparable<E>> extends MyPriorityQueue<E> {
    // Data fields
    /**
     * Maps items to themselves so we can locate and update them
     */
    private final Map<E, E> lookup = new HashMap<>();

    @Override
    public boolean offer(E item) {
        lookup.put(item, item);
        return super.offer(item);
    }

    @Override
    public boolean add(E item) {
        lookup.put(item, item);
        return super.add(item);
    }

    @Override
    public E poll() {
        E item = super.poll();

        if (item != null) {
            lookup.remove(item);
        }

        return item;
    }

    @Override
    public void clear() {
        lookup.clear();
        super.clear();
    }

    /**
     * Update an item whose key (priority) has changed.
     */
    public void update(E item) {
        E storedItem = lookup.get(item);

        if (storedItem == null) {
            throw new NoSuchElementException("Item not found in queue.");
        }
        // Remove old version (heap reorder)
        super.remove(storedItem);

        // Insert updated version
        super.add(item);
        lookup.put(item, item);
    }
}
