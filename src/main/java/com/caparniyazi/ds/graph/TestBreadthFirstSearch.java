package com.caparniyazi.ds.graph;

import java.util.Arrays;

public class TestBreadthFirstSearch {
    public static void main(String[] args) {
        MapGraph undirectedMg = new MapGraph(10, false);

        // Insert some edges
        undirectedMg.insert(new Edge(0, 1));
        undirectedMg.insert(new Edge(0, 3));
        undirectedMg.insert(new Edge(1, 2));
        undirectedMg.insert(new Edge(1, 4));
        undirectedMg.insert(new Edge(1, 6));
        undirectedMg.insert(new Edge(1, 7));
        undirectedMg.insert(new Edge(3, 2));
        undirectedMg.insert(new Edge(2, 8));
        undirectedMg.insert(new Edge(2, 9));
        undirectedMg.insert(new Edge(4, 5));
        undirectedMg.insert(new Edge(4, 6));
        undirectedMg.insert(new Edge(4, 7));

        BreadthFirstSearch app = new BreadthFirstSearch(undirectedMg);
        int[] parent = app.breadthFirstSearch(0);
        System.out.println(undirectedMg);
        System.out.println("The array that contains the predecessor of each vertex in the BFS tree:");
        System.out.println(Arrays.toString(parent));
        System.out.println(app.prettyPrintTree(0));
    }
}
