package com.caparniyazi.ds.graph;

import lombok.Getter;

import java.util.Arrays;
import java.util.Iterator;

/**
 * The class to implement the depth-first search algorithm.
 */
public class DepthFirstSearch {
    // Data fields
    private final Graph graph;  // A reference to the graph being searched.
    private final int[] parent; // Array of parents in the depth-first search tree.
    private final boolean[] visited;  // Flag to indicate whether this vertex has been visited.
    @Getter
    private final int[] discoveryOrder;   // The array that contains each vertex in discovery order.
    @Getter
    private final int[] finishOrder;  // The array that contains each vertex in finish order.
    private int discoverIndex = 0;  // The index that indicates the discovery order.
    private int finishIndex = 0;  // The index that indicates the finish order.

    // Constructor

    /**
     * Constructs the depth-first search of a Graph starting at vertex 0 and
     * visiting the start vertices in ascending order.
     *
     * @param graph The Graph object.
     */
    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
        int n = graph.getNumV();
        ;
        parent = new int[n];
        visited = new boolean[n];
        discoveryOrder = new int[n];
        finishOrder = new int[n];
        Arrays.fill(parent, -1);

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                depthFirstSearch(i);
            }
        }
    }

    /**
     * Recursively depth-first search the graph starting at vertex current.
     *
     * @param current The start vertex.
     */
    public void depthFirstSearch(int current) {
        // Mark the current vertex visited.
        visited[current] = true;
        discoveryOrder[discoverIndex++] = current;
        // Examine each vertex adjacent to the current vertex.
        Iterator<Edge> iter = graph.edgeIterator(current);

        while (iter.hasNext()) {
            int neighbor = iter.next().getDest();

            // Process a neighbor that has not been visited.
            if (!visited[neighbor]) {
                // Insert (current, neighbor) into the depth-first search tree.
                parent[neighbor] = current;
                // Recursively apply the algorithm starting at the neighbor.
                depthFirstSearch(neighbor);
            }
        }
        // Mark current finished.
        finishOrder[finishIndex++] = current;
    }

}
