package codingpractice;

import com.sun.source.tree.BreakTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Example:
 * Input: "abcabcbb" Output: 3 The longest substrings without repeating characters are abc, bca, and cab,
 * all with length 3.
 * <p>
 * Example:
 * Input: "nnnnn" Output: 1 The longest substring without repeating characters is n, with length 1.
 * <p>
 */
public class LongestSubstring {
    public static void main(String[] args) {
        System.out.println("The longest string length: " + getLongestSubstring("Malatya"));
        System.out.println("The longest string length with optimized: " + getLongestSubstringOptimized("Malatya"));
        System.out.println("The longest string length: " + getLongestSubstring("Java"));
        System.out.println("The longest string length with optimized: " + getLongestSubstringOptimized("Java"));
    }

    static String getLongestSubstring(String str) {
        String output = "";
        int result = 0;

        for (int i = 0; i < str.length(); i++) {
            Set<Character> visited = new HashSet<>();
            int j = i;

            for (; j < str.length(); j++) {
                char currChar = str.charAt(j);

                if (visited.contains(currChar)) {
                    break;
                } else {
                    visited.add(currChar);
                    ;
                }
            }

            if (output.length() < j - i + 1) {
                output = str.substring(i, j);
                //System.out.println(visited + " [" + i + ", " + j + "]  => " + output);
                result = Math.max(result, j - i);
            }
        }
        return output;
    }

    static String getLongestSubstringOptimized(String str) {
        Map<Character, Integer> visited = new HashMap<>();
        String output = "";
        int result = 0;

        for (int i = 0, j = 0; j < str.length(); j++) {
            char currChar = str.charAt(j);

            if (visited.containsKey(currChar)) {
                i = Math.max(visited.get(currChar) + 1, i);
            }

            if (output.length() < j - i + 1) {
                output = str.substring(i, j + 1);
            }

            visited.put(currChar, j);
        }
        return output;
    }
}
