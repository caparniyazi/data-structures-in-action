package com.caparniyazi.ds.concurrency;

public class ReadInventoryThread extends Thread {
    @Override
    public void run() {
        System.out.println("Printing the inventory");
    }

    public static void main(String[] args) {
        (new ReadInventoryThread()).start();    // This starts the task in a seperate operating system thread.
    }
}
