package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.List;

public class ColdObservables {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        Observable<Integer> source = Observable.fromIterable(list);
        source.subscribe(System.out::println);
        addItem(list);   // List is modified
        source.subscribe(System.out::println);  // Observable will emit the updated data to all new subscriptions.
    }

    public static void addItem(List<Integer> list) {
        list.add(4);
    }

    // Hot observables start when we create it. All the observers get the same data.
    // If a new observer is subscribed,
    // it will get the data from the point at which the observable is currently emitting.
}
