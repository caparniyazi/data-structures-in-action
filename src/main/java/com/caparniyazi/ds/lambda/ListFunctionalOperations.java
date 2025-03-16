package com.caparniyazi.ds.lambda;

import java.util.*;

public class ListFunctionalOperations {
    public static void main(String[] args) {
        List<Movie> movies = Arrays.asList(
                new Movie("Spotlight", 2025, "Hollywood"),
                new Movie("Avengers: Infinity War", 2018, "Hollywood"),
                new Movie("Inception", 2010, "Hollywood"),
                new Movie("Forest Gump", 1994, "Hollywood"),
                new Movie("3 Idiots", 2009, "Bollywood"),
                new Movie("Beauty and the beast", 2017, "Hollywood"),
                new Movie("Slumdog Millionaire", 2008, "Bollywood")
        );

        // Traversal
        movies.forEach(System.out::println);

        // Sorting
        System.out.println("---------------------------------------------");
        movies.sort(Comparator.comparing(Movie::getReleaseYear).reversed());
        movies.forEach(System.out::println);

        // Filtering
        System.out.println("---------------------------------------------");
        movies.stream()
                .filter(movie -> movie.getIndustry().equalsIgnoreCase("Hollywood"))
                .forEach(System.out::println);

        // Mapping
        System.out.println("---------------------------------------------");
        movies.stream()
                .map(Movie::getName).forEach(System.out::println);

        // Reduce
        System.out.println("---------------------------------------------");
        Optional<String> moviesString = movies.stream()
                .map(Movie::getName)
                .reduce((m1, m2) -> m1.concat("|").concat(m2));
        System.out.println(moviesString);
    }
}
