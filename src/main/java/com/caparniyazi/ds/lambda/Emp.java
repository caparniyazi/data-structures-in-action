package com.caparniyazi.ds.lambda;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Emp implements Comparable<Emp> {
    // Data fields
    private int id;
    private String name;
    private char gender;
    private Date dob;
    private String city;
    private String designation;
    private Date joinDate;
    private double salary;

    public Emp(int id, String name, char gender, Date dob, String city, String designation, Date joinDate, double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.city = city;
        this.designation = designation;
        this.joinDate = joinDate;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Emp {id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", city=" + city + ", designation="
                + designation + ", joinDate=" + joinDate + ", salary=" + salary + "}";
    }

    @Override
    public int compareTo(Emp other) {
        return this.id - other.id;
    }
}
