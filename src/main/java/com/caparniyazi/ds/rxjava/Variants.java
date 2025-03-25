package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class Variants {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Java", "TypeScript", "PL/SQL");
        Observable<String> source2 = Observable.empty();

        source.first("Programming Language")
                .subscribe(System.out::println);

        Single<String> singleSource = Single.just("Java");
        singleSource.subscribe(System.out::println);

        // Maybe is for 0 or 1 emission.
        source.firstElement().subscribe(System.out::println);

        source2.firstElement()
                .subscribe(System.out::println, e -> e.printStackTrace(), () -> System.out.println("Completed"));

        // Completable => does not emit any data.
        Completable completable = Completable.complete();
        completable.subscribe(() -> System.out.println("Completed"));

        Completable.fromRunnable(() -> System.out.println("A process is executing"))
                .subscribe(()-> System.out.println("The process executed sucessfully"));
    }
}
