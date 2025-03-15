package com.caparniyazi.ds.lambda;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DownstreamCollectors {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = ResourceUtils.getFile("classpath:EmpData.txt").toPath();

        try (Stream<String> lines = Files.lines(path)) {
            Stream<String> words = lines.flatMap(line -> Arrays.stream(line.split(",")));
            Spliterator<String> wordSpliterator = words.spliterator();
            Spliterator<Emp> empSpliterator = new EmpSpliterator(wordSpliterator);
            Stream<Emp> employees = StreamSupport.stream(empSpliterator, false);
            List<Emp> empList = employees.toList();

            Map<String, Long> countByDesignation = empList.stream().collect(
                    Collectors.groupingBy(Emp::getDesignation, Collectors.counting()));
            System.out.println(countByDesignation);

            Map<String, Double> fundDistro = empList.stream().collect(
                    Collectors.groupingBy(Emp::getDesignation, Collectors.summingDouble(Emp::getSalary)));
            System.out.println(fundDistro);

            Map<String, Optional<Emp>> maxSalaryEmployees = empList.stream().collect(
                    Collectors.groupingBy(
                            Emp::getDesignation,
                            Collectors.maxBy(Comparator.comparing(Emp::getSalary))));
            System.out.println(maxSalaryEmployees);

            Map<String, Optional<Double>> maxSalaries = empList.stream().collect(
                    Collectors.groupingBy(
                            Emp::getDesignation,
                            Collectors.mapping(
                                    Emp::getSalary,
                                    Collectors.maxBy(Comparator.comparing(Function.identity())))));
            System.out.println(maxSalaries);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
