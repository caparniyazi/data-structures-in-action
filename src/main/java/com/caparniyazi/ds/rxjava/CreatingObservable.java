package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.List;

public class CreatingObservable {
    public static void main(String[] args) {
        // create()
        Observable<Integer> source = Observable.create(
                emitter -> {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                    emitter.onComplete();
                }
        );

        source.subscribe(System.out::println);

        // just()
        Observable<Integer> just = Observable.just(1, 2, 3);
        just.subscribe(System.out::println);

        // fromIterable
        List<String> list = List.of("a", "b", "c");
        Observable<String> observable = Observable.fromIterable(list);
        observable.subscribe(System.out::println);

        // range()
        Observable<Integer> range = Observable.range(3, 10);
        range.subscribe(System.out::println);

        // defer()
        List<String> cities = new ArrayList<>();
        cities.add("İstanbul");
        cities.add("Ankara");

        Observable<String> cityObservable = Observable.defer(() -> Observable.fromIterable(cities));
        cityObservable.subscribe(System.out::println);
        cities.add("Malatya");
        cityObservable.subscribe(System.out::println);
    }
}
