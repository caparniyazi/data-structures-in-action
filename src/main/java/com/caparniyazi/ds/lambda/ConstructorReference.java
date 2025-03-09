package com.caparniyazi.ds.lambda;

import java.util.function.Function;

public class ConstructorReference {
    public static void main(String[] args) {
        Function<Runnable, Thread> threadGenerator = Thread::new;

        Runnable task1 = () -> System.out.println("Task 1 executed.");
        Runnable task2 = () -> System.out.println("Task 2 executed.");
        Thread thread1 = threadGenerator.apply(task1);
        thread1.start();
        Thread thread2 = threadGenerator.apply(task2);
        thread2.start();

        threadGenerator.apply(() -> System.out.println("Task 3 executed.")).start();
    }
}
