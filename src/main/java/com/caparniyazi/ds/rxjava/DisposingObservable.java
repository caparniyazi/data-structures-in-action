package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class DisposingObservable {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
        Disposable d = source.subscribe(e -> System.out.println("Observer1: " + e));
        Thread.sleep(5000);
        d.dispose();

        source.subscribe(e -> System.out.println("Observer2: " + e));
        Thread.sleep(10000);
    }
}
