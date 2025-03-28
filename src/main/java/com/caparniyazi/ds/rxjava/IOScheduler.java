package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class IOScheduler {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> source = Observable.just("Java", "RxJava", "TypeScript", "C#", "PL/SQL")
                .subscribeOn(Schedulers.io());

        source.subscribe(e -> ioOperation());
        source.subscribe(e -> ioOperation());
        source.subscribe(e -> ioOperation());
        source.subscribe(e -> ioOperation());
        source.subscribe(e -> ioOperation());

        Thread.sleep(5000);
    }

    // A sort of calculation that does its computation in 1 second.
    public static void ioOperation() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Computation done by: " + Thread.currentThread().getName());
    }
}
