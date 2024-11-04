package com.caparniyazi.ds.collections.stack;

import java.util.*;

/**
 * Class that converts an infix expression to a postfix expression.
 * The ability to convert expressions with parentheses is an important (and necessary)
 * addition.
 * <p/>
 * We can think of an opening parenthesis on an operator stack as a boundary or fence
 * between operators. Whenever we encounter an opening parenthesis, we want to push it
 * onto the stack. A closing parenthesis is the terminator symbol for a subexpression.
 * Whenever we encounter a closing parenthesis, we want to pop off all operators on the
 * stack until we pop the patching opening parenthesis. Neither opening nor closing
 * parentheses should appear in the postfix expression. Because operators scanned after
 * the opening parenthesis should be evaluated before the opening parenthesis, the precedence
 * of the opening parentheses must be smaller than any other operator. We also
 * give a closing parenthesis the lowest precedence. This ensures that a "(" can only be
 * popped by a ")".
 * <p/>
 * Class modified to handle parentheses.
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
    private static final String OPERATORS = "-+*/^()";
    // The precedence of the operators that matches order in OPERATORS.
    private static final int[] PRECEDENCE = {1, 1, 2, 2, 3, -1, -1};
    private final StringJoiner postfix = new StringJoiner(" ");
    /**
     * Each token can be an integer(a sequence of one or more digits),
     * a double (a sequence of one or more digits followed by a decimal point followed by zero or  more digits),
     * an identifier (a letter followed by zero or more letters or digits), or an operator.
     */
    private static final String tokens = "\\d+\\.\\d*|\\d+|\\p{L}[\\p{L}\\p{N}]*|[-+*/^()]";

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
        try {
            // Process each token in the infix string.
            String nextToken;
            Scanner scanner = new Scanner(infix);
            while ((nextToken = scanner.findInLine(tokens)) != null) {
                char firstChar = nextToken.charAt(0);

                // Is it an operand?
                if (Character.isJavaIdentifierStart(firstChar) || Character.isDigit(firstChar)) {
                    postfix.add(nextToken);
                } else if (isOperator(firstChar)) {
                    processOperator(firstChar);
                } else {
                    throw new SyntaxErrorException("Invalid character encountered: " + firstChar);
                }
            } // end loop.
            // Pop any remaining operators and append them to postfix.

            while (!operatorStack.isEmpty()) {
                char op = operatorStack.pop();

                // Any opening parenthesis on the stack is not matched.
                if (op == '(') {
                    throw new SyntaxErrorException("Unmatched opening parenthesis");
                }
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
        if (operatorStack.isEmpty() || op == '(') {
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

                    if (topOp == '(') {
                        // Matching opening parenthesis is popped, exit the loop.
                        break;
                    }
                    postfix.add(Character.toString(topOp));

                    if (!operatorStack.isEmpty()) {
                        topOp = operatorStack.peek();
                    }
                }

                // Current stack is empty or current operator precedence > top of stack operator precedence.
                // A closing parenthesis is considered processed when an opening parenthesis
                // is popped from the stack and the closing parenthesis is not placed on the stack.
                if (op != ')') {
                    operatorStack.push(op);
                }
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
