package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SingleScheduler {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> source = Observable.just("Java", "RxJava", "TypeScript", "C#", "PL/SQL")
                .subscribeOn(Schedulers.single());

        source.subscribe(e -> sensitiveTask());
        source.subscribe(e -> sensitiveTask());
        source.subscribe(e -> sensitiveTask());
        source.subscribe(e -> sensitiveTask());
        source.subscribe(e -> sensitiveTask());

        Thread.sleep(500000);
    }

    // Assume that the sensitive task is not thread-safe and thus it must be executed synchronously.
    // That's why we are using single scheduler/thread that will work for all the observers synchronously.
    public static void sensitiveTask() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Computation done by: " + Thread.currentThread().getName());
    }
}
