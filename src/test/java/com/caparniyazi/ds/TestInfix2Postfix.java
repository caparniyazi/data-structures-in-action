package com.caparniyazi.ds;

import com.caparniyazi.ds.collections.stack.InfixToPostfix;
import com.caparniyazi.ds.collections.stack.PostfixEvaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInfix2Postfix {
    @Test
    public void simpleExpressionWithSamePrecedence() throws RuntimeException {
        String infix = "a + b";
        String expectedResult = "a b +";
        String actualResult = InfixToPostfix.convert(infix);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void simpleExpressionWithNumbersSamePrecedence() throws RuntimeException {
        String infix = "2.5 * 6";
        String expectedResult = "2.5 6 *";
        String result = InfixToPostfix.convert(infix);
        assertEquals(expectedResult, result);
    }

    @Test
    public void expressionWithMixedPrecedence() throws RuntimeException {
        String infix = "x1 - y / 2 + foo";
        String expectedResult = "x1 y 2 / - foo +";
        String result = InfixToPostfix.convert(infix);
        assertEquals(expectedResult, result);
    }

    @Test
    public void expressionWithParentheses() {
        String infix = "3 + ((4 * 7) / 2)";
        String expectedResult = "3 4 7 * 2 / +";
        String actualResult = InfixToPostfix.convert(infix);
        assertEquals(expectedResult, actualResult);
        assertEquals(PostfixEvaluator.eval(expectedResult), 17);
    }

    @Test
    public void expressionWithParentheses2() {
        String infix = "(4 * 7) - 20";
        String expectedResult = "4 7 * 20 -";
        String actualResult = InfixToPostfix.convert(infix);
        assertEquals(expectedResult, actualResult);
        assertEquals(PostfixEvaluator.eval(expectedResult), 8);
    }

    @Test
    public void expressionWithParentheses3() {
        String infix = "4 * (7 + 2)";
        String expectedResult = "4 7 2 + *";
        String actualResult = InfixToPostfix.convert(infix);
        assertEquals(expectedResult, actualResult);
        assertEquals(PostfixEvaluator.eval(expectedResult), 36);
    }
}
