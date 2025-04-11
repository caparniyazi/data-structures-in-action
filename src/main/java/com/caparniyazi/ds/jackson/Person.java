package com.caparniyazi.ds.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
public class Person {
    // Getters & Setters
    // Data fields
    private String name;
    /*
     * The @JsonFormat annotation allows you to specify the date pattern you prefer
     * (e.g., dd.MM.yyyy), providing you with control over the format of your LocalDate
     * fields during both serialization (converting an object into JSON)
     * and deserialization (converting JSON into an object).
     */
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthDate;

    public Person() {
    }

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", birthDate=" + birthDate + "]";
    }
}