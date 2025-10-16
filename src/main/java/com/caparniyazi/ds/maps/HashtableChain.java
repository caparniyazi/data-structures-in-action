package com.caparniyazi.ds.maps;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Hashtable implementation using chaining.
 * We will represent the hash table as an array of linked lists.
 * <p/>
 * Time complexity on average: O(1) for get(), put() and remove() assuming even key distribution.
 * Worst-case: O(n) (if all keys hash to the same bucket).
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class HashtableChain<K, V> implements KWHashMap<K, V> {
    // Data fields

    /**
     * A hash table stores key-value pairs, so we will use an inner class Entry
     * in each hash table implementation with data fields key and value.
     *
     * @param <K> Key type
     * @param <V> Value type
     */
    private static class Entry<K, V> {
        // Data fields
        private final K key;    // The key
        private V value;    // The value

        /**
         * Creates a new key-value pair.
         *
         * @param key   The key
         * @param value The value.
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key
         *
         * @return The key.
         */
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         *
         * @return The value.
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         *
         * @param value The new value.
         * @return The old value.
         */
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        /**
         * Returns a string representation of this entry.
         *
         * @return "(key, value)"
         */
        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    /**
     * Table of chains (each chain is a LinkedList of entries).
     */
    private LinkedList<Entry<K, V>>[] table;    // The hash table.
    private int numKeys;    // The number of keys.
    private static final int CAPACITY = 101;    // The initial capacity of the table.

    /*
     Even though a hash table that uses chaining can store any number of elements in the same slot,
     we will expand the table if the number of entries becomes three times the number of slots
     (LOAD_THRESHOLD is 3.0) to keep the performance at a reasonable level.
     Load factor threshold (3.0) means average 3 entries per bucket.
     */
    private static final double LOAD_THRESHOLD = 3.0;  // The max. load factor.

    // Constructors
    @SuppressWarnings("unchecked")
    public HashtableChain() {
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[CAPACITY];
    }

    /**
     * Finds the first table element that is empty or the table element that contains the key.
     * If the table element found contains the key, return the value at this table element,
     * else, return null.
     *
     * @param key The key being sought.
     * @return The value associated with this key if found; otherwise, null.
     */
    @Override
    public V get(Object key) {
        // Compute the hash index for the key.
        int index = key.hashCode() % table.length;

        if (index < 0) {
            index += table.length;  // Make it positive.
        }

        if (table[index] == null) {
            return null;    // Key is not in the table.
        }

        // Search the list(bucket) at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.getKey().equals(key)) {
                return nextItem.getValue();
            }
        }
        // Assert: key is not in the table.
        return null;
    }

    /**
     * Finds the first element that is empty or the table element that contains the key.
     * If an empty element was found,
     * <pre>
     *  insert the new item and increment numKeys.
     *  Check for the need to rehash.
     *  Return null.
     * </pre>
     * If the key was found, replace the value associated with this table element and return the old value.
     *
     * @param key   The key of item being inserted.
     * @param value The value for this key.
     * @return The old value associated with this key if found; otherwise, null.
     * @post This key-value pair is inserted in the table and numKeys is incremented.
     * If the key is already in the table, its value is changed to the argument value
     * and "numKeys" is not changed.
     */
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;

        if (index < 0) {
            index += table.length;  // Make it positive.
        }

        if (table[index] == null) {
            table[index] = new LinkedList<>();  // Create a new linked list at table[index].
        }

        for (Entry<K, V> nextItem : table[index]) {
            // If the search is successful, replace the old value.
            if (nextItem.getKey().equals(key)) {
                V oldValue = nextItem.getValue();
                nextItem.setValue(value);
                return oldValue;
            }
        }
        // Assert: Key is not in the table, add new item.
        table[index].addFirst(new Entry<>(key, value));
        numKeys++;

        // Rehash if the load factor exceeded.
        if (numKeys >= LOAD_THRESHOLD * table.length) {
            rehash();
        }
        return null;
    }


    /**
     * Expands(doubles) the table size when the load factor exceeds the LOAD_THRESHOLD.
     *
     * @pre The size of the table is doubled and is an odd integer.
     * Each non-deleted entry from the original table is reinserted into the expanded table.
     * The value of numKeys is reset to the number of items actually inserted.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        // Save a reference to the oldTable.
        LinkedList<Entry<K, V>>[] oldTable = table;

        // Double the capacity of this table.
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[2 * oldTable.length + 1];
        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;

        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            if (bucket == null) {
                continue;
            }

            for (Entry<K, V> nextItem : bucket) {
                if (nextItem != null) {
                    put(nextItem.getKey(), nextItem.getValue());
                }
            }
        }
    }

    /**
     * Find the first table element that is empty or the table element that contains the key.
     * If an empty element was found, return null.
     * If the key was found, remove this table element by setting it to reference DELETED,
     * increment numDeletes, and decrement numKeys. Return the value associated with this key.
     *
     * @param key The key of item being deleted.
     * @return null if an empty element found, otherwise, remove this table element
     * by setting it to reference DELETED and return the value associated with this key.
     */
    @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;

        if (index < 0) {
            index += table.length;  // Make it positive.
        }
        LinkedList<Entry<K, V>> bucket = table[index];

        if (bucket == null) { // Key is not in the table.
            return null;
        }

        Iterator<Entry<K, V>> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (entry.getKey().equals(key)) {
                iterator.remove();
                numKeys--;

                if (bucket.isEmpty()) {
                    table[index] = null;    // Optional cleanup
                }
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    @Override
    public int size() {
        return numKeys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;

        for (LinkedList<Entry<K, V>> bucket : table) {
            if (bucket == null) {
                continue;
            }

            for (Entry<K, V> entry : bucket) {
                if (entry != null) {
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append(entry.getKey()).append("=").append(entry.getValue());
                    first = false;
                }
            }
        }

        sb.append("}");
        return sb.toString();
    }
}
