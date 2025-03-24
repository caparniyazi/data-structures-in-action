package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;

public class CreatingObserver {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Red", "Green", "Blue");
        source.subscribe(System.out::println, System.err::println, () -> System.out.println("Done"));
        System.out.println();
        source.subscribe(System.out::println, System.err::println);
        System.out.println();
        source.subscribe(System.out::println);
    }
}
