package com.caparniyazi.ds.graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A graph implementation using a two-dimensional array (Adjacency Matrix) to represent the graph.
 * <p>
 * For an unweighted graph, the entries in this matrix can be boolean values, where true represents the presence
 * of an edge and false its absence. Another popular method is to use value 1 for an edge and 0 for no edge.
 * Integer coding has benefits over the boolean approach for some graph algorithms that use matrix multiplication.
 * Instead of using Edge objects, an edge is indicated by the value 1.0, and the lack of an edge is indicated
 * by a blank space in a directed graph.
 * <p>
 * We can represent the vertices by integers from 0 up to, but not including,
 * |V|. (|V| means the cardinality of V, or the number of vertices in set V.)
 * </p>
 * <p>
 * If a graph is dense, the adjacency matrix representation is best, and if a graph is sparse, the
 * adjacency list representation is best.
 * </p>
 */
public class MatrixGraph implements Graph {
    // Data fields
    private final int numV; // The number of vertices.
    private final boolean directed; // An indicator of whether the graph is directed(true) or not(false).
    double[][] edges;   // The Adjacency Matrix.

    private class EdgeIter implements Iterator<Edge> {
        // Data fields
        private final int source;
        private int index;

        /**
         * Construct an EdgeIterator to iterate through the edges adjacent to a given vertex.
         *
         * @param source The source vertex.
         */
        public EdgeIter(int source) {
            this.source = source;
        }

        /**
         * Return true if there are more edges.
         *
         * @return true if there are more edges.
         */
        @Override
        public boolean hasNext() {
            while (index < getNumV()) {
                if (Double.POSITIVE_INFINITY != getEdgeValue(source, index)) {
                    return true;
                }
                index++;
            }
            return false;
        }

        @Override
        public Edge next() {
            if (index >= getNumV()) {
                throw new NoSuchElementException();
            }

            // Ensure that we move to a valid edge.
            // Technically, next() must never rely entirely on hasNext()
            // So, always check bounds in next() directly.
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int dest = index++;
            return new Edge(source, dest, getEdgeValue(source, dest));
        }

        /**
         * In ListGraph (adjacency lists), an iterator can remove an edge by removing an entry from a LinkedList.
         * <p>
         * But in MatrixGraph, every edge is represented by a cell in the matrix.
         * Removing an edge is simply: matrix[source][dest] = +∞;
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Constructors

    /**
     * Constructs a graph with the specified number of vertices and directionality.
     *
     * @param numV     The number of vertices.
     * @param directed The directionality flag.
     */
    public MatrixGraph(int numV, boolean directed) {
        this.numV = numV;
        this.directed = directed;
        edges = new double[numV][];

        if (directed) {
            // Initialize the full matrix.
            for (int i = 0; i < numV; i++) {
                edges[i] = new double[numV];

                for (int j = 0; j < numV; j++) {
                    edges[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        } else {
            // If the graph is undirected, then the matrix is symmetric and only the lower diagonal of the matrix needs to be saved.
            // Saves the init time (half the work), n * (n + 1) / 2 writes, instead of n^2.
            for (int i = 0; i < numV; i++) {
                edges[i] = new double[i + 1];

                // The Upper triangle is unused, so no need to touch edges[i][j] for j > i.
                for (int j = 0; j <= i; j++) {
                    edges[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
    }

    @Override
    public int getNumV() {
        return numV;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    /**
     * The iterator to the edges connected to a given vertex.
     *
     * @param source The source vertex.
     * @return The iterator to the edges connected to a given vertex.
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return new EdgeIter(source);
    }

    @Override
    public Edge getEdge(int source, int dest) {
        return new Edge(source, dest, getEdgeValue(source, dest));
    }

    private double getEdgeValue(int source, int dest) {
        if (isDirected()) {
            return edges[source][dest];
        } else {
            return (source >= dest) ? edges[source][dest] : edges[dest][source];
        }
    }

    /**
     * Inserts a new edge into the graph.
     *
     * @param edge The new edge.
     */
    @Override
    public void insert(Edge edge) {
        setEdgeValue(edge.getSource(), edge.getDest(), edge.getWeight());
    }

    /**
     * Method to set the value of an edge.
     *
     * @param source The source vertex.
     * @param dest   The dest vertex.
     * @param weight The weight.
     */
    private void setEdgeValue(int source, int dest, double weight) {
        if (isDirected()) {
            edges[source][dest] = weight;
        } else {
            if (source >= dest) {
                edges[source][dest] = weight;
            } else {
                edges[dest][source] = weight;
            }
        }
    }

    /**
     * Determine if an edge exists.
     *
     * @param source The source vertex.
     * @param dest   The destination vertex.
     * @return The edge between these two vertices.
     */
    @Override
    public boolean isEdge(int source, int dest) {
        return Double.POSITIVE_INFINITY != getEdgeValue(source, dest);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("MatrixGraph (directed=")
                .append(directed)
                .append(", vertices=")
                .append(numV)
                .append(")\n");

        // Header row
        sb.append("      ");
        for (int j = 0; j < numV; j++) {
            sb.append(String.format("%4d ", j));
        }
        sb.append("\n");

        // Each row of the matrix
        for (int i = 0; i < numV; i++) {
            sb.append(String.format("%2d [ ", i));

            for (int j = 0; j < numV; j++) {
                double weight = getEdgeValue(i, j);

                if (Double.POSITIVE_INFINITY == weight) {
                    sb.append(String.format("%4s ", "∞"));
                } else {
                    // Force integers to print cleanly (avoid ".0")
                    if (weight == (int) weight)
                        sb.append(String.format("%4d ", (int) weight));
                    else
                        sb.append(String.format("%4.1f ", weight));
                }
            }
            sb.append("]\n");
        }

        return sb.toString();
    }
}
