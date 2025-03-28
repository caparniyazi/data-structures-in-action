package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ComputationScheduler {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> source = Observable.just("Java", "RxJava", "TypeScript", "C#", "PL/SQL")
                .subscribeOn(Schedulers.computation());

        source.subscribe(e -> compute());
        source.subscribe(e -> compute());
        source.subscribe(e -> compute());
        source.subscribe(e -> compute());
        source.subscribe(e -> compute());

        Thread.sleep(5000);
    }

    // A sort of calculation that does its computation in 1 second.
    public static void compute() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Computation done by: " + Thread.currentThread().getName());
    }
}
