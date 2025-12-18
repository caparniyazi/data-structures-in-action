package com.caparniyazi.ds.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // Defines whether the annotation is available at runtime.
public @interface FamilyBudget {
    String userRole() default "GUEST";

    int budgetLimit() default 0;
}
