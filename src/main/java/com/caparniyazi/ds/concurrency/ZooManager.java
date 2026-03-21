package com.caparniyazi.ds.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// The sample class showing the alternate version using concurrent hashmap and methods without synchronization.
// A purpose of the concurrent collection classes is to solve common memory inconsistency errors.
// A memory inconsistency error occurs when two threads have inconsistent views of what should be the same data.
// ConcurrentHashMap is ordering read/write access such that all access to the class is consistent.
public class ZooManager {
    //    private Map<String, Object> foodData = new HashMap<>();
    private Map<String, Object> foodData = new ConcurrentHashMap<>();

    public /*synchronized*/ void put(String key, String value) {
        foodData.put(key, value);
    }

    public /*synchronized*/ Object get(String key) {
        return foodData.get(key);
    }
}
