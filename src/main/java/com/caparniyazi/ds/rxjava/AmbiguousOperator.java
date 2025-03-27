package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AmbiguousOperator {
    public static void main(String[] args) throws InterruptedException {
        // interval() methods will create separate threads.
        // Slower
        Observable<String> ob1 = Observable.interval(1, TimeUnit.SECONDS).take(10)
                .map(e -> "Obs 1: " + e);

        // Faster
        Observable<String> ob2 = Observable.interval(10, TimeUnit.MILLISECONDS).take(10)
                .map(e -> "Obs 2: " + e);

        // We get all the emissions from the observable 2, because it's the faster one.
        // So, if there are multiple sources emitting the same data,
        // and we'd like to reduce the execution time by only considering the faster one,
        // we use amb() operator.
        Observable.amb(Arrays.asList(ob1, ob2))
                .subscribe(System.out::println);

        Thread.sleep(11000);
    }
}
