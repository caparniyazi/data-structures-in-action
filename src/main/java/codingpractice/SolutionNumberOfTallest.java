package codingpractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 */
public class SolutionNumberOfTallest {
    /**
     * The method to return the total count of the highest numbers for the specified list.
     *
     * @param list The input list.
     * @return The total count of the highest numbers for the specified list.
     * @pre 1 <= list(i) <= 10^7
     */
    public static int numberOfTallest(List<Integer> list) {
        Map<Integer, List<Integer>> map = list.stream().collect(Collectors.groupingBy(i -> i));
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>((i1, i2) -> i2-i1);
        treeMap.putAll(map);
        int firstKey = treeMap.keySet().iterator().next();
        return treeMap.get(firstKey).size();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> list = Stream.of(br.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            int result = numberOfTallest(list);
            System.out.println(result);
        }
    }
}
