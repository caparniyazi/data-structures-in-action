package com.caparniyazi.ds.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Implements a Selection sort algorithm.
 * <p/>
 * The following series computes the number of executions:
 * <p>
 * (n-1) + (n-2) + ... + 3 + 2 + 1.
 * Which can be written as (n-1) * n / 2 = n^2/2 - n/2.
 * So the number of comparisons is O(n^2) and the number of exchanges is O(n).
 * Because the number of comparisons increases with the square of n,
 * the Selection Sort is called a quadratic sort.
 */
public class SelectionSort extends SortingAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] a) {
        sort(a, null);
    }

    public <T extends Comparable<T>> void sort(final T[] a, Comparator<? super T> c) {
        int n = a.length;
        System.out.println(Arrays.toString(a) + " [Initial]");

        for (int i = 0; i < n - 1; i++) {

            // Invariant: a[0 ... i-1] is sorted.
            int min = i;
            for (int j = i + 1; j < n; j++) {

                // Invariant: a[min] is the smallest item in a[i ... j-1]
                if (compare(a[j], a[min], c) < 0) {
                    min = j;
                }
            }

            // Assert: a[min] is the smallest item in a[i ... n-1]
            // Exchange a[i] with a[min]
            if (i != min) {
                swap(a, i, min);
            }

            System.out.println(Arrays.toString(a));
        }
        // Assert: a[0 ... n-1] is sorted.
        System.out.printf("Number of comparisons: %d %n", numComparisons);
        System.out.printf("Number of exchanges: %d %n", numExchanges);
    }

    @Override
    public String getName() {
        return "Selection Sort";
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(new Integer[]{40, 35, 80, 75, 60, 90, 70, 75, 50, 22});
        selectionSort.sort(new Integer[]{36, 42, 75, 83, 27});
        selectionSort.sort(new Integer[]{40, 35, 80, 75, 60, 90, 70, 75, 50, 22}, (n1, n2) -> n2 - n1);
    }
}
