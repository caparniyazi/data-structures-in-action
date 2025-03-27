package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class ZipAndCombineLatest {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> src1 = Observable.interval(200, TimeUnit.MILLISECONDS).take(10);
        Observable<Long> src2 = Observable.interval(1, TimeUnit.SECONDS).take(10);

//        Observable.zip(src1, src2, (a, b) -> "Source 1: " + a + "Source 2: " + b)
//                .subscribe(System.out::println);

        Observable.combineLatest(src1, src2, (a, b) -> "Source 1: " + a + " Source 2: " + b)
                .subscribe(System.out::println);

        Thread.sleep(20000);
    }
}
