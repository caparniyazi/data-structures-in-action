package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class ConnectableObservable {
    public static void main(String[] args) throws InterruptedException {
        io.reactivex.rxjava3.observables.ConnectableObservable<Long> source =
                Observable.interval(1, TimeUnit.SECONDS).publish(); // Make it hot observable.
        source.connect();
        source.subscribe(System.out::println);

        Thread.sleep(10000);

        source.subscribe(System.out::println);
        Thread.sleep(10000);
    }
}
