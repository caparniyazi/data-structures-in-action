package com.caparniyazi.ds.sorting;

import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Driver program to test sorting methods.
 * <p/>
 *                  Best        Average     Worst
 * Selection sort   O(n^2)       O(n^2)       O(n^2)
 * Insertion sort   O(n)        O(n^2)       O(n^2)
 * Tim sort         O(n)        O(n log n)  O(n log n)
 * Shell sort       O(n^7/6)    O(n5/4)     O(n^2)
 * Merge sort       O(n log n)  O(n log n)  O(n log n)
 * Heapsort         O(n log n)  O(n log n)  O(n log n)
 * Quicksort        O(n log n)  O(n log n)  O(n^2)
 */
public class SortComparisonDriver {
    public static void main(String[] args) {
        int size = Integer.parseInt(JOptionPane.showInputDialog("Enter array size: "));
        Integer[] items = new Integer[size];
        Integer[] copy = new Integer[size];
        Random rand = new Random();

        // Fill the arrays.
        for (int i = 0; i < size; i++) {
            items[i] = rand.nextInt();
            copy[i] = items[i];
        }

        // Sort with utility method.
        long start = System.currentTimeMillis();
        Arrays.sort(items);
        System.out.println("Utility sort time is: " + (System.currentTimeMillis() - start) + " ms.");
        System.out.println("Utility sort successful (true/false): " + verify(items));

        // Reload array items from array copy.
        items = Arrays.copyOf(copy, size);

        // Sort with quicksort.
        start = System.currentTimeMillis();
        (new QuickSort()).sort(items);
        System.out.println("QuickSort time is: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("QuickSort successful (true/false): " + verify(items));

        // Sort with heapsort.
        start = System.currentTimeMillis();
        (new HeapSort()).sort(items);
        System.out.println("HeapSort time is: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println("HeapSort successful (true/false): " + verify(items));
    }

    /**
     * Verifies that the elements in the array are in increasing order.
     *
     * @param items The array to verify.
     * @return true if the elements are in increasing order; false if not.
     */
    static boolean verify(Integer[] items) {
        boolean ok = true;
        int i = 0;

        while (ok && i < items.length - 1) {
            ok = items[i].compareTo(items[i + 1]) <= 0;
            i++;
        }

        return ok;
    }
}
