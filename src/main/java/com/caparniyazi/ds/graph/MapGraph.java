package com.caparniyazi.ds.graph;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * We can achieve the performance benefits of both the ListGraph and MatrixGraph by making
 * a slight modification to the ListGraph.
 */
public class MapGraph implements Graph {
    // Data fields
    private final int numV; // The number of vertices.
    private final boolean directed; // An indicator of whether the graph is directed(true) or not(false).
    /**
     * An array of maps to contain the edges that originate with each vertex.
     */
    Map<Integer, Edge>[] edges;
    // Constructors

    /**
     * Constructs a graph with the specified number of vertices and
     * directionality.
     *
     * @param numV     The number of vertices.
     * @param directed The directionality flag.
     */
    @SuppressWarnings("unchecked")
    public MapGraph(int numV, boolean directed) {
        this.numV = numV;
        this.directed = directed;
        edges = new Map[numV];

        for (int i = 0; i < numV; i++) {
            edges[i] = new LinkedHashMap<>();
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
     * @return The iterator object of the map's collection.
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return edges[source].values().iterator();
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
        return edges[source].get(dest);
    }

    /**
     * Inserts a new edge into the graph by adding the edge's data to the list of adjacent vertices
     * for that edge's source.
     * If the graph is not directed, it adds a new edge in the opposite direction
     * to the map of adjacent vertices for that edge's destination.
     *
     * @param edge The new edge.
     */
    @Override
    public void insert(Edge edge) {
        int source = edge.getSource();
        int dest = edge.getDest();
        double weight = edge.getWeight();

        edges[source].put(dest, edge);

        if (!directed) {
            Edge reverseEdge = new Edge(dest, source, weight);
            edges[dest].put(source, reverseEdge);
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
        return edges[source].containsKey(dest);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MapGraph (directed=")
                .append(isDirected())
                .append(", vertices=")
                .append(getNumV())
                .append(")\n");

        for (int i = 0; i < getNumV(); i++) {
            sb.append(i).append(" -> ");

            if (edges[i].isEmpty()) {
                sb.append("\n");
                continue;
            }

            for (Edge e : edges[i].values()) {
                sb.append("(")
                        .append(e.getDest())
                        .append(", w=")
                        .append(e.getWeight())
                        .append(") ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
