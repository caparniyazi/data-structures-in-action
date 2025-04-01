package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

/**
 * Subjects can be used as both observables and observers.
 * They multicast and they are hot. They can merge the emissions from multiple sources.
 */
public class Subjects {
    public static void main(String[] args) throws InterruptedException {
        Observable<Integer> source1 = Observable.just(5, 10, 15, 20)
                .subscribeOn(Schedulers.computation());

        Observable<Integer> source2 = Observable.just(50, 100, 150, 200)
                .subscribeOn(Schedulers.computation());

//        source1.subscribe(System.out::println);
//        source2.subscribe(System.out::println);

        @NonNull
        Subject<Object> subject = PublishSubject.create();
        subject.subscribe(System.out::println);

        source1.subscribe(subject);
        source2.subscribe(subject);

        Thread.sleep(10000);
    }
}
