package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;
import java.util.concurrent.TimeUnit;

public class MergeAndConcat {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> src1 = Observable.just("Java", "TypeScript", "C#");
        Observable<String> src2 = Observable.just("ShellScript", "PL/SQL");
        src1.mergeWith(src2)
                .subscribe(System.out::println);

        System.out.println("-----------------");

        src1 = Observable.interval(1, TimeUnit.SECONDS).map(e -> "src1: " + e);
        src2 = Observable.interval(1, TimeUnit.SECONDS).map(e -> "src2: " + e);

        // The sources are running concurrently and so the emissions are interleaved.
        Observable.merge(src1, src2)
                .subscribe(System.out::println);

        // concat method preserves the order.
//        Observable.concat(src1, src2)
//                .subscribe(System.out::println);

        Thread.sleep(10000);
    }
}
