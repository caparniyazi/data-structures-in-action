package com.caparniyazi.ds.maps;

/**
 * A hash table for storing set elements using open addressing.
 * An adapter class with a KWHashMap<K, V> data field implemented by a HashtableOpen object.
 *
 * @param <K>
 */
public class HashsetOpen<K> {
    // Data fields
    private final KWHashMap<K, K> setMap = new HashtableOpen<>();

    /**
     * The adapter method, contains().
     *
     * @param key The key
     * @return true if the key is found, false otherwise.
     */
    public boolean containsKey(K key) {
        // HashtableOpen.get() returns null if the key is not found.

        return setMap.get(key) != null;
    }

    /**
     * The adapter method, add().
     *
     * @param key The key.
     * @return true if the key is not a duplicate.
     */
    public boolean add(K key) {
        // HashtableOpen.put() returns null if the key is not a duplicate.
        return setMap.put(key, key) != null;
    }

    /**
     * The adapter method, remove().
     *
     * @param key The key.
     * @return true if the key is found and removed.
     */
    public boolean remove(K key) {
        // HashtableOpen.remove() returns null if the key is not removed.
        return setMap.remove(key) != null;
    }
}
