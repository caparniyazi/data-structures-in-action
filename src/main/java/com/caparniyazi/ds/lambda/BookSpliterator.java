package com.caparniyazi.ds.lambda;

import java.util.Spliterator;
import java.util.function.Consumer;

// Our custom spliterator class.
public class BookSpliterator implements Spliterator<Book> {
    // Data fields
    private String name;
    private String author;
    private String genre;
    private double rating;
    private final Spliterator<String> baseSpliterator;

    public BookSpliterator(Spliterator<String> baseSpliterator) {
        this.baseSpliterator = baseSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Book> action) {
        if (this.baseSpliterator.tryAdvance(name -> this.name = name) &&
                this.baseSpliterator.tryAdvance(author -> this.author = author) &&
                this.baseSpliterator.tryAdvance(genre -> this.genre = genre) &&
                this.baseSpliterator.tryAdvance(rating -> this.rating = Double.parseDouble(rating))) {

            action.accept(new Book(this.name, this.author, this.genre, this.rating));
            return true;
        }

        return false;
    }

    @Override
    public Spliterator<Book> trySplit() {
        // We do not want to process in parallel, so we don't implement it.
        return null;
    }

    @Override
    public long estimateSize() {
        return baseSpliterator.estimateSize() / 4;  // Since 4 lines make up the book object.
    }

    @Override
    public int characteristics() {
        return baseSpliterator.characteristics();
    }
}
