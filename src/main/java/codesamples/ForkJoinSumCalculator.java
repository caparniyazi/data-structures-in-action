package codesamples;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Extends RecursiveTask to create a task usable with
 * the fork/join framework.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    // Data fields
    private final long[] numbers;   // The array of numbers to be summed.
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000L; // The size threshold for splitting into subtasks.

    // Create the main task
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    // To create subtasks of the main task.
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }

        return sum;
    }

    @Override
    protected Long compute() {
        int length = end - start;   // The size of the subarray summed by this task.

        if (length <= THRESHOLD) {
            return computeSequentially();
        }

        // Create a subtask to sum the first half of the array.
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, (start + length) / 2);

        // Asynchronously executes the newly created subtask using another thread of ForkJoinPool.
        leftTask.fork();

        // Creates a subtask to sum the second half of the array.
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, (start + length) / 2, end);

        // Executes the second subtask synchronously, potentially allowing further recursive splits.
        Long rightResult = rightTask.compute();

        // Reads the result of the first subtask - waiting if it is not ready.
        Long leftResult = leftTask.join();

        // Combines the results of the two subtasks.
        return leftResult + rightResult;
    }

    /**
     * Method performing a parallel sum of the first n natural numbers.
     *
     * @param n The number of natural numbers to sum.
     * @return The sum of first n natural numbers.
     */
    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);

        /*
        Use all the cores available to the JVM.
        More precisely, this constructor will use the value returned by Runtime.availableProcessors() method.

        Note that in a real-world application, it doesn’t make sense to use more than one
        ForkJoinPool. For this reason, what you typically should do is instantiate it only once
        and keep this instance in a static field, making it a singleton, so it could be conveniently
        reused by any part of your software.
        */
        return new ForkJoinPool().invoke(task);
    }
}
