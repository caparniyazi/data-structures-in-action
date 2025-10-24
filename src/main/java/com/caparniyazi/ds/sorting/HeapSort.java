package com.caparniyazi.ds.sorting;

import java.util.Comparator;

/**
 * Implementation of the heap sort algorithm.
 * <p/>
 * The merge sort algorithm has the virtue that its time is O(n log n), but it still requires,
 * at least temporarily, n extra storage locations. This next algorithm can be implemented
 * without requiring any additional storage. It uses a heap to store the array and so is called
 * heapsort.
 * <p/>
 * Because we have n items to insert and each insert (or remove) is O(log n),
 * the buildHeap operation is O(n * log n).
 * Similarly, we have n items to remove from the heap, so that is also O(n log n).
 * Because we are storing the heap in the original array, no extra storage is required.
 */
public class HeapSort extends SortingAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] a) {
        sort(a, null);
    }

    /**
     * Sort the array using heapsort algorithm.
     *
     * @param a   The array to be sorted.
     * @param c   The comparator.
     * @param <T> The type parameter.
     * @pre Array contains Comparable items.
     * @post Array is sorted.
     */
    @Override
    public <T extends Comparable<T>> void sort(T[] a, Comparator<? super T> c) {
        buildHeap(a, c);
        shrinkHeap(a, c);
    }

    /**
     * Transforms a heap into a sorted array.
     *
     * @param a   The array to be sorted.
     * @param <T> The type parameter.
     * @pre All items in the array are in heap order.
     * @post The array is sorted.
     */
    private <T extends Comparable<T>> void shrinkHeap(T[] a, Comparator<? super T> c) {
        int n = a.length;

        // Invariant: a[0 ... n-1] form a heap.
        // a[n ... a.length-1] is sorted.
        while (n > 0) { // While the heap is not empty.
            n--;
            swap(a, 0, n);
            // a[1 ... n-1] form a heap
            // a[n ... a.length-1] is sorted.
            int parent = 0;

            while (true) {
                int leftChild = 2 * parent + 1;

                if (leftChild >= n) {
                    break; // No more children
                }

                int rightChild = leftChild + 1;
                // Find the larger of the two children.
                int maxChild = leftChild;

                if (rightChild < n // There is a right child.
                        &&
                        compare(a[leftChild], a[rightChild], c) < 0) {
                    maxChild = rightChild;
                }

                // If the parent is smaller than the larger child,
                if (compare(a[parent], a[maxChild], c) < 0) {
                    // Swap the parent and child.
                    swap(a, parent, maxChild);
                    // Continue at the child level.
                    parent = maxChild;
                } else { // Heap property is restored.
                    break; // Exit the loop.
                }
            }
        }
    }

    /**
     * Transfers the array into a heap.
     *
     * @param a   The array to be transformed into a heap.
     * @param <T> The type parameter.
     * @pre The array contains at least one item.
     * @post All items in the array are in heap order.
     */
    private <T extends Comparable<T>> void buildHeap(T[] a, Comparator<? super T> c) {
        int n = 1;

        // Invariant: a[0 ... n-1] is a heap.
        while (n < a.length) {
            n++;    // Add a new item to the heap and reheap.
            int child = n - 1;
            int parent = (child - 1) / 2;

            while (parent >= 0 && compare(a[parent], a[child], c) < 0) {
                swap(a, parent, child);
                child = parent;
                parent = (child - 1) / 2;
            }
        }
    }

    @Override
    public String getName() {
        return "HeapSort";
    }

    public <T extends Comparable<T>> void printTree(T[] a) {
        // Call recursive function to print tree structure
        printTreeRecursive(a, 0, 0);
    }

    // Helper method to print the tree recursively
    private <T extends Comparable<T>> void printTreeRecursive(T[] a, int index, int depth) {
        // Base case: If index is out of bounds, return
        if (index >= a.length) {
            return;
        }

        // Print the current node with indentation based on depth
        printIndentation(depth);
        System.out.println(a[index]);

        // Recursively print the left and right children
        printTreeRecursive(a, 2 * index + 1, depth + 1); // Left child
        printTreeRecursive(a, 2 * index + 2, depth + 1); // Right child
    }

    // Helper method to print indentation for tree depth
    private void printIndentation(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  "); // 2 spaces per level of depth
        }
    }


    public static void main(String[] args) {
        Integer[] a = {89, 76, 74, 37, 32, 39, 66, 20, 26, 18, 28, 29, 6};
        HeapSort app = new HeapSort();
        app.sort(a);
        app.printTree(a);
    }
}
