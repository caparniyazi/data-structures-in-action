package com.caparniyazi.ds.recursion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/***
 * Program to illustrate counting cells in a blob.
 */
public class BlobTest extends JFrame implements GridColors, ActionListener {
    // Data fields
    private final TwoDimGrid theGrid; // A 2D grid of buttons.

    // Constructors
    // Build the GUI.
    private BlobTest(TwoDimGrid grid) {
        theGrid = grid;
        getContentPane().add(theGrid, BorderLayout.CENTER);
        JTextArea instruct = new JTextArea(2, 10);
        instruct.setText("Toggle a button to change its color" + "\nPress SOLVE when ready.");
        instruct.setEditable(false);
        getContentPane().add(instruct, BorderLayout.NORTH);
        JButton solveButton = new JButton("SOLVE");
        solveButton.addActionListener(this);
        JButton restoreButton = new JButton("RESTORE");
        restoreButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(solveButton);
        buttonPanel.add(restoreButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Respond to pressing SOLVE button.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SOLVE")) {
            String reply = JOptionPane.showInputDialog("Enter x coordinate of blob cell");
            int x = Integer.parseInt(reply);
            reply = JOptionPane.showInputDialog("Enter y coordinate of blob cell");
            int y = Integer.parseInt(reply);
            Blob aBlob = new Blob(theGrid);
            JOptionPane.showMessageDialog(null, "For blob at ("
                    + x + ", " + y + ") \ncount is " + aBlob.countCells(x, y)
                    + "\nRest blob and try again");
        } else {
            Blob aBlob = new Blob(theGrid);
            aBlob.restore();
        }
    }

    /**
     * Reads data file and defines array bitMap to match data file.
     *
     * @param args Optional input file containing Blob.
     */
    public static void main(String[] args) {
        try {
            System.out.println(System.getProperty("user.dir"));
            if (args.length == 0) {
                // No filename given
                String reply = JOptionPane.showInputDialog("Enter number of rows");
                int rows = Integer.parseInt(reply);
                reply = JOptionPane.showInputDialog("Enter number of columns");
                int cols = Integer.parseInt(reply);
                TwoDimGrid grid = new TwoDimGrid(rows, cols);
                new BlobTest(grid);
            } else {
                // Create array bitmap from a data file.
                //
                BufferedReader br = new BufferedReader(new FileReader(args[0]));
                // Read each data line (a string) into gridArrayList. Each element is char array.
                ArrayList<char[]> gridArrayList = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    char[] row = line.replaceAll("\\s", "").toCharArray();
                    gridArrayList.add(row);
                }

                // bitMap is a 2-D array based on data in gridArrayList.
                char[][] bitMap = gridArrayList.toArray(new char[gridArrayList.size()][]);
                int nRows = bitMap.length;
                int nCols = bitMap[0].length;

                // Create a new TwoDimGrid and recolor it based on bitMap.
                TwoDimGrid aGrid = new TwoDimGrid(nRows, nCols);
                aGrid.recolor(bitMap, NON_BACKGROUND);
                new BlobTest(aGrid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
