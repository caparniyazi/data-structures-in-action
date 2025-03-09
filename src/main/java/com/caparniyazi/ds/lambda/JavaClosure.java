package com.caparniyazi.ds.lambda;

public class JavaClosure {
    public static void main(String[] args) {
        int val = 34;

        // This is possible only if "val" is final or effectively final!
        Task lambda = () -> {
            System.out.println(val);
            System.out.println("Task completed");
        };

        printValue(lambda);
    }

    private static void printValue(Task lambda) {
        lambda.doTask();
    }
}
