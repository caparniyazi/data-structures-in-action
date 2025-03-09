package com.caparniyazi.ds.codility;

/**
 * A non-empty array A consisting of N integers is given.
 * <p>
 * Array A represents a linked list. A list is constructed from this array as follows:
 * <p>
 * the first node (the head) is located at index 0;
 * the value of a node located at index K is A[K];
 * if the value of a node is −1 then it is the last node of the list;
 * otherwise, the successor of a node located at index K is located at index A[K] (you can assume that A[K] is a valid index, that is 0 ≤ A[K] < N).
 * For example, for array A such that:
 * <p>
 * A[0] = 1
 * A[1] = 4
 * A[2] = -1
 * A[3] = 3
 * A[4] = 2
 * the following list is constructed:
 * <p>
 * the first node (the head) is located at index 0 and has a value of 1;
 * the second node is located at index 1 and has a value of 4;
 * the third node is located at index 4 and has a value of 2;
 * the fourth node is located at index 2 and has a value of −1.
 * Write a function:
 * <p>
 * class Solution { public int solution (int[] A); }
 * <p>
 * that, given a non-empty array A[] consisting of N integers, returns the length of the list constructed from A[] in the above manner.
 * <p>
 * For example, given array A such that:
 * <p>
 * A[0] = 1
 * A[1] = 4
 * A[2] = -1
 * A[3] = 3
 * A[4] = 2
 * the function should return 4, as explained in the example above.
 * <p>
 * Assume that:
 * <p>
 * N is an integer within the range [1..200,000];
 * each element of array A is an integer within the range [−1..N-1];
 * it will always be possible to construct the list and its length will be finite.
 */
public class ArrListLenSolution {
    public static void main(String[] args) {
        int[] A = {1, 4, -1, 3, 2};
        int[] B = {-1};
        int[] C = {2, -1, 1, 0, 0, 0};
        System.out.println(new ArrListLenSolution().solution(A));
        System.out.println(new ArrListLenSolution().solution(B));
        System.out.println(new ArrListLenSolution().solution(C));
    }

    public int solution(int[] A) {
        int len = 1;
        int i = 0;

        while (true) {
            int newIndex = A[i];
            if (newIndex == -1) {
                break;
            } else {
                len++;
                i = newIndex;
            }
        }
        return len;
    }
}
