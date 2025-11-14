package com.caparniyazi.ds.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A graph implementation using an array of lists to represent the edges.
 */
public class ListGraph implements Graph {
    // Data fields
    private final int numV; // The number of vertices.
    private final boolean directed; // An indicator of whether the graph is directed(true) or not(false).
    /**
     * An array of lists to contain the edges that originate with each vertex.
     */
    private final List<Edge>[] edges;
    // Constructors

    /**
     * Constructs a graph with the specified number of vertices and
     * directionality.
     *
     * @param numV     The number of vertices.
     * @param directed The directionality flag.
     */
    @SuppressWarnings("unchecked")
    public ListGraph(int numV, boolean directed) {
        this.numV = numV;
        this.directed = directed;
        edges = new List[numV];

        for (int i = 0; i < numV; i++) {
            edges[i] = new LinkedList<>();
        }
    }

    // Methods

    @Override
    public int getNumV() {
        return numV;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    /**
     * The Iterator<Edge> object that can be used to iterate
     * through the edges adjacent to a given vertex.
     *
     * @return The list iterator object.
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return edges[source].iterator();
    }

    /**
     * Get the edge between two vertices.
     *
     * @param source The source vertex.
     * @param dest   The dest vertex.
     * @return The edge between these two vertices or null if an edge does not exist.
     */
    @Override
    public Edge getEdge(int source, int dest) {
        // Since 0 is valid weight, we will use Double.POSITIVE_INFINITY to indicate
        // the absence of an edge for a weighted graph.
        // For an unweighted graph, we will use 1.0 to indicate the presence of an edge.
        Edge target = new Edge(source, dest, Double.POSITIVE_INFINITY);

        for (Edge edge : edges[source]) {

            if (edge.equals(target)) {
                return edge;    // Desired edge found, return it.
            }
        }
        // Assert: All edges for source checked.
        return null;    // Desired edge not found.
    }

    /**
     * Inserts a new edge into the graph by adding the edge's data to the list of adjacent vertices
     * for that edge's source.
     * If the graph is not directed, it adds a new edge in the opposite direction
     * to the list of adjacent vertices for that edge's destination.
     *
     * @param edge The new edge.
     */
    @Override
    public void insert(Edge edge) {
        edges[edge.getSource()].add(edge);

        if (!directed) {
            edges[edge.getDest()].add(new Edge(edge.getDest(), edge.getSource(), edge.getWeight()));
        }
    }

    /**
     * Determine whether an edge exists.
     *
     * @param source The source vertex.
     * @param dest   The dest vertex.
     * @return true if there is an edge from source to dest.
     */
    @Override
    public boolean isEdge(int source, int dest) {
        // Note that Edge.equals() does not check the edge weights, so
        // the weight parameter is not needed.
        return edges[source].contains(new Edge(source, dest));
    }
}
