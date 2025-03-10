package com.caparniyazi.ds.lambda;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class NumericStreams {
    public static void main(String[] args) {
        List<Book> books = List.of(
                new Book("The Alchemist", "Paulo Coelho", "Adventure", 4.408789797),
                new Book("The Notebook", "Nicolas Sparks", "Romance", 4.10),
                new Book("Horror Cocktail", "Robert Bloch", "Horror", 2.67),
                new Book("House of Leaves", "Mark Z.Danielewski", "Horror", 4.10908908));

        OptionalDouble average = books.stream()
                .mapToDouble(Book::getRating)
                .average();
        System.out.println(average.getAsDouble());

        int sum = IntStream.of(1, 2, 3)
                .sum();
        System.out.println(sum);

        OptionalInt max = IntStream.of().max();
        System.out.println(max);

        IntSummaryStatistics intSummaryStatistics = IntStream.of(1, 2, 34)
                .summaryStatistics();
        System.out.println(intSummaryStatistics);
    }
}
