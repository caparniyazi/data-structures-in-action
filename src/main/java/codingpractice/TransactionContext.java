package codingpractice;

import java.util.UUID;

/**
 * In distributed systems, ThreadLocal can store transaction IDs to ensure each thread
 * handles only one transaction at a time.
 */
public class TransactionContext {
    // Data fields
    private static ThreadLocal<String> trnId = ThreadLocal.withInitial(() -> UUID.randomUUID().toString());

    public static String getTrnId() {
        return trnId.get();
    }

    public static void clearTrn() {
        trnId.remove();
    }
}
