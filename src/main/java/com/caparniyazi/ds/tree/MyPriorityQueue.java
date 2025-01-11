package com.caparniyazi.ds.tree;

import jakarta.annotation.Nonnull;

import java.util.*;

/**
 * The class implements the Queue interface by building a heap in an ArrayList.
 * The heap is structured so that the "smallest" item is at the top.
 *
 * @param <E> The element type.
 */
public class MyPriorityQueue<E> extends AbstractQueue<E> implements Queue<E> {
    // Data fields
    private final ArrayList<E> theData;     // To hold the data.
    private final Comparator<E> comp; // A reference to a comparator object.

    // Constructors

    /**
     * Creates a heap-based priority queue that orders its elements
     * based on their natural ordering.
     */
    @SuppressWarnings("unchecked")
    public MyPriorityQueue() {
        theData = new ArrayList<E>();
        comp = (left, right) -> ((Comparable<E>) left).compareTo(right);
    }

    /**
     * Creates a heap-based priority queue that orders its elements
     * according to the specified Comparator.
     *
     * @param comp The comparator used to order queue elements.
     */
    public MyPriorityQueue(Comparator<E> comp) {
        theData = new ArrayList<E>();
        this.comp = comp;
    }

    /**
     * Creates a heap-based priority queue with the specified initial capacity
     * that orders its elements according to the specified Comparator.
     *
     * @param capacity The initial capacity for this priority queue.
     * @param comp     The comparator used to order queue elements.
     * @throws IllegalArgumentException if capacity is less than 1.
     */
    public MyPriorityQueue(int capacity, Comparator<E> comp) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        theData = new ArrayList<E>(capacity);
        this.comp = comp;
    }

    @Override
    @Nonnull
    public Iterator<E> iterator() {
        return theData.iterator();
    }

    @Override
    public int size() {
        return theData.size();
    }

    /**
     * Inserts an item into the priority queue.
     *
     * @param item The item to be inserted.
     * @return true.
     * @throws NullPointerException if the item to be inserted is null.
     * @pre theData is in heap order.
     * @post The item is in the priority queue and theData is in heap order.
     */
    @Override
    public boolean offer(E item) {
        // Add the item to the heap.
        theData.add(item);
        // child is newly inserted item.
        int child = theData.size() - 1;
        int parent = (child - 1) / 2;   // Find child's parent.

        // Re-heap
        while (parent >= 0 && comp.compare(theData.get(parent), theData.get(child)) > 0) {
            swap(parent, child);
            child = parent;
            parent = (child - 1) / 2;
        }
        return true;
    }

    /**
     * Exchanges the object references at indexes parent, and child.
     *
     * @param parent The parent index of item to be switched.
     * @param child  The child index of item to be switched.
     */
    private void swap(int parent, int child) {
        E first = theData.get(parent);  // Get the first reference
        theData.set(parent, theData.get(child)); // Get the second reference and set it to first.
        theData.set(child, first);  // Set the second reference to first reference.
    }

    /**
     * Remove an item from the priority queue.
     *
     * @return The item with the smallest priority value or null if empty.
     * @pre theData is in heap order.
     * @post Removed the smallest item, theData is in heap order.
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        // Save the top of the heap.
        E result = theData.get(0);
        // If only one item then remove it.

        if (theData.size() == 1) {
            theData.remove(0);
            return result;
        }
        // Remove the last item from the ArrayList and place it into the first position.
        theData.set(0, theData.remove(theData.size() - 1));
        // The parent starts at the top.
        int parent = 0;

        /*
         * The loop is terminated under one of two circumstances:
         * Either the item has moved down the tree so that it has no children,
         * or it is smaller than both its children.
         */
        while (true) {
            int leftChild = 2 * parent + 1;
            if (leftChild >= theData.size()) {
                break; // Out of heap.
            }

            int rightChild = leftChild + 1;
            int minChild = leftChild;   // Assume leftChild is smaller.

            // See whether right child is smaller
            if (rightChild < theData.size() && comp.compare(theData.get(leftChild), theData.get(rightChild)) > 0) {
                minChild = rightChild;
            }

            // Assert: minChild is the index of the smaller child.
            // Move smaller child up heap if necessary.
            if (comp.compare(theData.get(parent), theData.get(minChild)) > 0) {
                swap(parent, minChild);
                parent = minChild;
            } else {    // Heap property is restored.
                break;
            }
        }

        return result;
    }

    /**
     * Returns the smallest entry without removing it.
     * If the queue is empty, returns null.
     *
     * @return The smallest entry without removing it, or null if the queue is empty.
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;    // If there are no objects in the list, returns null.
        }

        return theData.get(0);  // Return the first item.
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    // Code snippet from AbstractQueue.
    @Override
    public E remove() {
        E item = poll();

        if (item == null) {
            throw new NoSuchElementException();
        }
        return item;
    }

    // Method to print the priority queue as a tree
    @SuppressWarnings("unchecked")
    public void printTree() {
        E[] array = (E[]) this.toArray(); // Get the internal array representing the heap

        // Call recursive function to print tree structure
        printTreeRecursive(array, 0, 0);
    }

    // Helper method to print the tree recursively
    private void printTreeRecursive(E[] array, int index, int depth) {
        // Base case: If index is out of bounds, return
        if (index >= array.length) return;

        // Print the current node with indentation based on depth
        printIndentation(depth);
        System.out.println(array[index]);

        // Recursively print the left and right children
        printTreeRecursive(array, 2 * index + 1, depth + 1); // Left child
        printTreeRecursive(array, 2 * index + 2, depth + 1); // Right child
    }

    // Helper method to print indentation for tree depth
    private void printIndentation(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  "); // 2 spaces per level of depth
        }
    }
}
