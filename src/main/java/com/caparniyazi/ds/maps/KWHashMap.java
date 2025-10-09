package com.caparniyazi.ds.maps;

/**
 * Because we want to show more than one way to implement a hash table,
 * we introduce KWHashMap<K, V>.
 *
 * @param <K>
 * @param <V>
 */
public interface KWHashMap<K, V> {
    /*
     Returns the value associated with the specified key. Returns null if the key is not present.
     */
    V get(Object key);

    /*
    Associates the specified value with the specified key. Returns the previous value
    associated with the specified key, or null if there is no mapping for the key.
     */
    void put(K key, V value);

    /*
    Returns true if this table contains no key-value mappings.
     */
    boolean isEmpty();

    /*
     Removes the mapping for this key from this table if it is present (optional operation).
     Returns the previous value associated with the specified key, or null if there was no mapping.
     */
    V remove(Object key);

    /*
     Returns the size of the table.
     */
    int size();
}
