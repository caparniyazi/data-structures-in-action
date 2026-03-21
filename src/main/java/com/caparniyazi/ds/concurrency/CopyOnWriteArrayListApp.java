package com.caparniyazi.ds.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
The CopyOnWrite classes are similar to the immutable object pattern, as a new underlying structure is created every time
the collection is modified. Unlike the immutable object pattern, though, the reference to the object stays the same
even while the underlying data is changed.
<p>
The CopyOnWrite classes can use a lot of memory, since a new collection structure needs to
be allocated anytime the collection is modified. They are commonly used in multithreaded
environment situations where reads are far more common than writes.
 */
public class CopyOnWriteArrayListApp {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4, 3, 52));

        for (Integer item : list) {
            System.out.println(item + " ");
            list.add(9);
        }

        System.out.println();
        System.out.println("Size: " + list.size());
        System.out.println(list);
    }
}
