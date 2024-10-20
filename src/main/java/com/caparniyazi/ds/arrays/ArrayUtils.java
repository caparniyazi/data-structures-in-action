package com.caparniyazi.ds.arrays;

public class ArrayUtils {
    /**
     * The method that searches an array for a target value.
     *
     * @param array  The array being searched
     * @param target The target value being sought for
     * @return The index if the target is found, otherwise -1.
     * Complexity O(n):
     * If we consider the average over all cases where the target is present,
     * then the loop body will execute x.length/2 times. Therefore, the
     * total execution time is directly proportional to array.length.
     * If we doubled the size of the array,
     * we would expect the time to double.
     */
    public static int search(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }

        // Not found
        return -1;
    }

    /**
     * The method that determines whether two arrays have no common elements.
     *
     * @param x One array
     * @param y The other array
     * @return true if there are no common elements.
     * Complexity O(n*m): The total execution time would be proportional to the
     * product of x.length and y.length.
     */
    public static boolean areDifferent(int[] x, int[] y) {
        for (int target : x) {
            if (search(y, target) != -1) {
                return false;
            }
        }

        return true;
    }

    /**
     * The method that determines whether the contents of an array are all unique.
     *
     * @param x The array
     * @return true if all elements of x are unique.
     * Complexity O(x.length * x.length), O(n*n).
     * This is very inefficient, because we do approximately twice as many tests as necessary.
     * We can rewrite the inner loop as in the following method (areUniqueV2) implementation.
     */
    public static boolean areUnique(int[] x) {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x.length; j++) {

                if (i != j && x[i] == x[j]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * The method that is more efficient in determining whether the contents of an array are all unique.
     *
     * @param x The array
     * @return true if all elements of x are unique.
     * Complexity:
     * The first time, the inner loop will execute x.length−1 times.
     * The second time, it will execute x.length–2 times, and so on. The last time, it will execute just
     * once. The total number of times it will execute is
     * x.length-1 + x.length-2 +. . .+ 2 + 1
     * The series 1 2 3 · · · (n – 1) is a well‐known series that has a value of
     * n * (n -1) / 2  Therefore, this sum is (x.length * x.length - x.length) / 2.
     * O => Order of magnitude is called big-O notation.
     * Typically, in analyzing the running time of algorithms, we use logarithms to the base 2.
     * In general, it is safe to ignore all constants and drop the lower‐order
     * terms when determining the order of magnitude for an algorithm.
     * We use the expression O(1) to represent a constant growth rate. This is a value that doesn’t
     * change with the number of inputs. The simple steps all represent O(1). Any finite number of
     * O(1) steps is still considered O(1).
     */
    public static boolean areUniqueV2(int[] x) {
        for (int i = 0; i < x.length; i++) {
            for (int j = i + 1; j < x.length; j++) {
                if (x[i] == x[j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
