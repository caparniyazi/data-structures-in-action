package com.caparniyazi.ds.lambda;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ReadingObjectsFromFile {
    public static void main(String[] args) throws FileNotFoundException {
        Path path = ResourceUtils.getFile("classpath:Books.txt").toPath();
        // We need a spliterator that reads the file object by object.
        try (Stream<String> lines = Files.lines(path);) {
            Spliterator<String> baseSpliterator = lines.spliterator();

            // Now build the book spliterator on it.
            Spliterator<Book> bookSpliterator = new BookSpliterator(baseSpliterator);
            Stream<Book> stream = StreamSupport.stream(bookSpliterator, false);
            stream.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
