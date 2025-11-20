package com.caparniyazi.ds.graph;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * The class outputs the topological sort of a directed graph that contains no cycles.
 */
public class TopologicalSort {
    /**
     * The main method that performs the topological sort.
     *
     * @param args The command line arguments.
     * @pre args[0] contains the name of the file that contains the graph. It has no cycles.
     */
    public static void main(String[] args) {
        Graph theGraph = null;
        int numV = 9;   // Number of vertices (set to 9 for this sample graph)

        // Connect a Scanner to the input file.
        try (var scanner = new Scanner(new File(args[0]))) {
            // Load the graph data from a file.
            theGraph = new ListGraph(numV, true);
            theGraph.loadEdgesFromFile(scanner);
        } catch (IOException e) {
            System.err.println("I/O Exception while reading graph " + e.getMessage());
            System.exit(1);
        }

        // Perform the depth-first search.
        DepthFirstSearch dfs = new DepthFirstSearch(theGraph);
        // Get the finish order.
        int[] finishOrder = dfs.getFinishOrder();

        // Print the vertices in reverse finish order.
        System.out.println("The Topological Sort is: ");
        for (int i = numV - 1; i >= 0; i--) {
            System.out.print(finishOrder[i] + " ");
        }
    }
}
