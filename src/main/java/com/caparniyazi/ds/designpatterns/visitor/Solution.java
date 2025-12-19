package com.caparniyazi.ds.designpatterns.visitor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    // Data fields
    private static int[] values;
    private static Color[] colors;
    private static Map<Integer, HashSet<Integer>> map;

    public static Tree solve() {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();  // Number of nodes.

            values = new int[n];
            colors = new Color[n];
            map = new HashMap<>(n);

            // Save values
            for (int i = 0; i < n; i++) {
                values[i] = scanner.nextInt();
            }

            // Save colors
            for (int i = 0; i < n; i++) {
                colors[i] = scanner.nextInt() == 0 ? Color.RED : Color.GREEN;
            }

            // Save edges.
            for (int i = 0; i < n - 1; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();

                // Edges are undirected.
                // Add 1st direction.
                HashSet<Integer> uNeighbors = map.get(u);

                if (uNeighbors == null) {
                    uNeighbors = new HashSet<>();
                    map.put(u, uNeighbors);
                }
                uNeighbors.add(v);

                // Add 2nd direction.
                HashSet<Integer> vNeighbors = map.get(v);
                if (vNeighbors == null) {
                    vNeighbors = new HashSet<>();
                    map.put(v, vNeighbors);
                }
                vNeighbors.add(u);
            }

            if (n == 1) {
                return new TreeLeaf(values[0], colors[0], 0);
            }

            TreeNode root = new TreeNode(values[0], colors[0], 0);
            addChildren(root, 1);
            return root;
        }
    }

    /**
     * Recursively adds children of a TreeNode.
     *
     * @param parent       The parent node.
     * @param parentNumber The parent node number.
     */
    private static void addChildren(TreeNode parent, int parentNumber) {
        for (Integer n : map.get(parentNumber)) {
            map.get(n).remove(parentNumber);  // Remove the opposite arrow direction.

            // Add child.
            HashSet<Integer> grandChildren = map.get(n);
            boolean childHasChild = grandChildren != null && !grandChildren.isEmpty();
            Tree tree;

            if (childHasChild) {
                tree = new TreeNode(values[n - 1], colors[n - 1], parent.getDepth() + 1);
            } else {
                tree = new TreeLeaf(values[n - 1], colors[n - 1], parent.getDepth() + 1);
            }
            parent.addChild(tree);

            if (tree instanceof TreeNode) {
                addChildren((TreeNode) tree, n);
            }
        }
    }

    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}
