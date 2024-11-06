package com.caparniyazi.ds.recursion;

import java.util.Scanner;

/**
 * Class that solves Towers of Hanoi problem.
 * Constraints:
 * 1. Only the top disk on a peg can be moved to another peg.
 * 2. A larger disk cannot be placed on top of a smaller disk.
 * <p/>
 * Problem Inputs:
 * Number of disks (an integer n)
 * Letter of starting peg: L(left), M (middle), R (right)
 * Letter of destination peg: L,M, or R, but different from starting peg.
 * Letter of temporary peg: L,M, or R, but different from starting peg and destination peg.
 */
public class TowersOfHanoi {
    // Methods

    /**
     * Recursive method for "moving" disks.
     *
     * @param n        The number of disks
     * @param startPeg The starting peg.
     * @param destPeg  The destination peg.
     * @param tempPeg  The temporary peg.
     * @return A string with all the required disk moves.
     * @pre startPeg, destPeg, tempPeg are different.
     */
    public static String showMoves(int n, char startPeg, char destPeg, char tempPeg) {
        /*
         Solution:
         1. Move the top two disks from peg temp to temp
         2. Move the bottom disk from start peg to destination.
         3. Move the top two disks from temp to destination.

         */
        if (n == 1) {   // disk 1
            return "Move disk 1 from peg " + startPeg + " to peg " + destPeg + "\n";
        } else {    // Recursive step
            return showMoves(n - 1, startPeg, tempPeg, destPeg) +
                    "Move disk " + n + " from peg " + startPeg + " to peg " + destPeg + "\n"
                    + showMoves(n - 1, tempPeg, destPeg, startPeg);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of disks: ");
        int numberOfDisks = in.nextInt();
        String moves = showMoves(numberOfDisks, 'L', 'R', 'M');
        System.out.println(moves);
    }
}
