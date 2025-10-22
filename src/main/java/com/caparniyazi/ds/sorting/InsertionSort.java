package com.caparniyazi.ds.sorting;

import java.util.Comparator;

/**
 * Implements the insertion sort algorithm.
 * <p/>
 * Insertion sort is another quadratic sorting algorithm, and it is based on the technique used by card
 * players to arrange a hand of cards. The player keeps the cards that have been picked up so
 * far in sorted order. When the player picks up a new card, the player makes room for the new
 * card and then inserts it in its proper place.
 * <p/>
 * Insertion sort gives the best performance for larger arrays.
 * Insertion sort is better because it takes advantage of any partial
 * sorting that is in the array and uses less costly shifts instead of exchanges to rearrange
 * array elements.
 * <p/>
 * Since the time to sort an array of n elements is proportional to n^2,
 * none of these algorithms (Selection/Insertion) is particularly good for large arrays (i.e., n > 100).
 * The best sorting algorithms provide (n * log n) average-case behavior and
 * are considerably faster for large arrays.
 * <p/>
 * Both quadratic sorts require storage for the array being sorted.
 * However, there is only one copy of this array, so the array
 * is sorted in place. There are also requirements for variables that store references to particular
 * elements, loop control variables, and temporary variables.
 * However, for large n, the size of the array dominates these other storage considerations,
 * so the extra space usage is proportional to O(1).
 * <p/>
 * Comparison of Rates of Growth:
 * ---------------------+
 * n    n^2      n log n +
 * -----+-------+-------+
 * 8    64      24
 * 16   256     64
 * 32   1024    160
 * 64   4096    384
 * 128  16,384  896
 * 256  65,536  2048
 * 512  262,144 4608
 * <p/>
 * Comparison of Quadratic Sorts:
 * <p/>
 * Number of Comparisons Number of Exchanges
 * <pre>            Number of comparisons   Number of exchanges</pre>
 * <pre>            Best    Worst           Best    Worst      </pre>
 * ------------------------------------------------------------
 * Selection sort   O(n^2)  O(n^2)          O(n)    O(n)
 * Insertion sort   O(n)    O(n^2)          O(1)    O(n^2)
 */
public class InsertionSort extends SortingAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(final T[] a) {
        sort(a, null);
    }

    @Override
    public <T extends Comparable<T>> void sort(T[] a, Comparator<? super T> c) {

        int totalComparisons = 0;
        int totalExchanges = 0;

        for (int i = 1; i < a.length; i++) {
            // Invariant: a[0 ... i-1] is sorted.
            // Insert element at position i in the sorted subarray.
            int comparisons = insert(a, i);
            totalComparisons += comparisons;
            totalExchanges += numExchanges;
            System.out.printf("Pass %d: %d comparisons %d exchanges%n", i, comparisons, numExchanges);
        }

        System.out.println("Total comparisons: " + totalComparisons + " exchanges: " + totalExchanges);
    }

    /**
     * Insert the element at "i" position where it belongs in the array.
     *
     * @param a   The array being sorted.
     * @param i   The position of the element to insert.
     * @param <T> The element type.
     * @pre a[0 ... i-1] is sorted.
     * @post a[0 ... i] is sorted.
     */
    private <T extends Comparable<T>> int insert(T[] a, int i) {
        T nextVal = a[i];   // Element to insert.
        numExchanges = 0;
        int comparisons = 0;

        while (i > 0) {  // Check the next smaller element.
            comparisons++;

            if (nextVal.compareTo(a[i - 1]) < 0) {
                a[i] = a[i - 1];    // Shift down.
                i--;
            } else {
                break;
            }

            numExchanges++;
        }

        // Insert nextVal at i.
        a[i] = nextVal;
        return comparisons;
    }

    @Override
    public String getName() {
        return "Insertion Sort";
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        Integer[] arr = new Integer[]{40, 35, 80, 75, 60, 90, 70, 75, 50, 22};
        insertionSort.sort(arr);
    }
}
