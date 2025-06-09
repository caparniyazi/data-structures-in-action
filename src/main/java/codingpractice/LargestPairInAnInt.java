package codingpractice;

import java.util.Scanner;

/**
 * Find the largest Pair in a given integer.
 */
public class LargestPairInAnInt {
    public static int findTheLargestPair(String str) {
        var max = 0;

        for (int i = 0; i < str.length() - 1; i++) {
            int d1 = str.charAt(i) - '0';
            int d2 = str.charAt(i + 1) - '0';

            max = Math.max(max, 10 * d1 + d2);
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an integer: ");
        System.out.println(findTheLargestPair(scanner.nextLine()));
    }
}
