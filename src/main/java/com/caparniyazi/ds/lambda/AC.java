package com.caparniyazi.ds.lambda;

// The Air Conditioner class
public class AC {
    // The various commands that can be invoked by the client through the invoker.
    public void turnOn() {
        System.out.println("Turning on AC");
    }

    public void turnOff() {
        System.out.println("Turning off AC");
    }

    public void incTemp() {
        System.out.println("Increasing temperature");
    }

    public void decTemp() {
        System.out.println("Decreasing temperature");
    }
}
