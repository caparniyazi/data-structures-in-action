package com.caparniyazi.ds.garbagecollector;

import java.util.ArrayList;
import java.util.List;

/**
 * The class to run and analyze G1 Garbage Collector.
 */
public class G1GCVerification {
    public static void main(String[] args) {
        createRecurringObjects();
    }

    private static void createRecurringObjects() {
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 100000; j++) {
                A a = new A();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class A {
    List<Double> list = new ArrayList<>();
}