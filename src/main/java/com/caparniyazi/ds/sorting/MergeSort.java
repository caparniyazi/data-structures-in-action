package com.caparniyazi.ds.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Implements the recursive merge sort algorithm.
 * <p/>
 * The effort to do each merge is O(n). The number of lines that require
 * merging is (log n) because each recursive step splits the array in half.
 * So the total effort to reconstruct the sorted array through merging is O(n log n).
 */
public class MergeSort extends SortingAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] a) {
        sort(a, null);
    }

    @Override
    public <T extends Comparable<T>> void sort(T[] a, Comparator<? super T> c) {
        // An array with one element is sorted already.

        if (a.length > 1) {
            // Split the array into halves.
            int halfSize = a.length / 2;
            T[] leftTable = Arrays.copyOf(a, halfSize);
            T[] rightTable = Arrays.copyOfRange(a, halfSize, a.length);

            // Sort the halves.
            sort(leftTable, c);
            sort(rightTable, c);

            // Merge the halves.
            merge(a, 0, leftTable, rightTable, c);
        }
    }

    /**
     * Merge two sequences.
     * <p/>
     * For two input sequences that contain a total of n elements, we need to move each element
     * from its input sequence to its output sequence, so the time required for a merge is O(n).
     *
     * @param outputSeq is the merged result and is sorted.
     * @param dest      Starting position in the output sequence.
     * @param leftSeq   The left input.
     * @param rightSeq  The right input.
     * @param <T>       The type parameter.
     */
    private <T extends Comparable<T>> void merge(T[] outputSeq, int dest, T[] leftSeq, T[] rightSeq, Comparator<? super T> c) {
        int i = dest;   // Index into the output sequence, normally it is 0.
        int j = 0;  // Index to the left sequence.
        int k = 0;  // Index to the right sequence.

        while (i < leftSeq.length && j < rightSeq.length) {
            // Find the smaller and insert it into the output sequence.
            if (compare(leftSeq[i], rightSeq[j], c) < 0) {
                outputSeq[k++] = leftSeq[i++];
            } else {
                outputSeq[k++] = rightSeq[j++];
            }
        }
        // Assert: One of the sequences has more items to copy.
        // Copy the remaining input from the left sequence into the output.
        while (i < leftSeq.length) {
            outputSeq[k++] = leftSeq[i++];
        }

        // Copy the remaining input from the right sequence into the output.
        while (j < rightSeq.length) {
            outputSeq[k++] = rightSeq[j++];
        }
    }

    @Override
    public String getName() {
        return "Merge Sort";
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{40, 35, 80, 75, 60, 90, 70, 75, 55, 90, 85, 34, 45, 62, 57, 65};
        new MergeSort().sort(a);
        System.out.println(Arrays.toString(a));
    }
}
