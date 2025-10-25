package com.caparniyazi.ds.sorting;

import java.util.Comparator;

/**
 * Implements the quicksort algorithm.
 * This algorithm was developed by C. A. R. Hoare in 1962.
 * <p/>
 * If the pivot value is a random value selected from the current subarray, then statistically, it
 * is expected that half of the items in the subarray will be less than the pivot and half will be
 * greater than the pivot. If both subarrays always have the same number of elements (the best
 * case), there will be log n levels of recursion. At each level, the partitioning process involves
 * moving every element into its correct partition, so quicksort is O(n log n), just like merge
 * sort.
 * <p/>
 * The quicksort is O(n^2) when each split yields one empty subarray. Unfortunately,
 * that would be the case if the array was sorted. So the worst possible performance
 * occurs for a sorted array, which is not very desirable.
 * <p/>
 * A better solution is to pick the pivot value in a way that is less likely to lead to a bad
 * split. One approach is to examine the first, middle, and last elements in the array and
 * select the median of these three values as the pivot.
 * We can do this by sorting the three-element subarray. After sorting, the smallest of the
 * three values is in position first, the median is in a position middle, and the largest is in
 * the position last.
 * <p/>
 * At this point, we can exchange the first element with the middle element (the median) and
 * use the partition algorithm shown earlier, which uses the first element (now the median)
 * as the pivot value. When we exit the partitioning loop, a[first] and a[down] are
 * exchanged, moving the pivot value where it belongs (back to the middle position).
 * <p/>
 * Algorithm	Best Case	Average Case	Worst Case	Space Complexity	        Stable?
 * QuickSort	O(n log n)	O(n log n)	    O(n²)	    O(log n) (recursive stack)	❌ No
 * MergeSort	O(n log n)	O(n log n)	    O(n log n)	O(n) (extra array)          ✅ Yes
 */


public class QuickSort extends SortingAlgorithm {


    @Override
    public <T extends Comparable<T>> void sort(T[] a) {
        sort(a, null);
    }

    /**
     * Sorts the array using the quicksort algorithm.
     * Entry method.
     *
     * @param a   The array to be sorted.
     * @param c   The Comparator object.
     * @param <T> The type parameter.
     * @pre The array contains the Comparable objects.
     * @post The array is sorted.
     */
    @Override
    public <T extends Comparable<T>> void sort(T[] a, Comparator<? super T> c) {
        // Sort the whole array.
        quickSort(a, 0, a.length - 1);
    }

    /**
     * Sorts a part of the array using the quicksort algorithm.
     * Recursive helper.
     *
     * @param a     The array to be sorted.
     * @param first The index of the low bound.
     * @param last  The index of the last bound.
     * @param <T>   The type parameter.
     * @post The part of the array from "first" through "last" is sorted.
     */
    private <T extends Comparable<T>> void quickSort(T[] a, int first, int last) {
        if (first < last) { //  There is data to be sorted.
            // Partition the table.
            int pivotIndex = partition(a, first, last);

            // Sort the left half.
            quickSort(a, first, pivotIndex - 1);
            // Sort the right half.
            quickSort(a, pivotIndex + 1, last);
        }
    }

    private <T extends Comparable<T>> void bubbleSort(T[] a, int first, int last) {
        int middle = first + (last - first) / 2;

        if (a[middle].compareTo(a[first]) < 0) {
            swap(a, first, middle);
        }

        // Assert a[first] <= a[middle]
        if (a[last].compareTo(a[middle]) < 0) {
            swap(a, middle, last);
        }

        // Assert: a[last] is the largest value of the three.
        if (a[middle].compareTo(a[first]) < 0) {
            swap(a, first, middle);
        }
        // Assert: a[first] <= a[middle] <= a[last]
    }

    /**
     * Partitions the table so that values from first to pivotIndex are less than or equal to
     * the pivot value, and values from pivotIndex to last are greater than the pivot value.
     *
     * @param a     The array to be partitioned.
     * @param first The index of the low bound.
     * @param last  The index of high bound.
     * @param <T>   The type parameter.
     * @return The location of the pivot value(pivot index).
     */
    private <T extends Comparable<T>> int partition(T[] a, int first, int last) {
        bubbleSort(a, first, last);  // Sort a[first], a[middle], and a[last]
        // Move the median value (the pivot value) to a[first] by exchanging a[first] and a[middle].
        swap(a, first, first + (last - first) / 2);

        // Select the first item as the pivot value.
        T pivot = a[first];
        int up = first;
        int down = last;

        do {
            while ((up < last) && (pivot.compareTo(a[up]) >= 0)) {
                up++;
            }
            // Assert: up equals last or a[up] > pivot

            while (pivot.compareTo(a[down]) < 0) {
                down--;
            }
            // Assert: down equals last or a[down] <= pivot
            if (up < down) {    // If up is to the left of down.
                // Exchange a[up] and a[down]
                swap(a, up, down);
            }
        } while (up < down);    // Repeat while up is left of down.

        // Exchange a[first] and a[down] thus putting the pivot value where it belongs.
        swap(a, first, down);
        return down;    // Returns the index of the pivot value.
    }

    @Override
    public String getName() {
        return "QuickSort";
    }
}
