package com.caparniyazi.ds.lambda;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    // Data fields
    private String name;
    private String author;
    private String genre;
    private double rating;

    public Book(String name, String author, String genre, double rating) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                '}';
    }
}
