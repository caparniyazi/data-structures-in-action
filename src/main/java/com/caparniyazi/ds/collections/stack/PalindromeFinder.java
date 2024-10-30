package com.caparniyazi.ds.collections.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class with methods to check whether a string is a palindrome.
 */
public class PalindromeFinder {
    /**
     * Method that fills a stack with characters from an input string.
     *
     * @param inputString The string to be checked.
     * @return a stack of characters in inputString.
     */
    private static Deque<Character> fillStack(String inputString) {
        Deque<Character> charStack = new ArrayDeque<Character>();
        for (int i = 0; i < inputString.length(); i++) {
            charStack.push(inputString.charAt(i));
        }

        return charStack;
    }

    /**
     * Builds the reverse of a string by calling fillStack to push its characters onto a stack
     * and then popping them and appending them to a new string.
     *
     * @param inputString The string to be checked.
     * @return The string containing the characters in the stack.
     */
    private static String buildReverse(String inputString) {
        Deque<Character> charStack = fillStack(inputString);
        StringBuilder reversed = new StringBuilder();

        while (!charStack.isEmpty()) {
            reversed.append(charStack.pop());
        }
        return reversed.toString();
    }

    /**
     * Returns true if inputString is a palindrome, false if not.
     *
     * @param inputString The string to be checked.
     * @return true if inputString is a palindrome, false if not.
     */
    public static boolean isPalindrome(String inputString) {
        return buildReverse(inputString).equalsIgnoreCase(inputString);
    }
}
