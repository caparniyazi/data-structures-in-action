package codingpractice;

import java.util.concurrent.atomic.AtomicLong;

/**
 * A thread-safe counter implementation.
 */
public class SafeCounter {
    AtomicLong counter = new AtomicLong(0);

    public long increment() {
        return counter.incrementAndGet();
    }

    public long get() {
        return counter.get();
    }
}
