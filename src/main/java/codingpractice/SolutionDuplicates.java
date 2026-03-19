package codingpractice;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SolutionDuplicates {


    // Exptected  minimum.
    // Time: O(n)
    // Space: O(n)
    public static Set<Integer> findDuplicates(List<Integer> list) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();

        for (Integer item : list) {
            if (!seen.add(item)) {
                duplicates.add(item);
            }
        }

        return duplicates;
    }

    public static Set<Integer> findDuplicatesThreadSafe(List<Integer> list) {
        Set<Integer> seen = ConcurrentHashMap.newKeySet();
        Set<Integer> duplicates = ConcurrentHashMap.newKeySet();

        list.parallelStream().forEach(item -> {
            if (!seen.add(item)) {
                duplicates.add(item);
            }
        });

        return duplicates;
    }

    // Frequency map (better insight)
    // Shows Java Streams knowledge.
    // For very large datasets, DB-level solution is better, e.g.,
    // SELECT email, COUNT(*)
    //  FROM customers
    //  GROUP BY email
    //  HAVING COUNT(*) > 1;
    public static Map<Integer, Long> findDuplicatesWithCount(List<Integer> list) {
        return list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<Integer, Long> findDuplicatesWithCountConcurrent(List<Integer> list) {
        return list.parallelStream().collect(Collectors.groupingByConcurrent(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static void main(String[] args) {
        List<Integer> myList = List.of(10, 4, 5, 8, 10, 0, 9, 3, 4);
        System.out.println(findDuplicates(myList));
        System.out.println(findDuplicatesWithCount(myList));
        System.out.println(findDuplicatesWithCountConcurrent(myList));
        System.out.println(findDuplicatesThreadSafe(myList));
    }
}
