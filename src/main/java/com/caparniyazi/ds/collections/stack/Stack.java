package com.caparniyazi.ds.collections.stack;

/**
 * A stack is a data structure in which objects are inserted into
 * and removed from the same end(LIFO).
 *
 * @param <E> The element type.
 */
public interface Stack<E> {
    /**
     * Pushes an item onto the top of the stack and returns the item pushed.
     *
     * @param obj The object to be inserted.
     * @return The object inserted.
     */
    E push(E obj);

    /**
     * Returns the object at the top of the stack without removing it.
     *
     * @return The object at the top of the stack.
     * @throws java.util.NoSuchElementException if stack is empty.
     * @post The stack remains unchanged.
     */
    E peek();

    /**
     * Returns the object at the top of the stack and removes it.
     *
     * @return The object at the top of the stack
     * @throws java.util.NoSuchElementException is stack is empty.
     * @post The stack is one item smaller.
     */
    E pop();

    /**
     * Returns true if the stack is empty; otherwise returns false.
     *
     * @return true if the stack is empty, false otherwise.
     */
    boolean isEmpty();
}
