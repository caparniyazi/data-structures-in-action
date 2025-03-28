package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SubscribeOn {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws InterruptedException {
        // The app is using computational scheduler since it's the closest to the source.
        Observable.just("Python", "PL/SQL", "F#", "C#", "C++")
                .subscribeOn(Schedulers.computation())
                .map(String::toUpperCase)
                .subscribeOn(Schedulers.newThread())
                .filter(e -> e.startsWith("P"))
                .subscribe(SubscribeOn::print);

        Thread.sleep(6000);
    }

    public static void print(String element) {
        System.out.println(element + " : Printed by : " + Thread.currentThread().getName());
    }
}
