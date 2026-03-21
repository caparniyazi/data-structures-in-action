package codingpractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given intervals, merge overlapping ones.
 * <p/>
 * input:  [[1,3],[2,6],[8,10],[15,18]]
 * output: [[1,6],[8,10],[15,18]]
 */
public class MergeIntervals {
    public static void main(String[] args) {
        int[][] source = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        List<int[]> result = mergeOverlap(source);

        for (int[] interval : result) {
            System.out.print(Arrays.toString(interval) + " ");
        }
    }
    /**
     * Time: O(n log n) (Sorting)
     * Space: O(n)
     *
     * @param arr The input array
     * @return The merged list of intervals.
     */
    private static List<int[]> mergeOverlap(int[][] arr) {
        // Sort intervals.
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        List<int[]> result = new ArrayList<>();

        // Single pass after sorting.
        for (int[] interval : arr) {
            // Merge or add
            if (result.isEmpty() || result.get(result.size() - 1)[1] < interval[0]) {
                result.add(interval);   // Add
            } else {
                // Overlap -> merge
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], interval[1]);
            }
        }

        return result;
    }
}
