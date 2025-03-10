package com.caparniyazi.ds.lambda;

import java.util.ArrayList;
import java.util.List;

public class ParallelStreams {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(new Employee("Jale", 20000));
            list.add(new Employee("Remzi", 3000));
            list.add(new Employee("Turan", 15000));
            list.add(new Employee("Beren", 8000));
            list.add(new Employee("Selma", 200));
            list.add(new Employee("Kenan", 50000));
        }

        long startTime = System.currentTimeMillis();
        System.out.println("Performing sequentially: " + list.stream()
                .filter(e -> e.getSalary() > 1000)
                .count());
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken with Sequential: " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        System.out.println("Performing parallel: " + list.parallelStream()
                .filter(e -> e.getSalary() > 1000)
                .count());
        endTime = System.currentTimeMillis();
        System.out.println("Time taken with Parallel: " + (endTime - startTime));
    }
}
