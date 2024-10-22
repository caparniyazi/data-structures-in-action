package com.caparniyazi.ds.collections;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A sample implementation of ArrayList class. It does not implement the List interface.
 */
public class MyArrayList<E> {
    // Data fields
    private static final int INITIAL_CAPACITY = 10;
    private E[] theData;    // The underlying data array
    private int size;   // The current size
    private int capacity;   // The current capacity

    // Constructors
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        capacity = INITIAL_CAPACITY;
        theData = (E[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Invalid initial capacity: " + capacity);
        }

        this.capacity = capacity;
        theData = (E[]) new Object[capacity];
    }

    // Methods

    /**
     * Appends an item to the end of an ArrayList.
     *
     * @param entry An entry
     * @return true
     */
    public boolean add(E entry) {
        if (size == capacity) {
            reallocate();
        }

        theData[size++] = entry;
        return true;    // For an ArrayList, this is always true.
    }

    /**
     * Method to insert an item to the middle of the array.
     *
     * @param index The index of the insertion point.
     * @param entry An entry to be inserted.
     *              Complexity: O(n)
     */
    public void add(int index, E entry) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        if (size == capacity) {
            reallocate();
        }

        // Shift data in elements from index to size - 1.
        for (int i = size; i > index; i--) {
            theData[i] = theData[i - 1];
        }
        // Insert the new item.
        theData[index] = entry;
        size++;
    }

    /**
     * Helper method to create a new array that is twice the size of the current array
     * and then copies the contents of the current array into the new one.
     * The reason for doubling is to spread out the cost of copying.
     */
    private void reallocate() {
        capacity *= 2;
        theData = Arrays.copyOf(theData, capacity);
    }

    /**
     * Method to return the item at the specified index.
     *
     * @param index The index/position of the item.
     * @return the item at the specified index.
     * Complexity: O(1)
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        return theData[index];
    }

    /**
     * Method to insert the new item at the specified index and returns the value
     * previously stored at that index.
     *
     * @param index The index
     * @param entry The item to store
     * @return the new item at the specified index and returns the value
     * previously stored at that index.
     * Complexity: O(1)
     */
    public E set(int index, E entry) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        E oldValue = theData[index];
        theData[index] = entry;
        return oldValue;
    }

    /**
     * Method to remove the item at the specified index.
     *
     * @param index The index.
     * @return the item removed.
     * Complexity: O(n)
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        E oldValue = theData[index];
        for (int i = index + 1; i < size; i++) {
            theData[i - 1] = theData[i];
        }
        size--;
        return oldValue;
    }

    /**
     * Get the current size of the array.
     *
     * @return the current size of the array.
     */
    public int size() {
        return size;
    }

    /**
     * Method to remove the middle item from ArrayList and place it at the end.
     */
    public void moveMiddleToEnd() {
        int index = size / 2;
        E removedItem = remove(index);
        add(removedItem);
    }

    /**
     * Method to find the first index of the target value.
     *
     * @param target The value being searched for.
     * @return the first index of the target, -1 if not found.
     * Complexity: O(n)
     */
    public int indexOf(E target) {
        for (int i = 0; i < size; i++) {
            if (theData[i].equals(target)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(theData[i]);

            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
