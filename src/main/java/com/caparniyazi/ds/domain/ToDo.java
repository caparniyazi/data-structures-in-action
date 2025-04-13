package com.caparniyazi.ds.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data annotation generates a default constructor if you don't have one,
 * and all the getters, setters, and overrides, such as toString() method.
 * <p/>
 * To use JPA and be compliant, it is necessary to declare the entity (@Entity) and the
 * primary key (@Id) from the domain model.
 */
@Data
@Entity(name = "todo")
public class ToDo {
    // Data fields
    @NotNull
    @Id
    private String id;

    @NotNull
    @NotBlank
    private String description;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", locale = "tr_TR")
    @Column(updatable = false, nullable = false)
    private LocalDateTime created;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss", locale = "tr_TR")
    private LocalDateTime modified;
    private boolean completed;

    public ToDo() {
        LocalDateTime now = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
        this.created = now;
        this.modified = now;
    }

    public ToDo(String description) {
        this();
        this.description = description;
    }
}
