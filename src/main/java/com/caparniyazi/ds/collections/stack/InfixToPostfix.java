package com.caparniyazi.ds.collections.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Class that converts an infix expression to a postfix expression.
 */
public class InfixToPostfix {
    // Data fields
    // Nested static class to report a syntax error.
    public static class SyntaxErrorException extends RuntimeException {
        SyntaxErrorException(String message) {
            super(message);
        }
    }

    private final Deque<Character> operatorStack = new ArrayDeque<>();
    private static final String OPERATORS = "+-*/";
    // The precedence of the operators that matches order in OPERATORS.
    private static final int[] PRECEDENCE = {1, 1, 2, 2};
    private final StringJoiner postfix = new StringJoiner(" ");


    public static String convert(String infix) throws SyntaxErrorException {
        InfixToPostfix infixToPostfix = new InfixToPostfix();
        infixToPostfix.convertToPostfix(infix);
        return infixToPostfix.getPostfix();
    }

    /**
     * Converts a string from infix to postfix. Uses a stack to convert an infix expression to postfix.
     *
     * @param infix The string to convert to postfix.
     * @throws SyntaxErrorException if argument is invalid.
     * @pre operator stack is empty
     * @post postfix contains postfix expression and stack is empty.
     */
    private void convertToPostfix(String infix) {
        String[] tokens = infix.split("\\s+");
        try {
            // Process each token in the infix string.
            for (String nextToken : tokens) {
                char firstChar = nextToken.charAt(0);

                if (Character.isJavaIdentifierStart(firstChar) || Character.isDigit(firstChar)) {
                    postfix.add(nextToken);
                } else if (isOperator(firstChar)) {
                    processOperator(firstChar);
                } else {
                    throw new SyntaxErrorException("Invalid operator: " + firstChar);
                }
            } // end for.
            // Pop any remaining operators and append them to postfix.

            while (!operatorStack.isEmpty()) {
                char op = operatorStack.pop();
                postfix.add(Character.toString(op));
            }
            // assert: Stack is empty, return.
        } catch (NoSuchElementException e) {
            throw new SyntaxErrorException("Syntax error: The stack is empty");
        }
    }

    /**
     * Method to process operators.
     *
     * @param op The operator
     * @throws NoSuchElementException if the stack is empty.
     */
    private void processOperator(char op) {
        if (operatorStack.isEmpty()) {
            operatorStack.push(op);
        } else {
            // Peek the operator stack and let topOp be top operator
            char topOp = operatorStack.peek();

            if (precedence(op) > precedence(topOp)) {
                operatorStack.push(op);
            } else {
                // Pop all stacked operators with equal or higher precedence than op.

                while (!operatorStack.isEmpty() && precedence(op) <= precedence(topOp)) {
                    operatorStack.pop();
                    postfix.add(Character.toString(topOp));

                    if (!operatorStack.isEmpty()) {
                        topOp = operatorStack.peek();
                    }
                }

                // Current stack is empty or current operator precedence > top of stack operator precedence.
                operatorStack.push(op);
            }
        }
    }

    /**
     * Determine the precedence of an operator.
     *
     * @param op The operator.
     * @return The precedence.
     */
    private static int precedence(char op) {
        return PRECEDENCE[OPERATORS.indexOf(op)];
    }

    /**
     * Method to determine whether a character is an operator.
     *
     * @param op The character to be tested.
     * @return true if the character is an operator.
     */
    private static boolean isOperator(char op) {
        return OPERATORS.indexOf(op) != -1;
    }

    // Return the final postfix string.
    private String getPostfix() {
        return postfix.toString();
    }
}
