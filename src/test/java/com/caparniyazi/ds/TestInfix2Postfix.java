package com.caparniyazi.ds;

import com.caparniyazi.ds.collections.stack.InfixToPostfix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void expressionWithInvalidOperator() throws RuntimeException {
        String infix = "x1 & 2";
        String expectedResult = "x1 2";
        assertThrows(InfixToPostfix.SyntaxErrorException.class, () -> InfixToPostfix.convert(infix));
    }
}
