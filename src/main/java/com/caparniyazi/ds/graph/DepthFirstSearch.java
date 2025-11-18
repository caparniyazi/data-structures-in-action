package com.caparniyazi.ds.graph;

import lombok.Getter;

import java.util.*;

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
    private Map<Integer, List<Integer>> treeChildren = new HashMap<>();

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
        buildTree();
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

    private void buildTree() {
        for (int i = 0; i < parent.length; i++) {

            if (parent[i] != -1) {
                treeChildren
                        .computeIfAbsent(parent[i], k -> new ArrayList<>())
                        .add(i);
            }
        }
    }

    public String prettyPrint(int root) {
        StringBuilder sb = new StringBuilder();
        printNode(sb, root, 0);
        return sb.toString();
    }

    private void printNode(StringBuilder sb, int node, int depth) {
        List<Integer> children = treeChildren.get(node);

        if (children != null && children.size() > 0) {

            for (int i = children.size() - 1; i >= 1; i--) {
                printNode(sb, children.get(i), depth + 1);
            }
            printNode(sb, children.get(0), depth + 1);
        }

        sb.append("  ".repeat(depth))
                .append(node)
                .append("\n");
    }
}
