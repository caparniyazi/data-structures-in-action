package com.caparniyazi.ds.maps;

import java.util.Arrays;

/**
 * A hash function hashes (converts) a number in a large range into a number in a smaller range.
 * This smaller range corresponds to the index numbers in an array.
 * An array into which data is inserted using a hash function is called a hash table.
 * <p/>
 * Hash table is a data structure that offers very fast insertion and searching.
 * No matter how many data items there are, insertion and searching (and
 * sometimes deletion) can take close to constant time: O(1).
 * In practice, this is just a few machine instructions.
 * Hash tables are significantly faster than trees, which operate in relatively fast
 * O(logN) time.
 * <p/>
 * Hash tables do have several disadvantages.
 * They’re based on arrays, and arrays are difficult to expand after they’ve been created.
 * For some kinds of hash tables, performance may degrade catastrophically when a table becomes too
 * full, so the programmer needs to have a fairly accurate idea of how many data items will need to be stored
 * (or be prepared to periodically transfer data to a larger hash table, a time-consuming process).
 * <p/>
 * Hash table implementation using open addressing.
 * In a hash table that uses open addressing, we represent the hash table as
 * an array of Entry objects (initial size is START_CAPACITY).
 *
 * @param <K>
 * @param <V>
 */
public class HashtableOpen<K, V> implements KWHashMap<K, V> {
    // Data fields

    /**
     * A hash table stores key-value pairs, so we will use an inner class Entry
     * in each hash table implementation with data fields key and value.
     *
     * @param <K>
     * @param <V>
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

    private Entry<K, V>[] table;    // The hash table entry
    // Empirical tests have shown that hash tables with a size that is a prime number
    // often give better results.
    // And the best way to reduce the probability of a collision is to increase the table size.
    private static final int START_CAPACITY = 101;
    private static final double LOAD_THRESHOLD = 0.75; // The max. load factor.
    private int numKeys;    // The number of keys in the table excluding the keys that were deleted.
    private int numDeletes; // The number of deleted keys.

    // The entry object DELETED is used to indicate that the Entry at a particular table element
    // has been deleted; a null reference indicates that a table element was never occupied.
    private final Entry<K, V> DELETED = new Entry<>(null, null);

    // Constructors
    @SuppressWarnings("unchecked")
    public HashtableOpen() {
        table = (Entry<K, V>[]) new Entry[START_CAPACITY];
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
        // Find the first table element that is empty, or the table element that contains the key.
        int index = find(key);

        // If the search is successful, return the value.
        if (table[index] != null) {
            return table[index].getValue();
        } else {
            return null;
        }
    }

    /**
     * In open addressing, when a data item can’t be placed at the index calculated by the
     * hash function, another location in the array is sought.
     * <p/>
     * Finds the first element that is empty or the table element that contains the key.
     * If an empty element was found,
     * <pre>
     *          insert the new item and increment numKeys.
     *          Check for the need to rehash. Return null.
     *      </pre>
     * If the key was found, replace the value associated with this table element and return the old value.
     *
     * @param key   The key of item being inserted.
     * @param value The value for this key.
     * @return Old value associated with this key if found; otherwise, null.
     * @post This key-value pair is inserted in the table and numKeys is incremented.
     * If the key is already in the table, its value is changed to the argument value and the
     * "numKeys" is not changed. If the LOAD_THRESHOLD is exceeded, the table is expanded.
     */
    @Override
    public V put(K key, V value) {
        // Find the first table element that is empty or the table element that contains the key.
        int index = find(key);

        // If an empty element was found, insert new entry.
        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            numKeys++;

            // Check whether rehash is needed.
            // Before calling method rehash, method put calculates the load factor by dividing the
            // number of filled slots by the table size. This is a simple computation, but if you forget
            // to cast the numerator or denominator to double, the load factor will be zero (because
            // of integer division), and the table will not be expanded. This will slow down the
            // performance of the table when it becomes nearly full, and it will cause an infinite loop
            // (in method find()) when the table is completely filled.
            double loadFactor = (double) (numKeys + numDeletes) / table.length;

            if (loadFactor > LOAD_THRESHOLD) {
                rehash();
            }

            return null;
        }

        // Assert: The table element that contains the key was found.
        // Replace value for this key.
        V oldValue = table[index].getValue();
        table[index].setValue(value);
        return oldValue;
    }

    /**
     * Expands(doubles) the table size when the load factor exceeds LOAD_THRESHOLD
     * and permanently removes deleted items.
     *
     * @pre The size of the table is doubled and is an odd integer.
     * Each non-deleted entry from the original table is reinserted into the expanded table.
     * The value of numKeys is reset to the number of items actually inserted; numDeletes is rest to 0.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        // Save a reference to the oldTable.
        Entry<K, V>[] oldTable = table;

        // Double the capacity of this table.
        table = (Entry<K, V>[]) new Entry[2 * oldTable.length + 1];

        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        numDeletes = 0;

        for (Entry<K, V> entry : oldTable) {
            if (entry != null && entry != DELETED) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Returns true if empty.
     *
     * @return true if empty.
     */
    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    /**
     * Find the first table element that is empty or the table element that contains the key.
     * If an empty element was found, return null.
     * If the key was found, remove this table element by setting it to reference DELETED,
     * increment numDeletes, and decrement numKeys. Return the value associated with this key.
     *
     * @param key The key of item being deleted.
     * @return null if an empty element found, otherwise,
     */
    @Override
    public V remove(Object key) {
        int index = find(key);

        if (table[index] != null) {
            V oldValue = table[index].getValue();
            table[index] = DELETED;
            numKeys--;
            numDeletes++;
            return oldValue;
        }

        return null;
    }

    /**
     * Returns the size of the hash table.
     *
     * @return The size.
     */
    @Override
    public int size() {
        return numKeys;
    }

    /**
     * (Probing means 'Araştırma' in Turkish)
     * In linear probing, we search sequentially for vacant cells.
     * If 5,421 is occupied, e.g., when we try to insert a data item there,
     * we go to 5,422, then 5,423, and so on incrementing the index until we find an empty cell.
     * This is called linear probing because it steps sequentially along the line of cells.
     * <p/>
     * Finds either the index of the target key or the index of the first empty slot in the search chain
     * using linear probing.
     * <p/>
     * Note that as you increment the table index, your table should wrap around (as in a circular
     * array) so that the element with subscript 0 “follows” the element with subscript
     * table.length() − 1.This enables you to use the entire table, not just the part with subscripts larger
     * than the hash code value, but it leads to the potential for an infinite loop.
     * If the table is full and the objects examined so far do not match the one you are
     * seeking, how do you know when to stop?
     * One approach would be to stop when the index value for the next probe is the same as the
     * hash code value for the object.
     * This means that you have come full circle to the starting value for the index.
     * A second approach would be to ensure that the table is never full by increasing its size
     * after an insertion if its occupancy rate exceeds a specified threshold.
     * <p/>
     * By expanding the table when the load factor exceeds the LOAD_THRESHOLD,
     * we ensure that there will always be an empty slot in the table.
     *
     * @param key The key of the target object.
     * @return The position of the target or the first empty slot if the target is not in the table.
     * @pre The table is not full.
     */
    private int find(Object key) {
        // Calculate the starting index.
        // Note that the method calls key's hash code.
        int index = key.hashCode() % table.length;

        if (index < 0) {
            index += table.length;  // Make it positive.
        }

        // Increment index until an empty slot is reached or the key is found.
        while (table[index] != null && !table[index].getKey().equals(key)) {
            index++;

            // Check for wraparound
            if (index >= table.length) {
                index = 0;  // Wrap around.
            }
        }

        return index;
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
    }
}
