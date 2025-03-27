package com.caparniyazi.ds.rxjava;

import io.reactivex.rxjava3.core.Observable;

import java.util.Comparator;
import java.util.List;

public class OperatorsInAction {
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

        observable.filter(e -> e.getRating() > 4.0)
                .sorted(Comparator.comparing(Employee::getRating).reversed())
                .map(Employee::getName)
                .take(4)
                //.toList()
                .subscribe(System.out::println);

        // List of monthly expenses.
        List<Integer> expenses = List.of(200, 500, 300, 340, 129, 234, 999, 1030, 3400, 890, 996, 789);
        Observable.fromIterable(expenses)
                .scan(Integer::sum)
                .subscribe(System.out::println);

        Observable.fromIterable(expenses)
                .reduce(Integer::sum)
                .subscribe(System.out::println);
    }
}
