package com.caparniyazi.ds.graph;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TestDepthFirstSearch {
    public static void main(String[] args) {
        Graph g = null;
        int n = 7;

        try (var scanner = new Scanner(new File(args[0]))) {
            g = new ListGraph(n, true);
            g.loadEdgesFromFile(scanner);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(1); // Error
        }

        // Perform depth-first search.
        DepthFirstSearch dfs = new DepthFirstSearch(g);
        int[] dOrder = dfs.getDiscoveryOrder();
        int[] fOrder = dfs.getFinishOrder();
        System.out.println("Discovery and finish order:");

        for (int i = 0; i < n; i++) {
            System.out.println(dOrder[i] + " " + fOrder[i]);
        }
    }
}
