package com.caparniyazi.ds.graph;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Interface to specify a Graph ADT.
 * A graph is a set of vertices and a set of edges.
 * Vertices are represented by integers from 0 ... n-1.
 * Edges are ordered pairs of vertices.
 * Each implementation of the Graph interface should provide a constructor that specifies
 * the number of vertices and whether the graph is directed.
 */
public interface Graph {
    int getNumV();  // Returns the number of vertices in the graph.

    boolean isDirected();   // Returns an indicator of whether the graph is directed.

    Iterator<Edge> edgeIterator(int source);  // Returns an iterator to the edges that originate from a given vertex.

    Edge getEdge(int source, int dest); // Gets the edge between two vertices.

    void insert(Edge e);    // Inserts a new edge into the graph.

    boolean isEdge(int source, int dest);   // Determines whether an edge exists from vertex source to dest.

    /**
     * Load the edges of a graph from the data in an input file.
     * The file should contain a series of lines, each line
     * with two or three data values. The first is the source,
     * the second is the destination, and the optional third
     * is the weight.
     *
     * @param scanner The scanner object.
     */
    default void loadEdgesFromFile(Scanner scanner) {
        String line;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] tokens = line.split("\\s+");
            int source = Integer.parseInt(tokens[0]);
            int dest = Integer.parseInt(tokens[1]);
            double weight = 1.0;

            if (tokens.length == 3) {
                weight = Double.parseDouble(tokens[2]);
            }
            insert(new Edge(source, dest, weight));
        }
    }
}
