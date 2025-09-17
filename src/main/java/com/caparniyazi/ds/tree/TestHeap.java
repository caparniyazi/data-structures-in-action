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
    }
}
