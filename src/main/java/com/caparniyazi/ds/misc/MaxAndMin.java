package com.caparniyazi.ds.misc;

import java.util.Scanner;

/**
 * Class that contains two methods.One method returns the max & the other returns the min of the three numbers.
 * And we re-use the same methods for the five parameters version.
 */
public class MaxAndMin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter five numbers: ");
        int number1 = in.nextInt();
        int number2 = in.nextInt();
        int number3 = in.nextInt();
        int number4 = in.nextInt();
        int number5 = in.nextInt();

        int maxOf = maxOf(number1, number2, number3);
        maxOf = maxOf(maxOf, number4, number5);
        System.out.println("Maximum number is: " + maxOf);

        int minOf = minOf(number1, number2, number3);
        minOf = minOf(minOf, number4, number5);
        System.out.println("Minimum number is: " + minOf);
    }

    private static int maxOf(int number1, int number2, int number3) {
        return Math.max(Math.max(number1, number2), number3);
    }

    private static int minOf(int number1, int number2, int number3) {
        return Math.min(Math.min(number1, number2), number3);
    }
}
