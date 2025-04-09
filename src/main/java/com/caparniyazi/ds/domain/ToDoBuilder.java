package com.caparniyazi.ds.domain;

import lombok.Getter;

/**
 * The factory class that creates a ToDo instance.
 */
public class ToDoBuilder {
    // Data fields
    @Getter
    private static ToDoBuilder instance = new ToDoBuilder();
    private String id;
    private String description = "";

    private ToDoBuilder() {
    }

    public ToDoBuilder withDescription(String description) {
        this.description = description;
        return instance;
    }

    public ToDoBuilder withId(String id) {
        this.id = id;
        return instance;
    }

    public ToDo build() {
        ToDo result = new ToDo(this.description);

        if (id != null) {
            result.setId(id);
        }

        return result;
    }
}
