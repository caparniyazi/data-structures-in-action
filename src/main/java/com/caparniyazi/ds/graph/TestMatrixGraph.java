package com.caparniyazi.ds.graph;

import java.util.Iterator;

public class TestMatrixGraph {
    public static void main(String[] args) {
        System.out.println("===== UNDIRECTED GRAPH TEST =====");
        MatrixGraph undirected = new MatrixGraph(5, false);

        // Insert some edges
        undirected.insert(new Edge(0, 1, 3));
        undirected.insert(new Edge(1, 2, 5));
        undirected.insert(new Edge(2, 3, 2));
        undirected.insert(new Edge(3, 4, 7));

        // Print graph
        System.out.println(undirected);

        // Test edge queries
        System.out.println("isEdge(1,2): " + undirected.isEdge(1, 2));
        System.out.println("getEdge(1,2): " + undirected.getEdge(1, 2));
        System.out.println("isEdge(0,4): " + undirected.isEdge(0, 4));

        // Iterate over neighbors of 1
        System.out.println("\nEdges out of vertex 1:");
        for (Iterator<Edge> it = undirected.edgeIterator(1); it.hasNext(); ) {
            Edge e = it.next();
            System.out.println("  " + e);
        }

        System.out.println("\n===== DIRECTED GRAPH TEST =====");
        MatrixGraph directed = new MatrixGraph(5, true);

        // Insert edges
        directed.insert(new Edge(0, 1, 10));
        directed.insert(new Edge(0, 4, 2));
        directed.insert(new Edge(1, 3, 4));
        directed.insert(new Edge(3, 2, 8));

        // Print graph
        System.out.println(directed);

        // Test edge queries
        System.out.println("isEdge(0,4): " + directed.isEdge(0, 4));
        System.out.println("isEdge(4,0): " + directed.isEdge(4, 0)); // false in directed graph

        // Iterate edges from vertex 0
        System.out.println("\nEdges out of vertex 0:");
        for (Iterator<Edge> it = directed.edgeIterator(0); it.hasNext(); ) {
            Edge e = it.next();
            System.out.println("  " + e);
        }

        System.out.println("\n===== UNDIRECTED MAP-GRAPH TEST =====");
        MapGraph undirectedMg = new MapGraph(5, false);

        // Insert some edges
        undirectedMg.insert(new Edge(0, 1, 33));
        undirectedMg.insert(new Edge(1, 2, 55));
        undirectedMg.insert(new Edge(2, 3, 22));
        undirectedMg.insert(new Edge(3, 4, 77));

        // Print graph
        System.out.println(undirectedMg);
    }
}
