package com.caparniyazi.ds.lambda;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    // Data fields
    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "name=" + name + ", salary=" + salary + '}';
    }
}
