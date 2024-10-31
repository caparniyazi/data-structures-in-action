package com.caparniyazi.ds.collections.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Class that implements Stack interface using an array.
 *
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {
    // Data fields
    E[] theData;
    int topOfStack = -1;    // Index to the top of the stack.
    private static final int INITIAL_CAPACITY = 10; // initial capacity
    private int capacity = INITIAL_CAPACITY;

    // Constructors
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        theData = (E[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Invalid initial capacity: " + capacity);
        }

        this.capacity = capacity;
        theData = (E[]) new Object[capacity];
    }

    /**
     * Insert a new item on top of the stack.
     *
     * @param obj The item to be inserted.
     * @return The item that was inserted.
     * @post The  new item is the top item on the stack.
     * All other items are one position lower.
     */
    @Override
    public E push(E obj) {
        // Reallocate additional storage space when the array becomes filled.
        if (topOfStack == theData.length - 1) {
            reallocate();
        }
        theData[++topOfStack] = obj;
        return obj;
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
     * Peek at the top object on the stack.
     *
     * @return The top object on the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return theData[topOfStack];
    }

    /**
     * Remove and return the top item on the stack.
     *
     * @return The top item on the stack
     * @throws EmptyStackException if the stack is empty.
     * @pre The stack is not empty.
     * @post The top item on the stack has been removed and the stack is one item smaller.
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return theData[topOfStack--];
    }

    /**
     * See whether the stack is empty.
     *
     * @return true if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        return topOfStack == -1;
    }
}
