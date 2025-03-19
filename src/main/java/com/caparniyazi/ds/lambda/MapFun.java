package com.caparniyazi.ds.lambda;

public class MapFun<K, V> {
    // Data fields
    private Entry[] entries;
    private int size;

    public MapFun(int size) {
        this.size = size;
        this.entries = new Entry[size];

        for (int i = 0; i < size; i++) {
            entries[i] = new Entry();
        }
    }

    private MapFun(Entry[] entries, int size) {
        this.size = size;
        this.entries = entries;
    }

    public Integer getHash(K key) {
        return key.hashCode() % size;
    }

    public MapFun<K, V> put(K key, V value) {
        int hash = getHash(key);

        // Check if there is a collision
        Entry existingEntry = entries[hash];

        if (isDuplicate(key)) {
            existingEntry.value = value;
        }
        Entry newEntry = new Entry(key, value);
        entries[hash] = newEntry;
        newEntry.next = existingEntry;

        return new MapFun<>(entries, size);
    }

    private boolean isDuplicate(K key) {
        boolean duplicate = false;
        Entry entry = entries[getHash(key)];

        while (entry != null) {
            if (key.equals(entry.key)) {
                duplicate = true;
            } else {
                entry = entry.next;
            }
        }

        return duplicate;
    }

    @SuppressWarnings("unchecked")
    public V getValue(K key) {
        V val = null;
        int hash = getHash(key);
        Entry entry = entries[hash];

        while (entry.next != null) {
            if (key.equals(entry.getKey())) {
                val = (V) entry.getValue();
                break;
            }

            entry = entry.next;
        }

        return val;
    }
    
    public void display() {
        for (Entry entry : this.entries) {
            System.out.println(entry);
        }
    }
}
