package com.caparniyazi.ds.recursion;

import static com.caparniyazi.ds.recursion.GridColors.*;

/**
 * Class that solves the problem of counting abnormal cells.
 */
public class Blob {
    // Data fields
    /**
     * A two-dimensional grid of cells, and each contains either a normal background color or
     * a second color, which indicates the presence of an abnormality. The user wants to know
     * the size of the blob, where a blob is a collection of contiguous abnormal cells.
     * The user enter the (x, y) coordinates of a cell in the blob, and the count of all cells
     * in that blob will be determined.
     */
    private final TwoDimGrid grid;

    // Constructors
    public Blob(TwoDimGrid grid) {
        this.grid = grid;
    }

    // Methods

    /**
     * Finds the number of cells in the blob at (x, y).
     * <p/>
     * The recursive
     * step involves counting 1 for a cell that has the abnormal color and adding the counts for the
     * blobs that include each immediate neighbor cell. Each cell has eight immediate neighbors:
     * two in the horizontal direction, two in the vertical direction, and four in the diagonal
     * directions.
     * <p/>
     * If no neighbor has the abnormal color, then the result will be just 1. If any neighbor cell has
     * the abnormal color, then it will be counted along with all its neighbor cells that have the
     * abnormal color, and so on until no neighboring cells with abnormal color are encountered
     * (or the edge of the grid is reached). The reason for setting the color of the cell at (x, y) to a
     * temporary color is to prevent it from being counted again when its neighbors’ blobs are
     * counted.
     *
     * @param x The x coordinate (column) of a blob cell
     * @param y The y coordinate (row) of a blob cell.
     * @return the number of cells in the blob at (x, y).
     * @pre Abnormal cells are in ABNORMAL(RED) color, other cells are in BACKGROUND(WHITE) color.
     * @post All cells in the blob are in the TEMPORARY(BLACK) color.
     */
    public int countCells(int x, int y) {
        // If the cell at (x, y) is outside the grid, result is 0.
        if (x < 0 || x >= grid.getNCols() || y < 0 || y >= grid.getNRows()) { // The 1st stopping case.
            return 0;
        } else if (!grid.getColor(x, y).equals(ABNORMAL)) { // the 2nd stopping case.
            // If the color of the cell at(x, y) has either the background color or the temporary color
            return 0;
        } else {    // The recursive step.
            grid.recolor(x, y, TEMPORARY);  // Set the color of the cell at (x, y) to a temp color.
            // The result is 1 plus the number of cells in each piece of the blob that
            // includes a nearest/immediate neighbor ( two in the horizontal direction,
            // two in the vertical direction, and four in the diagonal directions).
            return 1
                    + countCells(x - 1, y + 1)
                    + countCells(x, y + 1)
                    + countCells(x + 1, y + 1)
                    + countCells(x - 1, y)
                    + countCells(x + 1, y)
                    + countCells(x - 1, y - 1)
                    + countCells(x, y - 1)
                    + countCells(x + 1, y - 1);

        }
    }

    public void restore() {
        for (int i = 0; i != grid.getNCols(); i++) {
            for (int j = 0; j != grid.getNRows(); j++) {
                if (grid.getColor(i, j).equals(TEMPORARY)) {
                    grid.recolor(i, j, NON_BACKGROUND);
                }
            }
        }
    }
}
