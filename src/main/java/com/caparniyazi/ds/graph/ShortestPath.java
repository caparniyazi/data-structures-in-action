package com.caparniyazi.ds.graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;

/**
 * A* Algorithm implementation.
 * <p>
 * The A-Star algorithm was developed by Peter Hart, Nills Nilsson, and Bertram Raphael in 1968.
 * It was developed to enable a robot to find the shortest path to a goal with
 * obstacles between it and the goal.
 * The A-Star algorithm is significant because it is one of the first examples of a tree-pruning algorithm,
 * which is one of the staples of artificial intelligence.
 * Pruning a search tree enables a program to focus on the more likely solution paths, ignoring
 * those that are not productive, and can save considerable computational time.
 * Chess-playing programs that compete with humans depend on tree pruning heuristics to search through a
 * tree with millions of possible sequences of moves and countermoves to determine the best move to make.
 * Instead of searching the vertices in the order of closeness to the start, they are searched in the
 * order of closeness to the destination.
 * However, we do not know the actual distance from a vertex to the destination.
 * A heuristic function, or heuristic, is used to guesstimate the distance from each vertex
 * to the destination based on available data.
 * <p>
 * A-Star adds an additional array, f, to Dijkstra’s algorithm.
 * In this array, f[v] is the estimated distance from the start to the destination through vertex v.
 * It is the sum of d[v], the actual distance from the start to vertex v, and h[v], the distance
 * from vertex v to the destination calculated by the heuristic.
 * The next vertex to visit is selected based on the smallest value of f.
 * d[v] and f[v] can change as we explore new paths to a vertex v, but h[v] remains the same.
 */
@SuppressWarnings("DuplicatedCode")
public class ShortestPath {
    /**
     * A-Star (A*) Shortest-Path Algorithm.
     *
     * @param graph  The weighted graph to be searched.
     * @param start  The start vertex.
     * @param dest   The destination vertex.
     * @param h      The heuristic function.
     * @param pred   Output array to contain the predecessors in the shortest path.
     * @param dist   Output array to contain the distance in the shortest path.
     * @param fScore Output array to contain the estimated distance in the shortest path.
     */
    public static void aStarAlgorithm(
            Graph graph,
            int start,
            int dest,
            BiFunction<Integer, Integer, Double> h,
            int[] pred,
            double[] dist,
            double[] fScore) {
        int numV = graph.getNumV();
        Set<Integer> vMinusS = new HashSet<>(numV);

        // Initialize V–S.
        for (int i = 0; i < numV; i++) {

            if (i != start) {
                vMinusS.add(i);
            }
        }

        // Initialize pred and dist.
        for (int v : vMinusS) {
            pred[v] = start;
            var e = graph.getEdge(start, v);

            if (e != null) {
                dist[v] = e.getWeight();
                fScore[v] = dist[v] + h.apply(v, dest);
            } else {
                dist[v] = Double.POSITIVE_INFINITY;
                fScore[v] = Double.POSITIVE_INFINITY;
            }
        }

        // Main loop
        while (!vMinusS.isEmpty()) {
            // Find the value(vertex) u in V-S with the smallest dist[u].
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;

            for (int v : vMinusS) {

                if (fScore[v] < minDist) {
                    minDist = fScore[v];
                    u = v;
                }
            }

            // If u is the destination, return.
            if (u == dest) {
                return;
            }

            // Remove u from V-S.
            vMinusS.remove(u);

            // Update the distances.
            var iter = graph.edgeIterator(u);
            while (iter.hasNext()) {
                Edge e = iter.next();
                int v = e.getDest();

                if (vMinusS.contains(v)) {
                    double weight = e.getWeight();

                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        fScore[v] = dist[v] + h.apply(v, dest);
                        pred[v] = u;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try (var in = new Scanner(new FileReader(args[0]))) {
            double[][] coords = readCityCoordinates(in);
            int numV = coords.length;
            Graph graph = new ListGraph(numV, false);
            graph.loadEdgesFromFile(in);
            int start = 0;
            int dest = numV - 1;
            double[] dist = new double[numV];
            double[] fScore = new double[numV];
            int[] pred = new int[numV];

            // Euclidean straight-line distance heuristic.
            BiFunction<Integer, Integer, Double> h = (v, w) -> {
                double x1 = coords[v][0];
                double y1 = coords[v][1];
                double x2 = coords[w][0];
                double y2 = coords[w][1];
                double deltaX = x1 - x2;
                double deltaY = y1 - y2;
                return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            };

            // Call A* algorithm to define arrays pred, dist, and fScore.
            ShortestPath.aStarAlgorithm(graph, start, dest, h, pred, dist, fScore);

            // Construct the path
            Deque<Integer> path = new ArrayDeque<>();
            int prev = pred[dest];

            while (prev != start) {
                path.push(prev);
                prev = pred[prev];
            }
            path.push(prev);

            // Output the path
            System.out.print("The shortest path is: ");
            while (!path.isEmpty()) {
                System.out.print(path.pop() + " -> ");
            }

            System.out.println(dest);
            System.out.println("Its length is: " + fScore[dest]);
        } catch (FileNotFoundException e) {
            System.out.println(args[0] + " not found");
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Read the city coordinates from a Scanner and returns a 2D array:
     * coords[i][0] = x-coordinate of the city i.
     * Coords[i][1] = y-coordinate of the city i.
     * <p>
     * Expected input:
     * <pre>
     *  +----------------+
     *  n                +
     *  +----------------+
     *  x0     +     yo  +
     *  +------+---------+
     *  x1     +     y1  +
     *  +------+---------+
     *  + ...  +    ...  +
     *  +      +         +
     *  x[n-1] +  y[n-1] +
     *  +------+---------+
     * </pre>
     *
     * @param scanner The Scanner object.
     * @return The (x, y) coordinates of each city beginning with start city and ending with the destination city.
     */
    private static double[][] readCityCoordinates(Scanner scanner) throws IOException {
        String line = scanner.nextLine();

        if (line == null) {
            throw new IOException("Missing number of cities");
        }
        int numCities = Integer.parseInt(line);
        double[][] coords = new double[numCities][2];
        int i = 0;

        for (int j = 0; j < numCities; j++) {
            line = scanner.nextLine();
            String[] tokens = line.split("\\s+");

            if (tokens.length != 2) {
                throw new IOException("Bad coordinate line: " + line);
            }
            coords[i][0] = Double.parseDouble(tokens[0]);
            coords[i][1] = Double.parseDouble(tokens[1]);
            i++;
        }

        return coords;
    }
}
