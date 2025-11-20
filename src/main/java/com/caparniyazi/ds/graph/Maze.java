package com.caparniyazi.ds.graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * The class to solve a maze represented as a graph.
 * It performs a breadth-first search of the graph to find the "shortest" path from the start vertex to
 * the end. It is assumed that the start vertex is 0, and the end vertex is numV -1.
 */
public class Maze {
    /**
     * The main method to solve the maze.
     *
     * @param args Command line argument.
     * @pre args[0] contains the name of the input file.
     */
    public static void main(String[] args) {
        int numV = 10;   // The number of vertices.
        Graph theMaze = null;

        // Load the graph data from the file.
        try (var scanner = new Scanner(new File(args[0]))) {
            theMaze = new ListGraph(numV, false);
            theMaze.loadEdgesFromFile(scanner);
        } catch (IOException e) {
            System.err.println("I/O Exception while reading graph " + e.getMessage());
            System.exit(1);
        }

        // Perform breadth-first search.
        BreadthFirstSearch bfs = new BreadthFirstSearch(theMaze);
        int[] parent = bfs.breadthFirstSearch(0);

        // Construct the path.
        Deque<Integer> thePath = new ArrayDeque<>();
        int v = numV - 1;

        // Trace backwards from destination until the source is reached.
        while (v != -1) {
            thePath.push(v);
            v = parent[v];
        }

        // Output the path
        System.out.println("The shortest path is: ");
        while (!thePath.isEmpty()) {
            System.out.println(thePath.pop());
        }
    }
}
