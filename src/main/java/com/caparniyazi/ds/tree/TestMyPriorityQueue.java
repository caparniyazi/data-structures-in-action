package com.caparniyazi.ds.tree;

public class TestMyPriorityQueue {
    public static void main(String[] args) {
        MyPriorityQueue<String> myPriorityQueue = new MyPriorityQueue<>();
        myPriorityQueue.offer("this");
        myPriorityQueue.offer("is");
        myPriorityQueue.offer("the");
        myPriorityQueue.offer("house");
        myPriorityQueue.offer("that");
        myPriorityQueue.offer("jack");
        myPriorityQueue.offer("built");

        myPriorityQueue.printTree();

        // Exchange the order of arrival of the first and
        // last words and build the new heap.
        myPriorityQueue = new MyPriorityQueue<>();
        myPriorityQueue.offer("built");
        myPriorityQueue.offer("is");
        myPriorityQueue.offer("the");
        myPriorityQueue.offer("house");
        myPriorityQueue.offer("that");
        myPriorityQueue.offer("jack");
        myPriorityQueue.offer("this");

        myPriorityQueue.printTree();

        MyPriorityQueue<Integer> myq = new MyPriorityQueue<>();
        myq.offer(15);
        myq.offer(25);
        myq.offer(10);
        myq.offer(33);
        myq.offer(55);
        myq.offer(47);
        myq.offer(82);
        myq.offer(90);
        myq.offer(18);

        myq.printTree();

        // Remove 18
        myq.remove(18);
        myq.printTree();

        /*
        Show the printer queue after receipt of the following documents:
        timestamp   size
        1100        256
        1101        512
        1102        64
        1103        96
         */

        MyPriorityQueue<PrintDocument> mypq = new MyPriorityQueue<>();
        mypq.offer(new PrintDocument(256, 1100));
        mypq.offer(new PrintDocument(512, 1101));
        mypq.offer(new PrintDocument(64, 1102));
        mypq.offer(new PrintDocument(96, 1103));
        mypq.printTree();
    }
}
