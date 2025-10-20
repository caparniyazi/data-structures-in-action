package com.caparniyazi.ds.sorting;

import lombok.Getter;

import java.util.Comparator;

@Getter
public abstract class SortingAlgorithm {
    int numComparisons;
    int numExchanges;

    public abstract <T extends Comparable<T>> void sort(final T[] a);

    public abstract <T extends Comparable<T>> void sort(final T[] a, Comparator<? super T> c);

    public abstract String getName();

    public <T extends Comparable<T>> void swap(T[] a, int i, int j) {
        numExchanges++;

        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public <T extends Comparable<T>> int compare(T a, T b, Comparator<? super T> c) {
        numComparisons++;

        if (c == null) {
            return a.compareTo(b);
        } else {
            return c.compare(a, b);
        }
    }

    void reset() {
        numComparisons = 0;
        numExchanges = 0;
    }
}
