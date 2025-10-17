package com.caparniyazi.ds.maps;

import java.util.*;

public class TestNavigableMap {
    /**
     * Returns the averages as a list for non-overlapping spans of values in its NavigableMap argument.
     *
     * @param valueMap The map whose values are averaged.
     * @param delta    The number of map values in each span.
     * @return An ArrayList of average values for each span.
     */
    public static List<Double> computeSpans(NavigableMap<Integer, Double> valueMap, int delta) {
        List<Double> result = new ArrayList<>();
        Integer min = valueMap.firstEntry().getKey();
        Integer max = valueMap.lastEntry().getKey();

        for (int i = min; i <= max; i += delta) {
            double avg = computeAverage(valueMap.subMap(i, true, i + delta, false));
            result.add(avg);
        }

        return result;
    }

    /**
     * Returns the average of the numbers in its Map argument.
     *
     * @param valueMap The map whose values are averaged.
     * @return The average of the map values.
     */
    public static double computeAverage(Map<Integer, Double> valueMap) {
        int count = 0;
        double sum = 0.0;

        for (Map.Entry<Integer, Double> entry : valueMap.entrySet()) {
            sum += entry.getValue();
            count++;
        }
        return sum;
    }

    public static void main(String[] args) {
        NavigableMap<Integer, Double> storms = new TreeMap<>();
        storms.put(1960, 10.0);
        storms.put(1961, 5.0);
        storms.put(1962, 20.0);
        storms.put(1963, 8.0);
        storms.put(1964, 16.0);
        storms.put(1965, 50.0);
        storms.put(1966, 25.0);
        storms.put(1967, 15.0);
        storms.put(1968, 21.0);
        storms.put(1969, 13.0);

        List<Double> stormAverage = computeSpans(storms, 2);
        System.out.println(stormAverage);
    }
}

