package com.caparniyazi.ds.ai.util;

import lombok.Getter;

/**
 * Implements a thread with an additional flag indicating cancellation.
 *
 * @author Ruediger Lunde
 * @author Mike Stampone
 */
@Getter
public class CancellableThread extends Thread {
    // Data fields
    private volatile boolean cancelled = false;

    public CancellableThread() {
    }

    public CancellableThread(Runnable runnable) {
        super(runnable);
    }

    /**
     * Returns <code>true</code> if the current thread is canceled.
     *
     * @return <code>true</code> if the current thread is canceled.
     */
    public static boolean currIsCancelled() {
        if (Thread.currentThread() instanceof CancellableThread) {
            return ((CancellableThread) Thread.currentThread()).cancelled;
        }

        return false;
    }

    /**
     * Cancels this thread.
     */
    public void cancel() {
        cancelled = true;
    }
}
