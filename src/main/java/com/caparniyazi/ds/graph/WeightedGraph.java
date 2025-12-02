package com.caparniyazi.ds.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class WeightedGraph {
    /**
     * Dijkstra's Shortest Path Algorithm. Edsger Wybe Dijkstra was a Dutch computer scientist.
     * Dijkstra’s algorithm as stated is O(|V|^2).
     *
     * @param graph The weighted graph to be searched.
     * @param start The start vertex.
     * @param pred  The output array to contain the predecessors in the shortest path.
     * @param dist  The output array to contain the distance in the shortest path.
     */
    public static void dijkstrasAlgorithm(Graph graph, int start, int[] pred, double[] dist) {
        int numV = graph.getNumV();
        HashSet<Integer> vMinusS = new HashSet<>(numV);

        // Initialize V-S.
        for (int i = 0; i < numV; ++i) {
            if (i != start) {
                vMinusS.add(i);
            }
        }

        // Initialize pred and dist.
        pred[start] = -1;
        dist[start] = 0;

        for (int v : vMinusS) {
            pred[v] = start;
            var e = graph.getEdge(start, v);

            if (e != null) {
                dist[v] = e.getWeight();
            } else {
                dist[v] = Double.POSITIVE_INFINITY;
            }
        }

        // Main loop
        while (!vMinusS.isEmpty()) {
            // Find the value(vertex) u in V-S with the smallest dist[u].
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;

            for (int v : vMinusS) {
                if (dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }

            // Remove u from V-S.
            vMinusS.remove(u);

            // Update the distances.
            var iter = graph.edgeIterator(u);
            while (iter.hasNext()) {
                var e = iter.next();
                int v = e.getDest();

                if (vMinusS.contains(v)) {
                    double weight = e.getWeight();

                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pred[v] = u;
                    }
                }
            }
        }
    }

    public static List<Integer> getPath(int end, int[] pred) {
        LinkedList<Integer> path = new LinkedList<>();

        for (int v = end; v != -1; v = pred[v]) {
            path.addFirst(v);
        }
        return path;
    }

    /**
     * The method main to create a graph and find the shortest path for the given start and end vertices.
     * <img src="./Path1.jpg">Path from Philadelphia to Chicago</img>
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Graph graph = new ListGraph(10, false);
        int[] pred = new int[graph.getNumV()];
        double[] dist = new double[graph.getNumV()];

        graph.insert(new Edge(0, 1, 330.0));
        graph.insert(new Edge(0, 2, 460.0));
        graph.insert(new Edge(1, 3, 135.0));
        graph.insert(new Edge(3, 2, 150.0));
        graph.insert(new Edge(3, 4, 125.0));
        graph.insert(new Edge(2, 4, 155.0));
        graph.insert(new Edge(4, 5, 60.0));
        graph.insert(new Edge(5, 6, 50.0));
        graph.insert(new Edge(4, 6, 40.0));
        graph.insert(new Edge(2, 8, 170.0));
        graph.insert(new Edge(6, 7, 260.0));
        graph.insert(new Edge(7, 9, 148.0));
        graph.insert(new Edge(7, 7, 170));
        graph.insert(new Edge(8, 9, 120.0));

        dijkstrasAlgorithm(graph, 0, pred, dist);

        // The array dist[] shows the shortest distances from the start vertex to all other vertices.
        System.out.println("Shortest distances from the start vertex to all others: " + Arrays.toString(dist));

        System.out.println("The array pred[] can be used to determine the path");
        System.out.println(Arrays.toString(pred));

        System.out.println("Path 0 -> 7: " + getPath(7, pred));
    }
}
