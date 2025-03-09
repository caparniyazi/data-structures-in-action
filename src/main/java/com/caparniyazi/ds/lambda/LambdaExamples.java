package com.caparniyazi.ds.lambda;

public class LambdaExamples {
    public static void main(String[] args) {
        // Using a class implementing Runnable.
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        // Using anonymous Runnable class.
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread Executed!");
            }
        });
        thread2.start();

        // Using Lambda.
        Runnable thread3 = () -> System.out.println("Thread Executed!");
        new Thread(thread3).start();

        // Assigning function to the variable (passing behavior).
        MyFunctionalInterface myFunctionalInterface = () -> System.out.println("Hello world!");
        myFunctionalInterface.myMethod();

        // Call on the fly method
        onTheFly(() -> System.out.println("Hello world!"));
    }

    public static void onTheFly(MyFunctionalInterface myFunctionalInterface) {
        myFunctionalInterface.myMethod();
    }
}