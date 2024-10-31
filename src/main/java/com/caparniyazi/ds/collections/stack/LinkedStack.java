package com.caparniyazi.ds.collections.stack;

import java.util.NoSuchElementException;

/**
 * Class to implement interface Stack as a single-linked list.
 */
public class LinkedStack<E> implements Stack<E> {

    // Data fields
    private Node<E> topOfStackRef;  // The reference to the first stack node.

    /**
     * The building-block for a list.
     *
     * @param <E> The data the node references.
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;

        // Constructors
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        public Node(E data) {
            this(data, null);
        }
    }

    /**
     * Insert a new item on top of the stack.
     * topOfStackRef references the new node;
     * topOfStackRef.next() references the old top of the stack.
     *
     * @param obj The item to be inserted.
     * @return The item that was inserted.
     */
    @Override
    public E push(E obj) {
        topOfStackRef = new Node<>(obj, topOfStackRef);
        return obj;
    }

    /**
     * Return the top item on the stack.
     *
     * @return The top item on the stack
     * @throws NoSuchElementException if the stack is empty.
     * @pre The stack is not empty.
     * @post The stack remains unchanged.
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return topOfStackRef.data;
        }
    }

    /**
     * Remove and return the top item on the stack.
     *
     * @return The top item on the stack.
     * @throws java.util.NoSuchElementException if the stack is empty
     * @pre The stack is not empty
     * @post The top item on the stack has been removed the stack is one item smaller.
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            E result = topOfStackRef.data;
            topOfStackRef = topOfStackRef.next;
            return result;
        }
    }

    /**
     * See whether the stack is empty.
     *
     * @return true if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        return topOfStackRef == null;
    }
}
