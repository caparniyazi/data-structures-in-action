package com.caparniyazi.ds.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayDeque;
import java.util.Deque;

@RestController
@RequestMapping("/api/v1")
public class PostfixController {
    // Data fields

    // Static nested classes for request/response models.
    @Getter
    @Setter
    private static class PostfixExpression {
        private String expression;
    }

    @Getter
    @Setter
    private static class PostfixResponse {
        private int result;
    }

    /**
     * REST API that evaluates a postfix expression.
     *
     * @param postfixExpression The string expression to be evaluated.
     * @return The value of the postfix expression.
     */
    @PostMapping("/calculate")
    public ResponseEntity<?> evaluatePostfixExpression(@RequestBody PostfixExpression postfixExpression) {
        PostfixResponse postfixResponse = new PostfixResponse();

        try {
            postfixResponse.setResult(evaluate(postfixExpression.getExpression()));
            return ResponseEntity.ok(postfixResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Method that evaluates the postfix expression.
     *
     * @param expression The string expression to be evaluated.
     * @return The value of the postfix expression.
     */
    public static int evaluate(String expression) {
        // Create an empty deque.
        Deque<Integer> operands = new ArrayDeque<>();

        // Process the tokens now.
        String[] tokens = expression.split("\\s+");
        for (String nextToken : tokens) {
            char firstChar = nextToken.charAt(0);

            if (Character.isDigit(firstChar)) {
                operands.push(Integer.parseInt(nextToken));
            } else if (isOperator(firstChar)) {
                // Push the arithmetic result back to the deque.
                operands.push(evaluateOperator(operands, firstChar));
            } else {
                throw new IllegalArgumentException("Invalid operator: " + firstChar);
            }
        }

        // No more tokens left, so pop the result.
        int result = operands.pop();

        // The deque should be empty now.
        if (operands.isEmpty()) {
            return result;
        } else {
            throw new IllegalArgumentException("Syntax error. Deque should be empty.");
        }
    }

    /**
     * Method to evaluate the binary arithmetic operators.
     *
     * @param operands Deque of operands
     * @param operator The arithmetic operator
     * @return The result of applying the operator.
     */
    private static Integer evaluateOperator(Deque<Integer> operands, char operator) {
        int rhs = operands.pop();   // The right-hand side operand.
        int lhs = operands.pop();   // The left-hand side operand.

        return switch (operator) {
            case '+' -> lhs + rhs;
            case '-' -> lhs - rhs;
            case '*' -> lhs * rhs;
            case '/' -> lhs / rhs;
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };
    }

    /**
     * Helper method to check the token whether it is an arithmetic operator.
     *
     * @param firstChar The current char token.
     * @return true if current token is an arithmetic operator, false otherwise.
     */
    private static boolean isOperator(char firstChar) {
        final String OPERATORS = "+-*/"; // The list of operators that are supported.
        return OPERATORS.indexOf(firstChar) != -1;
    }
}
