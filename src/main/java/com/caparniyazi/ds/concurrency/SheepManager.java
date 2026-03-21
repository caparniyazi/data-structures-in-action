package com.caparniyazi.ds.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Imagine that our zoo has a program to count sheep, preferably one that won’t put the
 * zoo workers to sleep! Each zoo worker runs out to a field, adds a new sheep to the flock,
 * counts the total number of sheep, and runs back to us to report the results.
 */
public class SheepManager {
    // Data fields
    // Atomic classes ensure that the data is consistent between workers and that no values are lost
    // due to concurrent modifications.
    private AtomicInteger sheepCount = new AtomicInteger(0);    // In this case we could have used int variable.
    private final Object lock = new Object();

    private void incAndReport() {
        synchronized (lock) {   // Synchronized block.  It could have been synchronized(this) as well.
            System.out.print((sheepCount.incrementAndGet()) + " ");
        }
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);

            // Using a monitor or lock to synchronize access.
            SheepManager manager = new SheepManager();
            for (int i = 0; i < 10; i++) {
                service.submit(() -> manager.incAndReport());
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}
