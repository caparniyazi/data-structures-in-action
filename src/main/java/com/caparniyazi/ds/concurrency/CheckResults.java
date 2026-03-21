package com.caparniyazi.ds.concurrency;

public class CheckResults {
    // Data fields
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                CheckResults.counter++;
            }
        }).start();

        while (CheckResults.counter < 100) {
            System.out.println("Not reached yet");
            // Thread.sleep() method requests the current thread of execution rest for a specified number of millis.
            Thread.sleep(1000); // Implementing polling.
        }

        System.out.println("Reached!");
    }
}
