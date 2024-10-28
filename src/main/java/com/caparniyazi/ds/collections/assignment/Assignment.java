package com.caparniyazi.ds.collections.assignment;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Assignment implements Comparable<Assignment> {
    // Data fields
    private String description;
    private LocalDate dueDate;

    @Override
    public int compareTo(Assignment other) {
        return dueDate.compareTo(other.dueDate);
    }
}
