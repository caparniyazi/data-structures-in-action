package com.caparniyazi.ds.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class SettingParallelism {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());

        // Setting the parallelism.
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
        System.out.println(ForkJoinPool.getCommonPoolParallelism());

        /*
         * For cpu-bound operations, the number of threads <= number of cores
         * For io-bound operations, the number of threads can be greater than the number of cores.
         */
        ForkJoinPool pool = new ForkJoinPool(2);    // Using two threads.
        List<Employee> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add(new Employee("Jale", 20000));
            list.add(new Employee("Remzi", 3000));
            list.add(new Employee("Turan", 15000));
            list.add(new Employee("Beren", 8000));
            list.add(new Employee("Selma", 200));
            list.add(new Employee("Kenan", 50000));
        }

        long count = pool.submit(() -> list.parallelStream().filter(e -> e.getSalary() > 1000).count()).get();
        System.out.println(count);
    }
}
