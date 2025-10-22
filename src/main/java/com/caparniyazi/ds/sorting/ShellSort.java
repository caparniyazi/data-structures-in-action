package com.caparniyazi.ds.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Implements the Shell sort algorithm. It's a generalization of Insertion sort.
 * <p/>
 * Shell sort is an improvement over regular insertion sort, because it
 * ends with an insertion sort of the entire array.
 * Each later sort (including the last one) will be performed on an array
 * whose elements have been presorted by the earlier sorts.
 * Because the behavior of insertion sort is closer to O(n) than O(n^2),
 * when an array is nearly sorted, the presorting will make the later sorts,
 * which involve larger subarrays, go more quickly.
 * <p/>
 * It is known that Shell sort is O(n^2) if successive powers of 2 are used for gap (i.e., 32, 16, 8, 4, 2, 1).
 * If successive values for a gap are of the form 2^k − 1 (i.e., 31, 15, 7, 3, 1), however,
 * it can be proven that the performance is O(n^3/2). This sequence is known as Hibbard’s sequence.
 */
public class ShellSort extends SortingAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] a) {
        sort(a, null);
    }

    /**
     * Sort the array using Shell sort algorithm.
     *
     * @param a   The array to be sorted.
     * @param c   The comparator.
     * @param <T> The type parameter.
     * @pre Array a[] contains Comparable objects.
     * @post Array is sorted.
     */
    @Override
    public <T extends Comparable<T>> void sort(T[] a, Comparator<? super T> c) {
        int gap = a.length / 2; // Set the initial value of the gap (gap between adjacent elements).

        while (gap > 0) {
            for (int i = gap; i < a.length; i++) {
                // Insert element at "i" in its subarray.
                insert(a, i, gap);
            }

            // Reset "gap" for the next pass.
            if (gap == 2) {
                gap = 1;
            } else {
                gap = (int) (gap / 2.2);    // 2.2 is chosen by experimentation.
            }
        }

        System.out.printf("Total: %d comparisons, %d shifts%n", numComparisons, numExchanges);
    }

    /**
     * Inserts the element at "i" where it belongs in the array.
     *
     * @param a   The array being sorted.
     * @param i   The position of element to insert.
     * @param gap The gap between elements in the array.
     * @param <T> The type parameter.
     * @pre Elements through i-gap in subarray are sorted.
     * @post Elements through i in subarray are sorted.
     */
    private <T extends Comparable<T>> void insert(T[] a, int i, int gap) {
        T nextValue = a[i]; // Element to insert.

        // Shift all values > nextVal in subarray down by gap.
        while ((i > gap - 1) && nextValue.compareTo(a[i - gap]) < 0) {
            numComparisons++;
            // The First element not shifted.
            a[i] = a[i - gap];  // Shift down.
            numExchanges++;
            i -= gap;   // Check the next position in subarray.
        }

        a[i] = nextValue;   // Insert nextVal.
    }

    @Override
    public String getName() {
        return "Shell Sort";
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{40, 35, 80, 75, 60, 90, 70, 75, 55, 90, 85, 34, 45, 62, 57, 65};
        new ShellSort().sort(a);
        System.out.println(Arrays.toString(a));
    }
}
