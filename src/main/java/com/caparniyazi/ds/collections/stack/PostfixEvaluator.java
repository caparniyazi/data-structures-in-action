package com.caparniyazi.ds.collections.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * Class that can evaluate a postfix expression.
 * The postfix expression will be a string containing digit characters and operator characters
 * from the set +,-,*,/. The space character will be used as a delimiter between tokens(integers and operators).
 * <p/>
 * In a postfix expression, the operands precede the operators.
 * For example:
 * Postfix expression => 4 7 *
 * Infix expression = 4 *7
 * Value = 28
 * <p/>
 * A stack is the perfect place to save the operands until the operator is scanned. When the operator is scanned,
 * its operands can be popped off the stack, i.e., the right operand, will be popped first.
 * When the operator is read, the two operands are popped, the operation is performed on its operands,
 * and the result is pushed back onto the stack. The final value should be the only value remaining on the stack
 * when the end of the expression is reached.
 */
public class PostfixEvaluator {
    // Data fields
    private static final String OPERATORS = "+-*/";

    // Nested static class to report a syntax error.
    private static class SyntaxErrorException extends RuntimeException {

        SyntaxErrorException(String message) {
            super(message);
        }
    }

    // Methods

    /**
     * Evaluates the current operation.
     * This function pops the two operands off the operand stack and applies the operator.
     *
     * @param op           A character representing the operator.
     * @param operandStack The current stack of operands.
     * @return The result of applying the operator.
     * @throws NoSuchElementException if pop is attempted on an empty stack.
     */
    private static int evalOp(char op, Deque<Integer> operandStack) {
        // Pop the two operands
        int rhs = operandStack.pop();
        int lhs = operandStack.pop();
        int result = 0;

        // Evaluate the operator.
        return switch (op) {
            case '+' -> result = lhs + rhs;
            case '-' -> result = lhs - rhs;
            case '*' -> result = lhs * rhs;
            case '/' -> result = lhs / rhs;
            default -> throw new IllegalStateException("Unexpected value: " + op);
        };
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

    /**
     * Method that evaluates the postfix expression.
     *
     * @param expression The expression to be evaluated.
     * @return The value of the expression.
     * @throws SyntaxErrorException if a syntax error is detected.
     */
    public static int eval(String expression) {
        // Create an empty stack
        Deque<Integer> operandStack = new ArrayDeque<>();

        // Process each token
        String[] tokens = expression.split("\\s+");
        try {
            for (String token : tokens) {
                char firstChar = token.charAt(0);
                if (Character.isDigit(firstChar)) {
                    // Get the integer value
                    operandStack.push(Integer.parseInt(token));
                } else if (isOperator(firstChar)) {
                    // Evaluate the operator
                    int result = evalOp(firstChar, operandStack);
                    // Push the result back onto the operand stack
                    operandStack.push(result);
                } else {
                    // Invalid character
                    throw new SyntaxErrorException(String.format("Unrecognized character '%s'", token));
                }
            } // End for.

            // No more tokens, pop result from operand stack
            int answer = operandStack.pop();

            if (operandStack.isEmpty()) {
                return answer;
            } else {
                throw new SyntaxErrorException("Syntax error: Stack should be empty.");
            }
        } catch (NoSuchElementException e) {
            // Pop was attempted on an empty stack.
            throw new SyntaxErrorException("Syntax error: Stack is empty.");
        }
    }
}
