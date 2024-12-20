package com.caparniyazi.ds.recursion;

/**
 * Class that solves maze problems with backtracking.
 * Backtracking is an approach to implementing systematic trial and error
 * in a search for a solution. The class uses backtracking to find and display the path through a maze.
 * From each point in a maze, you can move to the next cell in the horizontal or vertical direction,
 * if that cell is not blocked. So there are at most four possible moves from each point.
 * <p>
 * Our maze will consist of a grid of colored cells.
 * The starting point is the cell at the top left corner (0, 0), and the exit point is the cell at the
 * bottom right corner (getNCols() – 1, getNRows() – 1).
 * All cells that can be part of a path will be in the BACKGROUND color.
 * All cells that represent barriers and cannot be part of a path will be in the ABNORMAL color.
 * To keep track of a cell that we have visited, we will set it to the TEMPORARY color.
 * If we find a path, all cells on the path will be reset to the PATH color (a new color for a
 * button defined in GridColors).
 * </p>
 * <p>
 * An application of backtracking is finding a path through a maze.
 * If you are attempting to walk through a maze, you will probably follow the general approach
 * of walking down a path as far as you can go. Eventually either you will reach your destination
 * and exit the maze, or you won’t be able to go any further. If you exit the maze, you are
 * done. Otherwise, you need to retrace your steps (backtrack) until you reach a fork in the
 * path. At each fork, if there is a branch you did not follow, you will follow that branch hoping
 * to reach your destination. If not, you will retrace your steps again, and so on.
 * What makes backtracking different from random trial and error is that backtracking provides
 * a systematic approach to trying alternative paths and eliminating them if they don’t
 * work out. You will never try the exact same path more than once, and you will eventually
 * find a solution path if one exists.
 * </p>
 * <p>
 * Recursion allows us to implement backtracking in a relatively straightforward manner because
 * we can use each activation frame to remember the choice that was made at that particular
 * decision point. We will show how to use backtracking to find a path through a maze, but it can be applied
 * to many other kinds of problems that involve a search for a solution. For example, a program
 * that plays chess may use a kind of backtracking.
 * </p>
 */
public class Maze implements GridColors {
    // Data fields
    private final TwoDimGrid maze;

    // Constructors
    public Maze(TwoDimGrid maze) {
        this.maze = maze;
    }

    /**
     * Wrapper method.
     *
     * @return true if there is path to exit, false otherwise.
     */
    public boolean findMazePath() {
        return findMazePath(0, 0);  // (0, 0) is the starting point.
    }

    /**
     * Helper method that attempts to find a path through point (x, y).
     * Note that there is no attempt to find the shortest path through the maze. We just show the
     * first path that is found.
     *
     * @param x The x-coordinate of the current point.
     * @param y The y-coordinate of the current point.
     * @return true, if a path through (x, y) is found; false otherwise.
     * @pre Possible path cells are in BACKGROUND color; barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the PATH color; all cells
     * that were visited but are not on the path are in the TEMPORARY color.
     */
    private boolean findMazePath(int x, int y) {
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getHeight()) {
            return false;   // Cell is out of bonds.
        } else if (!maze.getColor(x, y).equals(BACKGROUND)) {
            return false; // Cell is part of the barrier or dead end(has already been visited).
        } else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH); // Cell is on path & is maze exit. (You have successfully completed the maze)
            return true;
        } else {    // Recursive case.
            // Attempt to find a path from each neighbour. Tentatively mark cell as on path.
            System.out.println("Trying " + "("+ x + ", " + y + ") to find a path");
            maze.recolor(x, y, PATH);

            if (findMazePath(x - 1, y) ||
                    findMazePath(x + 1, y) ||
                    findMazePath(x, y - 1) ||
                    findMazePath(x, y + 1)
            ) {
                return true;
            } else {
                System.out.println("Tried " + "("+ x + ", " + y + ") to find a path");
                maze.recolor(x, y, TEMPORARY);  // Dead end.
                return false;
            }
        }
    }

    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }

    private void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
}
