package com.caparniyazi.ds.rxjava;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    // Data fields
    private int id;
    private String name;
    double salary;
    double rating;

    public Employee(int id, String name, double salary, double rating) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + " name=" + name + ", salary=" + salary + ", rating=" + rating + "]";
    }
}
