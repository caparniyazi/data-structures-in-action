package com.caparniyazi.ds.graph;

import java.util.HashSet;

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
            vMinusS.add(i);
        }

        // Initialize pred and dist.
        pred[start] = -1;
        dist[start] = 0;

        for (int v : vMinusS) {
            pred[v] = start;
            dist[v] = graph.getEdge(start, v).getWeight();
        }

        // Main loop
        while (!vMinusS.isEmpty()) {
            // Find the vertex u in V-S with the smallest dist[u].
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
                    double weight = graph.getEdge(v, u).getWeight();

                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pred[v] = u;
                    }
                }
            }
        }
    }
}
