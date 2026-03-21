package com.caparniyazi.ds.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ZooInfo {
    // Data fields

    /**
     * The main() method is an independent thread from the ExecutorService,
     * and it can perform tasks while the other thread is running.
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService service = null;

        // Running execute methods to perform async tasks.
        /**
         * A thread executor creates a non-daemon thread on the first task that is executed,
         * so failing to call shutdown() will result in your app never terminating.
         */
        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("begin");

            // The execute() method takes a Runnable lambda expression or instance
            // and completes the task asynchronously.
            // It is considered a “fire-and-forget”
            // method, as once it is submitted, the results are not directly available to the calling thread.
            service.execute(() -> System.out.println("Printing zoo inventory"));    // Executes a Runnable task at some point in the future.
            service.execute(() -> {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Printing record: " + i);
                }
            });
            service.execute(() -> System.out.println("Printing zoo inventory"));
            System.out.println("end");
            Future<String> future = service.submit(() -> {
                System.out.println("Hello");
                return "OK";
            });
            try {
                String result = future.get();   // Retrieve the result, waiting endlessly if it is not yet available!
                System.out.println("Future result is: " + result);
                System.out.println("Is submitted hello task done? " + future.isDone());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        } finally {
            if (service != null) {
                service.shutdown();

                // Note that shutdownNow() method returns a List<Runnable> of tasks that were submitted to the
                // thread executor, but that were never started.
//                service.shutdownNow();  // Attempts to stop all running tasks.
                System.out.println("Is service shutdown? " + service.isShutdown());
                if (service.isTerminated()) {
                    System.out.println("Task finished");
                } else {
                    System.out.println("Task is still running.");
                }
            }
        }
    }
}
