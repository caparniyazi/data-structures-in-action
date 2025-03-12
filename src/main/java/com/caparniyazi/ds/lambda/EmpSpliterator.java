package com.caparniyazi.ds.lambda;

import java.sql.Date;
import java.util.Spliterator;
import java.util.function.Consumer;

public class EmpSpliterator implements Spliterator<Emp> {
    // Data fields
    private final Spliterator<String> wordSpliterator;
    private int id;
    private String name;
    private char gender;
    private Date dob;
    private String city;
    private String designation;
    private Date joinDate;
    private double salary;


    public EmpSpliterator(Spliterator<String> wordSpliterator) {
        this.wordSpliterator = wordSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Emp> action) {
        if (this.wordSpliterator.tryAdvance(
                word -> this.id = Integer.parseInt(word)) &&
                this.wordSpliterator.tryAdvance(word -> this.name = word) &&
                this.wordSpliterator.tryAdvance(word -> this.gender = word.charAt(0)) &&
                this.wordSpliterator.tryAdvance(word -> this.dob = Date.valueOf(word)) &&
                this.wordSpliterator.tryAdvance(word -> this.city = word) &&
                this.wordSpliterator.tryAdvance(word -> this.designation = word) &&
                this.wordSpliterator.tryAdvance(word -> this.joinDate = Date.valueOf(word)) &&
                this.wordSpliterator.tryAdvance(word -> this.salary = Double.parseDouble(word))
        ) {
            action.accept(new Emp(this.id,
                    this.name,
                    this.gender,
                    this.dob,
                    this.city,
                    this.designation,
                    this.joinDate,
                    this.salary));
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<Emp> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return wordSpliterator.estimateSize() / 8;
    }

    @Override
    public int characteristics() {
        return wordSpliterator.characteristics();
    }
}
