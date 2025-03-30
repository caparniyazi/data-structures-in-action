package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.time.LocalTime;

/**
 * flatMap() allows to process emissions in parallel.
 */
public class TheFlatMap {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws InterruptedException {
        Observable.just("Python", "PL/SQL", "F#", "C#", "C++")
                .flatMap(e -> Observable.just(e)
                        .subscribeOn(Schedulers.computation())
                        .map(TheFlatMap::compute))
                .subscribe(System.out::println);

        Thread.sleep(7000);
    }

    public static String compute(String element) {
        return element + " : Printed by: " + Thread.currentThread().getName() + " at: " + LocalTime.now();
    }
}
