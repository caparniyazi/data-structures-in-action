package com.caparniyazi.ds.lambda;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

@BenchmarkMode(Mode.AverageTime)    // Measures the average time taken to the benchmarked method.
@OutputTimeUnit(TimeUnit.MILLISECONDS)
// Executes the benchmark 2 times to increase the reliability of results, with 4GB of heap space.
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelStreamBenchmark {
    // Data fields
    private static final long N = 10_000_000L;

//    @Benchmark
//    public long sequentialSum() {
//        return Stream.iterate(1L, i -> i + 1).limit(N)
//                .reduce(0L, Long::sum);
//    }

//    @Benchmark
//    public long iterativeSum() {
//        long result = 0;
//        for (long i = 1L; i <= N; i++) {
//            result += i;
//        }
//        return result;
//    }

    /*
     * This is quite disappointing: the parallel version of the summing method isn’t taking
     * any advantage of our quad-core CPU and is much slower than the sequential one.
     * (iterate is difficult to divide into independent chunks to execute in parallel.
     * Iterate is inherently sequential. This demonstrates how parallel programming
     * can be tricky and sometimes counterintuitive.)
     *
     * @return the sum using parallel version.
     *

    @Benchmark
    public long parallelSum() {
       return Stream.iterate(1L, i -> i + 1)
               .limit(N)
                .parallel()
                .reduce(0L, Long::sum);
    }
    */

    /*
     * 1. There is no boxing/unboxing for LongStream
     * 2. RangeClosed produces ranges of numbers which can be easily split into independent chunks.
     *
     * @return The sum.
     *

    @Benchmark
    public long rangedSum() {
        return LongStream.rangeClosed(1, N)
                .reduce(0L, Long::sum);
    }
    */

    /**
     * The method to be benchmarked.
     * This parallel reduction is faster than its sequential counterpart (rangedSum()),
     * because this time the reduction operation can be executed in parallel.
     *
     * @return The parallel sum of the first n natural numbers.
     */
    @Benchmark
    public long parallelRangedSum() {
        return LongStream.rangeClosed(1, N)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}
