package codingpractice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * The class to find the smallest k-th elements in a given array.
 * Note that the elements in the array can be in any order.
 */
public class Solution {
    public static void main(String[] args) {
        Integer[] integers = {1, 44, 17, 23, 25, 34, 100};
        System.out.println("Original array: ");
        System.out.println(Arrays.toString(integers));

        System.out.println("Enter a value 0 < k < " + integers.length + ": ");
        int k = new Scanner(System.in).nextInt();
        Arrays.sort(integers);
        System.out.println("Sorted array: " + Arrays.toString(integers));
        System.out.println(k + " smallest k-element of the array: ");
        for (int i = 0; i < k; i++) {
            System.out.print(integers[i] + " ");
        }
    }
}
