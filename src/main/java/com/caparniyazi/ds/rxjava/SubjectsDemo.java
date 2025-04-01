package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class SubjectsDemo {
    public static void main(String[] args) {
        @NonNull
        PublishSubject<String> subject = PublishSubject.create();
        Subject<String> serialized = subject.toSerialized();

        serialized.subscribe(System.out::println);
        serialized.subscribe(e -> System.out.println("Observer 2: " + e));

        serialized.onNext("Hello");
        serialized.onNext("World");
        serialized.onComplete();   // Observable is done with emitting the data.
    }
}
