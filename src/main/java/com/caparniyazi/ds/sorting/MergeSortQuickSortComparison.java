package com.caparniyazi.ds.sorting;

import java.util.Arrays;
import java.util.Random;

public class MergeSortQuickSortComparison {
    // Data fields
    private static final int SIZE = 1_048_576;

    public static void main(String[] args) {
        final Integer[] data1 = new Integer[SIZE];
        final Integer[] data2 = new Integer[SIZE];
        final Random rand = new Random();

        // Fill arrays with random integers.
        for (int i = 0; i < SIZE; i++) {
            int value = rand.nextInt(SIZE);
            data1[i] = value;
            data2[i] = value;
        }

        System.out.println("Array size: " + SIZE);
        System.out.println("Starting benchmarks...\n");

        // QuickSort.
        long start = System.currentTimeMillis();
        (new QuickSort()).sort(data1);
        long end = System.currentTimeMillis();
        System.out.printf("QuickSort time: %d ms%n", (end - start));

        // MergeSort.
        start = System.currentTimeMillis();
        (new MergeSort()).sort(data2);
        end = System.currentTimeMillis();
        System.out.printf("MergeSort time: %d ms%n", (end - start));

        // Verify equality.
        if (Arrays.equals(data1, data2)) {
            System.out.println("\n✅ Arrays sorted correctly and match.");
        } else {
            System.out.println("\n❌ Arrays differ! Check implementations.");
        }
    }
}
