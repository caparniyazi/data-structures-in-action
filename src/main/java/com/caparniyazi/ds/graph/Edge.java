package com.caparniyazi.ds.graph;

import lombok.Getter;

import java.util.Objects;

/**
 * An Edge represents a relationship between two vertices.
 */
@Getter
public class Edge {
    // Data fields
    private int source; // The source vertex for an edge.
    private int dest;   // The destination vertex for an edge.
    private double weight;   // the weight.

    // Constructors
    // Constructs an Edge from source to dest. Sets the weight to weight.
    public Edge(int source, int dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    // Constructors
    // Constructs an Edge from source to dest. Sets the weight to 1.0
    public Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
        this.weight = 1.0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;

        if (other == this) return true;
        if (this.getClass() != other.getClass()) return false;

        Edge otherEdge = (Edge) other;
        return this.source == otherEdge.source && this.dest == otherEdge.dest;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.source, this.dest);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[(");
        sb.append(source);
        sb.append(", ");
        sb.append(dest);
        sb.append("): ");
        sb.append(weight);
        sb.append("]");

        return sb.toString();
    }
}
