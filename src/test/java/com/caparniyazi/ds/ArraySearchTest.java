package com.caparniyazi.ds;

import com.caparniyazi.ds.arrays.ArrayUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test of ArrayUtils.search().
 */
public class ArraySearchTest {
    // Data fields
    // Common array to search for most of the tests.
    private final int[] x = {5, 12, 15, 4, 8, 12, 7};

    @Test
    public void firstElementTest() {
        // Test for target as the first element.
        assertEquals(0, ArrayUtils.search(x, 5), "5 is not at position 0");
    }

    @Test
    public void lastElementTest() {
        // Test for target as the last element.
        assertEquals(6, ArrayUtils.search(x, 7), "7 is not at position 6");
    }

    @Test
    public void inMiddleTest() {
        // Test for target somewhere in the middle.
        assertEquals(3, ArrayUtils.search(x, 4), "4 is not at position 3");
    }

    @Test
    public void notInArrayTest() {
        // Test for target not in the array.
        assertEquals(-1, ArrayUtils.search(x, -5));
    }

    @Test
    public void oneElementArrayTestItemPresent() {
        // Test for 1-element array.
        int[] y = {10};
        assertEquals(0, ArrayUtils.search(y, 10));
    }

    @Test
    public void oneElementArrayTestItemAbsent() {
        // Test for 1-element array.
        int[] y = {10};
        assertEquals(-1, ArrayUtils.search(y, -10));
    }

    @Test
    public void emptyArrayTest() {
        // Test for an empty array.
        int[] y = new int[0];
        assertEquals(-1, ArrayUtils.search(y, 10));
    }

    @Test
    public void nullArrayTest() {
        // Test for a null array.
        int[] y = null;
        assertThrows(NullPointerException.class, () -> ArrayUtils.search(y, 10));
    }
}
