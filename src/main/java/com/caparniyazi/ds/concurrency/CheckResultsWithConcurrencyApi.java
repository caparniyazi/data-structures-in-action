package com.caparniyazi.ds.concurrency;

import java.util.concurrent.*;

/**
 * Class that use Concurrency API without using Thread class directly.
 */
public class CheckResultsWithConcurrencyApi {
    // Data fields
    private static int counter;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = null;

        try {
            service = Executors.newSingleThreadExecutor();

            Future<?> result = service.submit(() -> {
                for (int i = 0; i < 500; i++) {
                    CheckResultsWithConcurrencyApi.counter++;
                }
            });
            result.get(10, TimeUnit.SECONDS);   // Waits at most 10 seconds,
            // throwing a TimeoutException if the task is not done!
        } catch (TimeoutException e) {
            System.out.println("Not reached in time.");
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}
