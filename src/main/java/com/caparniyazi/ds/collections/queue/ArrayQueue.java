package com.caparniyazi.ds.collections.queue;

import jakarta.annotation.Nonnull;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Class that implements the Queue interface using a circular array.
 * <p/>
 * While the time efficiency of using a single‐ or double‐linked list to implement the Queue is
 * acceptable, there is some space inefficiency. Each node of a single‐linked list contains a reference
 * to its successor, and each node of a double‐linked list contains references to its predecessor
 * and successor. These additional references will increase the storage space required.
 * An alternative is to use an array. If we use an array, we can do an insertion at the rear of the
 * array in O(1) time. However, a removal from the front will be an O(n) process if we shift all
 * the elements that follow the first one over to fill the space vacated. Similarly, removal from the
 * rear is O(1), but insertion at the front is O(n). This class is how to avoid this inefficiency.
 * <p/>
 * All three implementations(Single-linked List, Double-linked List, and Circular Array)
 * of the Queue interface are comparable in terms of computation time.
 * All operations are O(1) regardless of the implementation.
 * Although reallocating an array is an O(n) operation, it is amortized over n items,
 * so the cost per item is O(1).
 * <p/>
 * In terms of storage requirements, both linked‐list implementations require more storage
 * because of the extra space required for links. Each node for a single‐linked list would store a total
 * of two references (one for the data and one for the link), a node for a double‐linked list would
 * store a total of three references, and a node for a circular array would store just one reference.
 * Therefore, a double-linked list would require 1.5 times the storage required for a single-linked
 * list with the same number of elements. A circular array that is filled to capacity would
 * require half the storage of a single‐linked list to store the same number of elements. However,
 * if the array were just reallocated, half the array would be empty, so it would require the same
 * storage as a single‐linked list.
 */
public class ArrayQueue<E> extends AbstractQueue<E> implements Queue<E> {
    // Data fields
    private int front;  // Index of the front of the queue.
    private int rear;   // Index of the rear of the queue.
    private int size;   // Current number of items in the queue.
    private int capacity;   // Current capacity of the queue.
    private static final int DEFAULT_CAPACITY = 10; // Default capacity of the queue.
    private E[] theData;   // Array to hold the data.

    private class Iter implements Iterator<E> {
        // Data fields
        private int index;  // Index of next item.
        private int count = 0;  // Count of items accessed so far.

        // Constructor

        /**
         * Initialize the Iter object to reference the first queue element.
         */
        public Iter() {
            index = front;
        }

        /**
         * Returns true if there are more items in the queue to access.
         *
         * @return true if there are more items in the queue to access.
         */
        @Override
        public boolean hasNext() {
            return count < size;
        }

        /**
         * Return the next item in the queue.
         *
         * @return The item with subscript index.
         * @pre index references the next item to access.
         * @post index and count are incremented.
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = theData[index];
            index = (index + 1) % capacity;
            count++;
            return result;
        }

        /**
         * Not implemented.
         * Method throws an UnsupportedOperationException because it would violate the contract
         * for a queue to remove an item other than the first one.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Constructors

    /**
     * Construct a queue with the default capacity.
     */
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Construct the queue with the initial capacity.
     *
     * @param capacity The initial capacity.
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        theData = (E[]) new Object[capacity];
        front = 0;
        rear = capacity - 1;    // The queue is circular.
        size = 0;
    }

    // Methods
    @Override
    @Nonnull
    public Iterator<E> iterator() {
        return new Iter();
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Inserts an item at the rear of the queue.
     *
     * @param item The item to add
     * @return true (always successful)
     * @post Item is added to the rear of the queue.
     */
    @Override
    public boolean offer(E item) {
        if (size == capacity) {
            reallocate();
        }
        size++;
        rear = (rear + 1) % capacity;   // The first queue element will be placed in theData[0] as desired.
        theData[rear] = item;
        return true;
    }

    /**
     * Double the capacity and reallocate that data.
     * <p/>
     * We must first copy the elements from position front through the end of the original
     * array to the beginning of the expanded array; then copy the elements from the beginning of
     * the original array through rear to follow those in the expanded array.
     * <p/>
     * By choosing a new capacity that is twice the current capacity, the cost of the reallocation is
     * amortized across each insert, just as for an ArrayList. Thus, insertion is still considered an
     * O(1) operation.
     *
     * @pre The array is filled to capacity.
     * @post The capacity is doubled and the first half of the expanded array is
     * filled with data.
     */
    @SuppressWarnings("unchecked")
    private void reallocate() {
        int newCapacity = capacity * 2;
        E[] newData = (E[]) new Object[newCapacity];
        int j = front;

        for (int i = 0; i < size; i++) {
            newData[i] = theData[j];
            j = (j + 1) % capacity;
        }
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
        theData = newData;
    }

    /**
     * Removed the entry at the front of the queue and returns it
     * if the queue is not empty.
     *
     * @return The item removed if successful, or null if not.
     * @post front references item that was the second in the queue.
     */
    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }

        E result = theData[front];
        front = (front + 1) % capacity;
        size--;
        return result;
    }

    /**
     * Returns the item at the front of the queue and returns it
     * if the queue is not empty.
     *
     * @return The item at the front of the queue if successful, or null if the queue is empty.
     */
    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }

        return theData[front];
    }
}
