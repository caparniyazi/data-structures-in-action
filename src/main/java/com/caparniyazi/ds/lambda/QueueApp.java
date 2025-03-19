package com.caparniyazi.ds.lambda;

public class QueueApp {
    public static void main(String[] args) {
        QueueFun<String> myQueue = QueueFun.queue();
        QueueFun<String> enqueue =
                myQueue.enqueue("Java")
                        .enqueue(" functional")
                        .enqueue(" data")
                        .enqueue(" structure");
        enqueue.forEach(System.out::println);
        System.out.println("----------------------");
        QueueFun<String> dequeue = enqueue.dequeue();
        dequeue.forEach(System.out::println);
        System.out.println("----------------------");
        String peek = dequeue.peek();
        System.out.println(peek);
        System.out.println("Is queue empty? " + dequeue.isEmpty());
        System.out.println("Queue size is: " + dequeue.size());
    }
}