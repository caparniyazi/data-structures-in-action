package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;

public class Operators {
    public static void main(String[] args) {
        Observable.just(63, 74, 77, 86, 67, 99, 100)
                .filter(x -> x > 75)
                .sorted()   //  downstream observable
                .subscribe(e -> System.out.println("Grade A with " + e));
    }
}
