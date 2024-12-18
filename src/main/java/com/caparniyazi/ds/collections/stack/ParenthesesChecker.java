package com.caparniyazi.ds.collections.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;

/**
 * Class to check for balanced parentheses.
 */
public class ParenthesesChecker {
    public static void main(String[] args) {
        String input = "(abc(d())e)";
        System.out.println(isBalanced(input));
    }

    // Data fields
    private static final String OPEN = "([{";   // Set of opening parenthesis characters
    private static final String CLOSE = ")]}";  // Set of closing parenthesis characters, matches OPEN

    /**
     * Method to test the input string to see that it contains balanced parentheses.
     *
     * @param expression The string containing the expression to be examined.
     * @return true if all the parentheses match.
     * Time Complexity O(n) = n, where n is the length of the input string, because we iterate
     * through the entire string once.
     * Space Complexity O(n) = n, where n is the length of the input string.In the worst case
     * the stack could contain all opening parentheses of the input string.
     */
    public static boolean isBalanced(String expression) {
        Deque<Character> stack = new ArrayDeque<>();   // Create an empty stack.
        boolean balanced = true;

        try {
            int index = 0;

            while (balanced && index < expression.length()) {
                char nextChar = expression.charAt(index);

                if (isOpen(nextChar)) {
                    stack.push(nextChar);
                } else if (isClose(nextChar)) {
                    char topChar = stack.pop();
                    balanced = OPEN.indexOf(topChar) == CLOSE.indexOf(nextChar);
                }
                index++;
            }
        } catch (EmptyStackException e) {
            // Attempting to pop an empty stack is a reasonable thing to do when
            // an expression has more closing parentheses than opening parentheses.
            balanced = false;
        }

        return balanced && stack.isEmpty();
    }

    /**
     * Method to determine whether a character is one of the closing parentheses.
     *
     * @param ch Character to be tested.
     * @return true if ch is one of the closing parentheses.
     */
    private static boolean isClose(char ch) {
        return CLOSE.indexOf(ch) != -1;
    }

    /**
     * Method to determine whether a character is one of the opening parentheses.
     *
     * @param ch Character to be tested.
     * @return true if nextChar is one of the opening parentheses.
     */
    private static boolean isOpen(char ch) {
        return OPEN.indexOf(ch) != -1;
    }
}
