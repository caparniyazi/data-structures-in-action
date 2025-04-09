package com.caparniyazi.ds.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that holds any errors that arise with any request.
 */
@Getter
public class ToDoValidationError {
    // Data fields

    // Even if the errors' field is empty, it must be included.
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<String> errors = new ArrayList<>();
    private String errorMessage;

    public ToDoValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
