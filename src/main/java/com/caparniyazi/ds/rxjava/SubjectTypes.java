package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.subjects.*;

public class SubjectTypes {
    public static void main(String[] args) {
//        Subject<String> subject = PublishSubject.create();
//        Subject<String> subject = ReplaySubject.create();
//        Subject<String> subject = BehaviorSubject.create();
        Subject<String> subject = AsyncSubject.create();
//        Subject<String> subject = UnicastSubject.create();  // Allows only a single observer.
        subject.subscribe(e -> System.out.println("Subscriber 1: " + e));

        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");

        subject.subscribe(e -> System.out.println("Subscriber 2: " + e));

        subject.onNext("d");
        subject.onNext("e");
        subject.onComplete();
    }
}
