package com.caparniyazi.ds.lambda;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CollectorsInAction {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = ResourceUtils.getFile("classpath:EmpData.txt").toPath();
        try (Stream<String> lines = Files.lines(path)) {
            Stream<String> words = lines.flatMap(line -> Arrays.stream(line.split(",")));
            Spliterator<String> wordSpliterator = words.spliterator();
            Spliterator<Emp> empSpliterator = new EmpSpliterator(wordSpliterator);
            Stream<Emp> employees = StreamSupport.stream(empSpliterator, false);
            List<Emp> empList = employees.toList();

            // Get all the emp names
            List<String> empNames = empList.stream().map(Emp::getName).toList();
            empNames.forEach(System.out::println);

            // Get all the emp designations
            Set<String> designations = empList.stream().map(Emp::getDesignation).collect(Collectors.toSet());
            designations.forEach(System.out::println);

            TreeSet<Emp> employeesSorted = empList.stream().collect(Collectors.toCollection(TreeSet::new));
            employeesSorted.forEach(System.out::println);

            Map<Integer, String> getNameById = empList.stream().collect(Collectors.toMap(Emp::getId, Emp::getName));
            System.out.println(getNameById);

            Map<Boolean, List<Emp>> partitionedByGender = empList.stream().collect(Collectors.partitioningBy(e -> e.getGender() == 'M'));
            System.out.println(partitionedByGender);

            List<Emp> allFemales = partitionedByGender.get(false);
            allFemales.forEach(System.out::println);

            Map<String, List<Emp>> groupByDesignation = empList.stream().collect(Collectors.groupingBy(Emp::getDesignation));
            System.out.println(groupByDesignation);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
