package com.caparniyazi.ds.recursion;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class to manage two-dimensional grid of cells(buttons).
 * Each button can be toggled between two colors by clicking it with the mouse,
 * or its color can be changed/queried under program control.
 */
public class TwoDimGrid extends JPanel implements GridColors {
    // Data fields
    private static final int PREFERRED_BUTTON_SIZE = 60;
    private static final int DEFAULT_COLS = 20; // Default number of columns.
    private static final int DEFAULT_ROWS = 20; // Default number of rows.
    private JButton[][] theGrid;    // A two dimensional grid of buttons.
    @Getter
    private int nRows;  // Number of rows in the y-axis.
    @Getter
    private int nCols;  // Number of columns in the x-axis.

    // Constructors

    public TwoDimGrid(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        setPreferredSize(new Dimension(nCols * PREFERRED_BUTTON_SIZE, nRows * PREFERRED_BUTTON_SIZE));
        setLayout(new GridLayout(nRows, nCols));
        theGrid = new JButton[nCols][];
        for (int i = 0; i != nCols; ++i) {
            theGrid[i] = new JButton[nRows];
            for (int j = 0; j != nRows; ++j) {
                theGrid[i][j] = new JButton(i + ", " + j);
                theGrid[i][j].setBackground(BACKGROUND);
                theGrid[i][j].addActionListener(new ToggleColor(theGrid[i][j]));
            }
        }

        // Add the buttons to the button panel
        for (int j = 0; j != nRows; ++j) {
            for (int i = 0; i != nCols; ++i) {
                add(theGrid[i][j]);
            }
        }
    }

    // Methods

    /**
     * Retrieves the color of the cell at position (x, y)
     *
     * @param x The column number
     * @param y The row number
     * @return The color at the given coordinate.
     */
    public Color getColor(int x, int y) {
        return theGrid[x][y].getBackground();
    }

    /**
     * Change the color at a given coordinate.
     *
     * @param x        The column number
     * @param y        The row number
     * @param newColor The color to set the button to.
     */
    public void recolor(int x, int y, Color newColor) {
        theGrid[x][y].setBackground(newColor);
        repaint();
    }

    /**
     * Set the color of each square in the grid that correspond to the elements of the given array
     * with the value 1.
     *
     * @param bitmap An array of 0's and 1's the same size as the grid.
     * @param aColor The color to be set.
     * @throws ArrayIndexOutOfBoundsException if the array size and grid size differ.
     */
    public void recolor(char[][] bitmap, Color aColor) {
        for (int i = 0; i != bitmap.length; i++) {
            for (int j = 0; j != bitmap[i].length; j++) {
                if (bitmap[i][j] == '1') {
                    theGrid[j][i].setBackground(aColor);
                }
            }
        }
    }

    /**
     * Inner class to toggle color when clicked.
     */
    private static class ToggleColor implements ActionListener {
        // Data fields
        private final JButton me; // The button to be responded to

        public ToggleColor(JButton theButton) {
            me = theButton;
        }

        /**
         * Action in response to button push.
         *
         * @param e the event object is ignored.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (me.getBackground().equals(BACKGROUND)) {
                me.setBackground(NON_BACKGROUND);
            } else {
                me.setBackground(BACKGROUND);
            }
        }
    }
}
