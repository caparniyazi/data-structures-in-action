package com.caparniyazi.ds.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * The factory class that easily creates a ToDoValidationError instance
 * with all the necessary info.
 */
public class ToDoValidationErrorBuilder {
    public static ToDoValidationError fromBindings(Errors errors) {
        ToDoValidationError error = new ToDoValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");

        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }

        return error;
    }
}
