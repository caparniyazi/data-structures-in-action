package com.caparniyazi.ds.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MineSweeperController {
    // Data fields

    // Static nested classes for request/response models.
    @Getter
    @Setter
    private static class MinesRequest {
        private char[][] square;
    }

    @Getter
    @Setter
    private static class MinesResponse {
        private char[][] hints;
    }

    @PostMapping("/show-hints")
    public ResponseEntity<?> mineSweeper(@RequestBody MinesRequest minesRequest) {
        MinesResponse minesResponse = new MinesResponse();

        try {
            minesResponse.setHints(evaluateMines(minesRequest.getSquare()));
            return ResponseEntity.ok(minesResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * The method that finds all the mines within an MxN field.
     *
     * @param square The field of mines represented as matrix.
     * @return The hint numbers showing the adjacent mines to that square.
     */
    public static char[][] evaluateMines(char[][] square) {
        for (int j = 0; j != square.length; ++j) {

            for (int i = 0; i != square[j].length; ++i) {
                if (square[j][i] == '.') {
                    countMines(square, j, i);
                }
            }
        }

        return square;
    }

    /**
     * The method that finds the number of mines at position (i, j)
     *
     * @param square The minefield.
     * @param j      The column
     * @param i      The row
     */
    private static void countMines(char[][] square, int j, int i) {
        int count = checkMine(square, j, i);
        square[j][i] = (char) ('0' + count);    // set the hint number for the current coordinate.
    }

    /**
     * @param square The minefield
     * @param j      The column
     * @param i      The row
     * @return The number of mines adjacent to (i, j) coordinate.
     */
    private static char checkMine(char[][] square, int j, int i) {
        char count = 0;

        if (isBoundaryValid(square, j, i)) {
            if (square[j][i] == '*') {
                count++;
            }
        }
        if (isBoundaryValid(square, j + 1, i - 1)) {
            if (square[j + 1][i - 1] == '*') {
                count++;
            }
        }
        if (isBoundaryValid(square, j + 1, i)) {
            if (square[j + 1][i] == '*') {
                count++;
            }
        }
        if (isBoundaryValid(square, j + 1, i + 1)) {
            if (square[j + 1][i + 1] == '*') {
                count++;
            }
        }
        if (isBoundaryValid(square, j, i - 1)) {
            if (square[j][i - 1] == '*') {
                count++;
            }
        }
        if (isBoundaryValid(square, j, i + 1)) {
            if (square[j][i + 1] == '*') {
                count++;
            }
        }
        if (isBoundaryValid(square, j - 1, i - 1)) {
            if (square[j - 1][i - 1] == '*') {
                count++;
            }
        }
        if (isBoundaryValid(square, j - 1, i)) {
            if (square[j - 1][i] == '*') {
                count++;
            }
        }
        if (isBoundaryValid(square, j - 1, i + 1)) {
            if (square[j - 1][i + 1] == '*') {
                count++;
            }
        }

        return count;
    }

    /**
     * The helper method that checks whether mine at (i, j) coordinate is outside the minefield.
     *
     * @param square The minefield
     * @param j      The column
     * @param i      The row
     * @return True if (i, j) coordinate is not outside the minefield, otherwise return false.
     */
    private static boolean isBoundaryValid(char[][] square, int j, int i) {
        return !(j < 0 || j >= square.length || i < 0 || i >= square[0].length);
    }
}
