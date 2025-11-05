package com.caparniyazi.ds.tree;

import java.util.Random;

public class TestTreeTiming {
    public static void main(String[] args) {
        final int N = 100_000;
        Random rand = new Random(42);
        Integer[] data = new Integer[N];

        for (int i = 0; i < N; i++) {
            data[i] = rand.nextInt(N * 10);
        }
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        AVLTree<Integer> avl = new AVLTree<>();

        // Measure AVL insertion time
        long start = System.nanoTime();
        for (int i : data) {
            avl.add(i);
        }
        long avlInsertTime = System.nanoTime() - start;

        // Measure RBT insertion time
        start = System.nanoTime();
        for (int i : data) {
            rbt.add(i);
        }
        long rbtInsertTime = System.nanoTime() - start;

        // Random lookups
        int found = 0;
        start = System.nanoTime();

        for (int i = 0; i < N; i++) {
            if (avl.contains(rand.nextInt(N * 10))) {
                found++;
            }
        }
        long avlSearchTime = System.nanoTime() - start;

        found = 0;
        start = System.nanoTime();

        for (int i = 0; i < N; i++) {
            if (rbt.contains(rand.nextInt(N * 10))) {
                found++;
            }
        }
        long rbtSearchTime = System.nanoTime() - start;

        System.out.printf("Insertions: %,d elements%n", N);
        System.out.printf("AVL insert:  %.2f ms%n", avlInsertTime / 1e6);
        System.out.printf("RBT insert:  %.2f ms%n", rbtInsertTime / 1e6);
        System.out.printf("AVL search:  %.2f ms%n", avlSearchTime / 1e6);
        System.out.printf("RBT search:  %.2f ms%n", rbtSearchTime / 1e6);
    }
}
