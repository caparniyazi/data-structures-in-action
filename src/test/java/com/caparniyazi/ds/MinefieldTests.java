package com.caparniyazi.ds;

import com.caparniyazi.ds.controller.MineSweeperController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MinefieldTests {
    @Test
    public void testCase1() {
        char[][] square = {
                {'*', '*', '.', '.', '.'},
                {'.', '.', '.', '.', '.'},
                {'.', '*', '.', '.', '.'}};

        char[][] result = {
                {'*', '*', '1', '0', '0'},
                {'3', '3', '2', '0', '0'},
                {'1', '*', '1', '0', '0'}
        };

        assertEquals(Arrays.deepToString(result), Arrays.deepToString(MineSweeperController.evaluateMines(square)));
    }

    @Test
    public void testCase2() {
        char[][] square = {
                {'*', '.', '.', '.'},
                {'.', '.', '.', '.'},
                {'.', '*', '.', '.'},
                {'.', '.', '.', '.'}};

        char[][] result = {
                {'*', '1', '0', '0'},
                {'2', '2', '1', '0'},
                {'1', '*', '1', '0'},
                {'1', '1', '1', '0'}
        };

        assertEquals(Arrays.deepToString(result), Arrays.deepToString(MineSweeperController.evaluateMines(square)));
    }

    @Test
    public void testNoMines() {
        char[][] square = {
                {'.', '.', '.', '.'},
                {'.', '.', '.', '.'},
                {'.', '.', '.', '.'},
                {'.', '.', '.', '.'}};

        char[][] result = {
                {'0', '0', '0', '0'},
                {'0', '0', '0', '0'},
                {'0', '0', '0', '0'},
                {'0', '0', '0', '0'}
        };

        assertEquals(Arrays.deepToString(result), Arrays.deepToString(MineSweeperController.evaluateMines(square)));
    }

    @Test
    public void testAllMines() {
        char[][] square = {
                {'*', '*', '*', '*'},
                {'*', '*', '*', '*'},
                {'*', '*', '*', '*'},
                {'*', '*', '*', '*'}};

        char[][] result = {
                {'*', '*', '*', '*'},
                {'*', '*', '*', '*'},
                {'*', '*', '*', '*'},
                {'*', '*', '*', '*'}
        };

        assertEquals(Arrays.deepToString(result), Arrays.deepToString(MineSweeperController.evaluateMines(square)));
    }

    @Test
    public void testAllMinesButDiagonals() {
        char[][] square = {
                {'.', '*', '*', '.'},
                {'*', '.', '.', '*'},
                {'*', '.', '.', '*'},
                {'.', '*', '*', '.'}};

        char[][] result = {
                {'2', '*', '*', '2'},
                {'*', '4', '4', '*'},
                {'*', '4', '4', '*'},
                {'2', '*', '*', '2'}
        };

        assertEquals(Arrays.deepToString(result), Arrays.deepToString(MineSweeperController.evaluateMines(square)));
    }
}
