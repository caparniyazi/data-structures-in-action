package com.caparniyazi.ds;

import com.caparniyazi.ds.controller.PostfixController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PostfixTests {
    @Test
    public void testCase1() {
        String expression = "20 5 /";
        int result = PostfixController.evaluate(expression);
        assertEquals(4, result);
    }

    @Test
    public void testCase2() {
        String expression = "23 9 * 6 / 6 3 5 * - +";
        int result = PostfixController.evaluate(expression);
        assertEquals(25, result);
    }

    @Test
    public void testCase3() {
        String expression = "15 4 * 8 5 + 3 2 / - *";
        int result = PostfixController.evaluate(expression);
        assertEquals(720, result);
    }

    // 4 2 + 3
    @Test
    public void testCase4() {
        String expression = "4 2 + 3";
        assertThrows(IllegalArgumentException.class, () -> PostfixController.evaluate(expression));
    }

    //  4 2 + 3 -
    @Test
    public void testCase5() {
        String expression = "4 2 + 3 -";
        int result = PostfixController.evaluate(expression);
        assertEquals(3, result);
    }

    // 3 5 8 * 7 + *
    @Test
    public void testCase6() {
        String expression = "3 5 8 * 7 + *";
        // 127 *
        int result = PostfixController.evaluate(expression);
        assertEquals(141, result);
    }
}
