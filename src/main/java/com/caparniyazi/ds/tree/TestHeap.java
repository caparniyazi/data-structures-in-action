package com.caparniyazi.ds.tree;

public class TestHeap {

    public static void main(String[] args) {
        Heap<Integer> theHeap = new Heap<>();
        theHeap.insert(6);
        theHeap.insert(18);
        theHeap.insert(29);
        theHeap.insert(20);
        theHeap.insert(28);
        theHeap.insert(39);
        theHeap.insert(8);
        theHeap.insert(37);
        theHeap.insert(26);
        theHeap.insert(76);
        theHeap.insert(32);
        theHeap.insert(74);
        theHeap.insert(89);
        theHeap.insert(66);

        theHeap.printTree();
        System.out.println("---------------------------------");
        theHeap.remove();
        theHeap.printTree();

        // Show the heap that would result from inserting the items 35, 20, 30, 50, 45, 60, 18, 25
        // in this sequence.
        System.out.println("----------------------------------");
        theHeap = new Heap<>();
        theHeap.insert(35);
        theHeap.insert(20);
        theHeap.insert(30);
        theHeap.insert(50);
        theHeap.insert(45);
        theHeap.insert(60);
        theHeap.insert(18);
        theHeap.insert(25);
        theHeap.printTree();
    }
}
