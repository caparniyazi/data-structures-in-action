package com.caparniyazi.ds.lambda;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    // Data fields
    private String name;
    private int releaseYear;
    private String industry;

    public Movie(String name, int releaseYear, String industry) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "Movie [name=" + name + ", releaseYear=" + releaseYear + ", industry=" + industry + "]";
    }
}
