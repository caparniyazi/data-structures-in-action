package com.caparniyazi.ds.collections.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class that implements a stack as an adapter to the List.
 * <p/>
 * The easiest approach to implementing a stack in Java would be to give it a List component
 * for storing the data. Since all insertions and deletions are at one end, the stack operations
 * would all be O(1) operations. You could use an object of any class that implements the List
 * interface to store the stack data, but the ArrayList is the simplest.
 *
 * @param <E> The type of elements in the stack.
 */
public class ListStack<E> implements Stack<E> {
    // Data fields
    private List<E> theData;    // The list containing the data.

    // Constructors

    /**
     * Construct an empty stack using an ArrayList as the container.
     */
    public ListStack() {
        theData = new ArrayList<>();
    }

    /**
     * Push an object onto the stack.
     *
     * @param obj The object to be pushed.
     * @return The object pushed.
     * @post The object is at the top of the stack.
     */
    @Override
    public E push(E obj) {
        theData.add(obj);
        return obj;
    }

    /**
     * Peek at the top object on the stack.
     *
     * @return The top object on the stack
     * @throws java.util.NoSuchElementException if the stack is empty.
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return theData.get(theData.size() - 1);
    }

    /**
     * Pop the top object off the stack.
     *
     * @return The top object, which is removed.
     * @throws NoSuchElementException if the stack is empty.
     * @post The object at the top of the stack is removed.
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return theData.remove(theData.size() - 1);
    }

    /**
     * See whether the stack is empty.
     *
     * @return true if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        return theData.isEmpty();
    }
}
