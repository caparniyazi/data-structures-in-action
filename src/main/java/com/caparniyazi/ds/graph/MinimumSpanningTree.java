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
 * <p>
 * The overall cost of the algorithm is O(|V|^2).
 * By using a priority queue to hold the edges from S to V–S, we can improve on this algorithm.
 * We say that the algorithm is O(|E| log |V|).
 * For a dense graph, where |E| is approximately |V|^2, this is not an improvement; however,
 * for a sparse graph, where |E| is significantly less than |V|^2, it is. Furthermore, computer
 * science researchers have developed improved priority queue implementations that give
 * O(|E| |V| log |V|) or better performance.
 * <p>
 * This implementation of Prim's algorithm uses a priority queue to hold the edges from S to V-S.
 * The arrays p and d are not needed because the priority queue contains complete edges.
 * For a given vertex d, if a shorter edge is discovered, we do not remove the entry containing the longer edge
 * from the priority queue. We merely insert new edges as they are discovered.
 * Therefore, when the next shortest edge is removed from the priority queue,
 * it may have a destination that is no longer in V-S. In that case,
 * we continue to remove edges from the priority queue until we find one with a destination that is still in V-S.
 * <pre>
 * do {
 *   edge = pQ.remove();
 *   dest = edge.getDest();
 * } while (!vMinusS.contains(dest));
 * </pre>
 */
@SuppressWarnings("DuplicatedCode")
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

    /**
     * Trace the execution of Prim's algorithm to find the minimum spanning tree for the graphs shown:
     * <img src="./mst1.jpg">Path from Philadelphia to Chicago</img>
     * <img src="./Path1.jpg">Path from Philadelphia to Chicago</img>
     */

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

        g = new ListGraph(10, false);
        g.insert(new Edge(0, 1, 330.0));
        g.insert(new Edge(0, 2, 460.0));
        g.insert(new Edge(1, 3, 135.0));
        g.insert(new Edge(3, 2, 150.0));
        g.insert(new Edge(3, 4, 125.0));
        g.insert(new Edge(2, 4, 155.0));
        g.insert(new Edge(4, 5, 60.0));
        g.insert(new Edge(5, 6, 50.0));
        g.insert(new Edge(4, 6, 40.0));
        g.insert(new Edge(2, 8, 170.0));
        g.insert(new Edge(6, 7, 260.0));
        g.insert(new Edge(7, 9, 148.0));
        g.insert(new Edge(7, 7, 170));
        g.insert(new Edge(8, 9, 120.0));

        mst = MinimumSpanningTree.primsAlgorithm(g, 0);
        MinimumSpanningTree.printMST(mst);
    }
}
