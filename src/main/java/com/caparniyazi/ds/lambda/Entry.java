package com.caparniyazi.ds.lambda;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Entry {
    // Data fields
    Object key;
    Object value;
    Entry next;

    public Entry(Object key, Object value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public Entry() {
        this.next = null;
    }

    @Override
    public String toString() {
        return "Entry [key=" + key + ", value=" + value + ", next=" + next + "]";
    }
}
