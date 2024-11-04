package com.caparniyazi.ds.collections.queue;

import jakarta.annotation.Nonnull;

import java.util.*;

/**
 * Class that implements the Queue interface using a single-linked list.
 */
public class ListQueue<E> extends AbstractQueue<E> implements Queue<E> {
    // Data fields
    private Node<E> front; // Reference to front of queue.
    private Node<E> rear;   // Reference to rear of queue.
    private int size;   // Size of queue.

    /**
     * The building-block for a list.
     *
     * @param <E> The data the node references.
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;

        // Constructors

        /**
         * Create a new node with the given data value.
         *
         * @param data The data stored.
         */
        private Node(E data) {
            this.data = data;
        }
    }

    /**
     * Inner class to provide an iterator to the contents of the queue.
     */
    private class MyIterator implements Iterator<E> {
        // Data fields
        private Node<E> next = front;

        /**
         * Returns true if there are more items in the iteration.
         *
         * @return true if there are more items in the iteration.
         */
        @Override
        public boolean hasNext() {
            return next != null;
        }

        /**
         * Return the next item in the iteration and advance the iterator.
         *
         * @return the next item in the iteration.
         * @throws NoSuchElementException if the iteration has no more items.
         */
        @Override
        public E next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            E data = next.data;
            next = next.next;
            return data;
        }

        /**
         * This operation is not supported.s
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    @Nonnull
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Method to insert an item at the rear of the queue.
     *
     * @param item the element to add
     * @return true (always successful).
     * @post Item is added to the rear of the queue.
     */
    @Override
    public boolean offer(E item) {
        // Check for empty queue
        if (front == null) {
            rear = new Node<>(item);
            front = rear;
        } else {
            // Allocate a new node at end, store item in it, and link it to old end of queue.
            rear.next = new Node<>(item);
            rear = rear.next;
        }
        size++;
        return true;
    }

    /**
     * Remove the entry at the front of the queue and return it if the queue is not empty.
     *
     * @return The item removed if successful, or null if not.
     * @post front references item that was second in the queue.
     */
    @Override
    public E poll() {
        E item = peek();
        // Retrieve item at front.
        if (item == null) {
            return null;
        }

        // Remove item at front
        front = front.next;
        size--;
        return item;
    }

    /**
     * Return the item at the front of the queue without removing it.
     *
     * @return The item at the front of the queue if successful; return null if the queue is empty.
     */
    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }

        return front.data;
    }
}
