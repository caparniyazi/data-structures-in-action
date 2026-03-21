package codingpractice;

import java.util.*;

/**
 * Problem: Finding the duplicates and their counts with Java:
 * <p/>
 * input:  [1,2,3,2,4,5,1]
 * output: [1,2]
 * <p>
 * Time: O(n)
 * Space: O(n)
 */
public class FindAllDuplicates {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 2, 4, 5, 1);
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        // To count duplicates
        Map<Integer, Integer> map = new HashMap<>();

        for (Integer item : list) {
            map.put(item, map.getOrDefault(item, 0) + 1);

            if (!seen.add(item)) {
                duplicates.add(item);
            }
        }

        System.out.println(duplicates);
        System.out.println(map);
    }
}
