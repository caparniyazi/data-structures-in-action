package codingpractice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array and target, find two indices whose values sum to target.
 * <p/>
 * input:  [2,7,11,15], target = 9
 * output: [0,1]
 * <p>
 * Time: O(n).
 * Space: O(n).
 */
public class TwoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum()));
    }

    private static int[] twoSum() {
        int[] arr = new int[]{2, 7, 11, 15};
        int target = 9;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            Integer complementIndex = map.get(target - arr[i]);

            if (complementIndex != null) {
                return new int[]{complementIndex, i};
            } else {
                map.put(arr[i], i);
            }
        }

        return new int[]{-1, -1};
    }
}
