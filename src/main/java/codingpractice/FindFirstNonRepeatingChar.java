package codingpractice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Time: O(n)
 * Space: O(n)
 */
public class FindFirstNonRepeatingChar {
    // Output is: b
    public static void main(String[] args) {
        String input =  "aabccdeff";
        Map<Character, Integer> charMap = new LinkedHashMap<>();

        for(char ch : input.toCharArray()) {
            charMap.put(ch, charMap.getOrDefault(ch, 0) + 1);
        }

        charMap.entrySet().stream()
                .filter(e->e.getValue()==1)
                .findFirst().ifPresent(e-> System.out.println(e.getKey()));
    }
}
