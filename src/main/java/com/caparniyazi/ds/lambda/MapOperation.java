package com.caparniyazi.ds.lambda;

import java.util.List;
import java.util.stream.Stream;

public class MapOperation {
    public static void main(String[] args) {
        List<Integer> collect = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .map(i -> i * 25)
                .toList();
        collect.forEach(System.out::println);

        List<Book> books = List.of(
                new Book("The Alchemist", "Paulo Coelho", "Adventure", 4.408789797),
                new Book("The Notebook", "Nicolas Sparks", "Romance", 4.10),
                new Book("Horror Cocktail", "Robert Bloch", "Horror", 2.67),
                new Book("House of Leaves", "Mark Z.Danielewski", "Horror", 4.10908908));

        books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase("Horror"))
                .filter(book -> book.getRating() > 3)
                .map(Book::getName)
                .forEach(System.out::println);

    }
}
