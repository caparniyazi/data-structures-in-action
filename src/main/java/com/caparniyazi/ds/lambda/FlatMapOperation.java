package com.caparniyazi.ds.lambda;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FlatMapOperation {
    public static void main(String[] args) throws FileNotFoundException {
        Stream<String> a = Stream.of("Hello ", "there! ");
        Stream<String> b = Stream.of("Learning ", "Java? ");
        Stream<Stream<String>> a1 = Stream.of(a, b);

        Stream<String> stringStream = Stream.of(a, b)
                .flatMap(s -> s);

        Path p = ResourceUtils.getFile("classpath:myFile.txt").toPath();
        try (Stream<String> lines = Files.lines(p)) {
            List<String> collect = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .toList();
            collect.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
