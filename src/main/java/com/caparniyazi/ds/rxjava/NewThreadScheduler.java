package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewThreadScheduler {

    public static void main(String[] args) throws InterruptedException {
        Observable<String> source = Observable.just("Java", "RxJava", "TypeScript", "C#", "PL/SQL")
                .subscribeOn(Schedulers.newThread());

        source.subscribe(e -> task());
        source.subscribe(e -> task());
        source.subscribe(e -> task());
        source.subscribe(e -> task());
        source.subscribe(e -> task());

        Thread.sleep(5000);
    }

    // A sort of calculation that does its computation in 1 second.
    public static void task() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Computation done by: " + Thread.currentThread().getName());
    }
}

