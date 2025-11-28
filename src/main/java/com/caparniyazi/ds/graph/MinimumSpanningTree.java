package com.caparniyazi.ds.graph;

import java.util.*;

/**
 * A spanning tree is a subset of the edges of a graph such that there is only one edge between
 * each vertex, and all the vertices are connected. If we have a spanning tree for a graph, then we
 * can access all the vertices of the graph from the start node. The cost of a spanning tree is the
 * sum of the weights of the edges. We want to find the minimum spanning tree or the spanning
 * tree with the smallest cost. For example, if we want to start up our own long-distance phone
 * company and need to connect the cities,
 * finding the minimum spanning tree would allow us to build the least expensive network.
 * <p>
 * During his career at Bell Laboratories, Robert Prim along with coworker Joseph Kruskal developed two different algorithms
 * for finding a minimum spanning tree (MST) in a weighted graph, a basic stumbling block in computer network design.
 * His self-named algorithm, Prim's algorithm, was originally discovered in 1930 by mathematician Vojtěch Jarník and
 * later independently by Prim in 1957. It was later rediscovered by Edsger Dijkstra in 1959.
 * It is sometimes referred to as the DJP algorithm or the Jarník algorithm.
 * <p>
 * In statistics, Kruskal's most influential work is his seminal contribution to the formulation of multidimensional scaling.
 * In computer science, his best known work is Kruskal's algorithm for computing the minimal spanning tree (MST) of a weighted graph.
 * The algorithm first orders the edges by weight and then proceeds through the ordered list adding an edge to the partial MST
 * if adding the new edge does not create a cycle.
 * Minimal spanning trees have applications to the construction and pricing of communication networks.
 */
public class MinimumSpanningTree {
    /**
     * Prim's Minimum Spanning Tree (MST) algorithm.
     *
     * @param graph The weighted graph to be searched.
     * @param start The start vertex.
     * @return An ArrayList of edges that forms the MST.
     */
    public static ArrayList<Edge> primsAlgorithm(Graph graph, int start) {
        ArrayList<Edge> result = new ArrayList<>();
        int numV = graph.getNumV();

        // Use a HashSet to represent V-S, with the remaining vertices.
        Set<Integer> vMinusS = new HashSet<>(numV);

        // Declare the priority queue.
        Queue<Edge> pq = new PriorityQueue<>(numV,
                Comparator
                        .comparingDouble(Edge::getWeight)
                        .thenComparingInt(Edge::getSource)
                        .thenComparingInt(Edge::getDest));

        // Initialize V-S
        for (int i = 0; i < numV; i++) {
            if (i != start) {
                vMinusS.add(i);
            }
        }

        int current = start;    // Initialize S with the start vertex. {0}

        // Main loop
        while (!vMinusS.isEmpty()) {
            // Update priority queue.
            var iter = graph.edgeIterator(current);

            while (iter.hasNext()) {
                Edge edge = iter.next();
                int dest = edge.getDest();

                if (vMinusS.contains(dest)) {
                    pq.add(edge);
                }
            }

            // Find the shortest edge whose source is in S and destination is in V-S.
            int dest;
            Edge edge;

            do {
                edge = pq.remove();
                dest = edge.getDest();
            } while (!vMinusS.contains(dest));

            // Take dest out of vMinusS.
            vMinusS.remove(dest);

            // Add edge to result
            result.add(edge);

            // Make this the current vertex.
            current = dest;
        }

        return result;
    }

    public static void printMST(List<Edge> mst) {
        System.out.println("Minimum Spanning Tree:");
        double total = 0;

        for (Edge e : mst) {
            System.out.printf("%d -- %d  (%.2f)%n",
                    e.getSource(),
                    e.getDest(),
                    e.getWeight());
            total += e.getWeight();
        }

        System.out.printf("Total weight: %.2f%n", total);
    }

    public static void main(String[] args) {
        ListGraph g = new ListGraph(6, false);

        g.insert(new Edge(0, 1, 6));
        g.insert(new Edge(0, 2, 1));
        g.insert(new Edge(0, 3, 5));

        g.insert(new Edge(1, 2, 5));
        g.insert(new Edge(1, 4, 3));

        g.insert(new Edge(2, 3, 5));
        g.insert(new Edge(2, 4, 6));
        g.insert(new Edge(2, 5, 4));

        g.insert(new Edge(3, 5, 2));

        g.insert(new Edge(4, 5, 5));

        ArrayList<Edge> mst = MinimumSpanningTree.primsAlgorithm(g, 0);
        MinimumSpanningTree.printMST(mst);
    }
}
