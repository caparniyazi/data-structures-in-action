package com.caparniyazi.ds.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data annotation generates a default constructor if you don't have one,
 * and all the getters, setters, and overrides, such as toString() method.
 */
@Data
public class ToDo {
    // Data fields
    @NotNull
    private String id;

    @NotNull
    @NotBlank
    private String description;
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean completed;

    // Default constructor doing the field initialization.
    public ToDo() {
        LocalDateTime now = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.created = now;
        this.modified = now;
    }

    public ToDo(String description) {
        this.description = description;
    }
}
