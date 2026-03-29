package codingpractice;

import java.util.concurrent.atomic.LongAdder;

/**
 * Thread-safe counter implementation using LongAdder.
 * Reduces contention by splitting counters.
 * Used internally in high-throughput systems.
 * <p/>
 * One or more variables that together maintain an initially zero long sum.
 * When updates (method add(long)) are contended across threads,
 * the set of variables may grow dynamically to reduce contention.
 * Method sum() (or, equivalently, longValue()) returns the current total combined across the variables maintaining the sum.
 * <p/>
 * This class is usually preferable to AtomicLong when multiple threads update a common sum that is used for purposes such as collecting statistics,
 * not for fine-grained synchronization control.
 * Under high contention, expected throughput of this class is significantly higher, at the expense of higher space consumption.
 */
public class SafeCounterWithLongAdder {
    // Data fields
    private LongAdder counter = new LongAdder();

    public void increment() {
        counter.increment();
    }

    public long get() {
        return counter.sum();
    }
}
