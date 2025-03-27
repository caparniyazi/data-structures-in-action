package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;

public class Grouping {
    public static void main(String[] args) {
        Observable<Employee> observable = Observable.just(
                new Employee(101, "Ahmet", 60000, 4.0),
                new Employee(123, "Mehmet", 94000, 4.7),
                new Employee(236, "Ekrem", 65000, 4.0),
                new Employee(155, "Elif", 85000, 4.4),
                new Employee(443, "Erva", 50000, 3.6),
                new Employee(127, "Selma", 85000, 4.5),
                new Employee(509, "Cengiz", 60000, 4.0),
                new Employee(344, "Celal", 94000, 4.7),
                new Employee(509, "Turan", 75000, 4.3),
                new Employee(344, "Mine", 55000, 3.7)
        );

        observable.groupBy(e -> e.getRating())
                .flatMapSingle(e -> e.toMultimap(key -> e.getKey(), emp -> emp.getName()))
                .subscribe(System.out::println);

    }
}
