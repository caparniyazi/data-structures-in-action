package codingpractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * We define the following:
 * <p>
 * A subarray of an n-element array is an array is composed of a contiguous block of the original array's elements.
 * For example, if the array = [1, 2, 3],
 * then the subarrays are [1], [2], [3], [1,2], [2,3] and [1,2,3].
 * Something like [1,3] would not be a subarray as it's not a contiguous subsection of the original array.
 * The sum of an array is the total sum of its elements.
 * <pre>
 *      An array's sum is negative if the total sum of its elements is negative.
 *      An array's sum is positive if the total sum of its elements is positive.
 *  </pre>
 * Given an array of n integers, find and print its number of negative subarrays on a new line.
 * <p>
 * Input Format
 * <p>
 * The first line contains a single integer, n, denoting the length of array a = [a0, a1, a2, ..., an-1].
 * The second line contains n space-separated integers describing each respective element, a[i], in array A.
 * <p>
 * Constraints
 * <p>
 * Output Format
 * <p>
 * Print the number of subarrays of A having negative sums.
 * <p>
 * Sample Input:
 *
 * <pre>
 *      5
 *      1 -2 4 -5 1
 *  </pre>
 * Sample Output:
 * <p>
 * 9
 */
public class SolutionSubArray {
    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(bufferedReader.readLine().replaceAll("\\s+$", ""));

            List<Integer> list = Arrays.stream(bufferedReader.readLine().replaceAll("\\s+$", "")
                    .split(" ")).map(Integer::parseInt).collect(toList());
            int count = 0;  // The number of subarrays of A having negative sums.
            int i = 1;  // The i element sub-array.

            while (i <= n) {
                for (int j = 0; j < n; j++) {

                    if (j + i <= n) {
                        List<Integer> temp = list.subList(j, j + i);

                        if (temp.stream().mapToInt(Integer::intValue).sum() < 0) {
                            count++;
                        }
                    }
                }
                i++;
            }

            System.out.println(count);
        }
    }
}
