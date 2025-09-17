package com.caparniyazi.ds.tree;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A generic binary heap (min-heap) implemented as an ArrayList.
 * Space complexity O(n) for storing n elements.
 * We are using an ArrayList internally, so resizing is amortized O(1) per insertion.
 * Time complexity:
 * Insert: O(log(n)) New elem is placed at the end of the list, and then bubble up is performed.
 * Peek: O(1) Min elem is always at index zero (root).
 * Remove: O(log(n)) Root elem is removed, last elem moved to root, and then bubble down performed.
 * Build heap: O(nlog(n)) Each insert is log(n), and there are n inserts.
 * Search O(n) Heap does not implement full ordering, so you must scan.
 * <p/>
 * Since heap is a complete binary tree:
 * All levels are filled except possibly the last.
 * The last level is filled from left to right.
 * <p/>
 * The height of the heap = the number of edges on the longest path from the root to a leaf.
 * The height of heap with n nodes = log2(n).
 * * @param <E> The element type.
 */
public class Heap<E extends Comparable<? super E>> {
    // Data fields
    private final ArrayList<E> theData; // The ArrayList to hold the data.

    // Constructors
    public Heap() {
        theData = new ArrayList<>();
    }

    /**
     * Insert the new element to the end of the ArrayList.
     * Place each item initially at the bottom row of the heap and
     * then move it up until it reaches the position where it belongs.
     * <p/>
     * Method insert traces a path from a leaf to the root.
     *
     * @param item The item to be inserted.
     */
    public void insert(E item) {
        // Insert the new item in the next position at the bottom of the heap.
        theData.add(item);
        bubbleUp();
    }

    /**
     * Removing an item from a Heap is always from the top.
     * The top item is first replaced with the last item in the heap (LIH)
     * so that the heap remains a complete tree. If you used any other value,
     * there would be "hole" in the tree where that value used to be.
     * Then the new item at the top is moved down the heap until
     * it is in its proper position.
     * <p/>
     * Method remove traces a path from the root to a leaf.
     *
     * @return Remove and return the min. element in the tree.
     * @throws NoSuchElementException if the heap is empty.
     */
    public E remove() {
        if (theData.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        E root = this.theData.get(0);   // Get the first item.
        E last = this.theData.remove(theData.size() - 1);   // Remove the last element.
        this.theData.set(0, last); // and replace the first item with the last item.

        int parent = 0;
        /*
         * The loop is terminated under one of two circumstances:
         * Either the item has moved down the tree so that it has no children,
         * or it is smaller than both its children.
         */
        while (true) {
            int leftChild = parent * 2 + 1;

            if (leftChild >= theData.size()) {
                break;  // Out of heap
            }
            int rightChild = leftChild + 1;
            int minChild = leftChild;

            // See whether the right child is smaller.
            if (rightChild < theData.size() && theData.get(rightChild).compareTo(theData.get(minChild)) < 0) {
                minChild = rightChild;
            }

            // Assert: minChild is the index of the smaller child.
            // Move smaller child up heap if necessary.
            if (theData.get(parent).compareTo(theData.get(minChild)) > 0) {
                swap(parent, minChild);
                parent = minChild;
            } else {
                // The Heap is restored, and the next smallest item can be removed from the heap.
                break;
            }
        }

        return root;
    }

    private void bubbleUp() {
        int child = theData.size() - 1; // Child is the new inserted item.
        int parent = (child - 1) / 2;   // Find the child's parent.

        // While new item is not at the root, and new item is smaller than its parent
        while (parent >= 0 && theData.get(parent).compareTo(theData.get(child)) > 0) {
            swap(parent, child);    // Swap the new item with its parent, moving the new item up the heap.
            child = parent;
            parent = (child - 1) / 2;
        }
    }

    /**
     * Exchanges the object references at index parent, and child.
     *
     * @param parent The parent index of item to be switched.
     * @param child  The child index of item to be switched.
     */
    private void swap(int parent, int child) {
        E first = theData.get(parent);  // Get the first reference
        theData.set(parent, theData.get(child)); // Get the second reference and set it to first.
        theData.set(child, first);  // Set the second reference to first reference.
    }

    public void printTree() {
        // Call recursive function to print tree structure
        printTreeRecursive(0, 0);
    }

    // Helper method to print the tree recursively
    private void printTreeRecursive(int index, int depth) {
        // Base case: If index is out of bounds, return
        if (index >= theData.size()) {
            return;
        }

        // Print the current node with indentation based on depth
        printIndentation(depth);
        System.out.println(this.theData.get(index));

        // Recursively print the left and right children
        printTreeRecursive(2 * index + 1, depth + 1); // Left child
        printTreeRecursive(2 * index + 2, depth + 1); // Right child
    }

    // Helper method to print indentation for tree depth
    private void printIndentation(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  "); // 2 spaces per level of depth
        }
    }

    public int size() {
        return theData.size();
    }

    public boolean isEmpty() {
        return theData.isEmpty();
    }

    /**
     * Get the minimum element without removing.
     *
     * @return The min element without removing.
     */
    public E peek() {
        if (theData.isEmpty()) {
            return null;
        }

        return theData.get(0);
    }
}
