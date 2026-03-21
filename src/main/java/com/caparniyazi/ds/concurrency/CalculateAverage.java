package com.caparniyazi.ds.concurrency;

import lombok.RequiredArgsConstructor;

/**
 * A class to pass information to your Runnable object to be used by the run() method.
 */
@RequiredArgsConstructor
public class CalculateAverage implements Runnable {
    // Data fields
    private final double[] scores;

    @Override
    public void run() {
        // Define work here that uses the scores object.
    }
}
