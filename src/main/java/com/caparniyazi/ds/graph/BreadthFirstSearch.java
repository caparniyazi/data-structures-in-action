package com.caparniyazi.ds.graph;

import java.util.*;

/**
 * The class to implement the breadth-first search algorithm.
 */
public class BreadthFirstSearch {
    // Data fields
    private final Graph graph;    // The graph to be searched.
    private final int[] parent;
    private final boolean[] identified;
    private final List<Integer> order;

    // Constructor
    public BreadthFirstSearch(Graph graph) {
        this.graph = graph;
        parent = new int[graph.getNumV()];
        identified = new boolean[graph.getNumV()];
        order = new ArrayList<>();
    }

    // Public API.

    /**
     * Perform a breadth-first search of a graph.
     *
     * @param start The arbitrary start vertex.
     * @return The array of parents.
     * @post The array parent will contain the predecessor of each vertex in the breadth-first search tree.
     */
    public int[] breadthFirstSearch(int start) {
        Queue<Integer> theQueue = new LinkedList<>();
        Arrays.fill(parent, -1);


        // Make the start vertex as identified, change it to light color, for example, and insert it into the queue.
        identified[start] = true;
        theQueue.offer(start);

        // Perform breadth-first search until done.
        // We need to be able to determine the first identified vertex that has not been visited so far that
        // we can visit it. To ensure that the identified vertices are visited in the correct sequence,
        // we will store them in a queue (FIFO). When we need a new node to visit, we remove it from the queue.

        while (!theQueue.isEmpty()) {
            // Take a vertex, current, out of the queue, and begin visiting it.
            int current = theQueue.remove();    // Take a vertex, u, out of the queue and visit u.
            // We can build a tree that represents the order in which vertices would be visited in a
            // breadth-first traversal, by attaching the vertices as they are identified to the vertex from
            // which they are identified.
            order.add(current);

            // Examine each vertex, neighbor, adjacent to the current.
            Iterator<Edge> iter = graph.edgeIterator(current);
            while (iter.hasNext()) {
                Edge edge = iter.next();
                int neighbor = edge.getDest();

                if (!identified[neighbor]) {    // If neighbor has not been identified
                    identified[neighbor] = true;    // Make it identified.
                    theQueue.offer(neighbor);   // Place it into the queue.

                    /*
                     Insert the edge (current, neighbor) into the tree by making current as the parent of neighbor.
                     In other words, parent[i] is the parent of vertex i.
                     The entry parent[0] is -1 because node 0 is the start vertex.
                     */
                    parent[neighbor] = current;
                }
            }   // Finished visiting current (u).
        } // Finished the breadth-first traversal.

        return parent;
    }

    public String prettyPrintTree(int start) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nBFS Tree starting at ").append(start).append("\n");

        for (int v = 0; v < graph.getNumV(); v++) {
            sb.append("Vertex ").append(v);

            if (!identified[v]) {
                sb.append(" (unreachable)").append("\n");
                continue;
            }

            sb.append("  parent=").append(parent[v]);
            sb.append("\n");
        }

        sb.append("\nBFS Order: ").append(order);
        return sb.toString();
    }
}
