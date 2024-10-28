package com.caparniyazi.ds.collections;

import jakarta.annotation.Nonnull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * A generic class to store any group of objects that can be compared.
 * For example, we can use it to maintain a list of students who are registered for a course.
 * We want to maintain this list so that it will be in alphabetical order
 * even after students have added and dropped the course.
 * <p/>
 * If we implement our class as an extension of LinkedList, a client will be able to use
 * methods in the List interface that can insert new elements or modify existing elements in
 * such a way that the items are no longer in order.
 * Therefore, we will use the LinkedList as a component of the OrderedList class, and
 * we will implement only those methods that preserve the order of items.
 */
public class OrderedList<E extends Comparable<E>> implements Iterable<E> {
    // Data fields
    private final List<E> theList = new LinkedList<>();  // A list to contain the data.

    // Methods

    /**
     * Method to insert item into the list preserving the list's order.
     *
     * @param item The item to be inserted.
     * @pre The items in the list are ordered.
     * @post The item has been inserted into the list such that the items are still in order.
     */
    public void add(E item) {
        ListIterator<E> iterator = theList.listIterator();
        // Find the insertion position and insert.

        // 5, 8, 10 && target = 44
        while (iterator.hasNext()) {
            if (item.compareTo(iterator.next()) < 0) {
                // Iterator has stepped over the first element
                // that is greater than the element to be inserted.
                // Move the iterator back one.

                iterator.previous();
                // Insert the element
                iterator.add(item);
                // Exit the loop and return.
                return;
            }
        }

        // assert: All items were examined and no item is larger than the element to be inserted.
        // Add the new item to the end of the list.
        iterator.add(item);
    }

    /**
     * Returns an iterator to this OrderedList.
     *
     * @return The iterator, positioning it before the first element.
     */
    @Override
    @Nonnull
    public Iterator<E> iterator() {
        return theList.iterator();
    }

    /**
     * Returns the element at the specified position.
     *
     * @param index The specified position.
     * @return The element at position index.
     */
    public E get(int index) {
        return theList.get(index);
    }

    /**
     * Method to remove the first occurrence of item from the list.
     * Returns true if the list contained item; otherwise, return false.
     *
     * @param item The item to be removed.
     * @return true if the list contained item and removed; otherwise, return false.
     */
    public boolean remove(E item) {
        return theList.remove(item);
    }

    /**
     * Method to return the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return theList.size();
    }
}
