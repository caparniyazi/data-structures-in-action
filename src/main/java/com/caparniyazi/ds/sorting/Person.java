package com.caparniyazi.ds.sorting;

import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

@Getter
public class Person implements Comparable<Person> {
    // Data fields
    private final String lastName;
    private final String firstName;
    private final int birthDay;   // Represented by an integer from 1 to 366

    public Person(String lastName, String firstName, int birthDay) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDay = birthDay;
    }

    /**
     * Compares two Person objects based on names.
     * The result is based on last names if they are different;
     * otherwise, it is based on the first names.
     *
     * @param other the other Person.
     * @return A negative integer if this person's name precedes the other person's name;
     * 0 if the names are the same;
     * a positive integer if this person's name follows the other person's name.
     */
    @Override
    public int compareTo(Person other) {
        int result = this.lastName.compareTo(other.lastName);   // Using the last name as the primary key.

        if (result == 0) {
            result = this.firstName.compareTo(other.firstName); // Using the first name as the secondary key.
        }
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Person[] people = new Person[]{
                new Person("Çapar", "Hasan", 60),
                new Person("Çapar", "Mustafa", 51)};

        // Although the sort operation is O(n log n),
        // the comparison of two names is O(k), where k is the length of the shorter name.

        // Sort by the natural ordering.
        Arrays.sort(people);
        System.out.println(Arrays.toString(people));

        // Sort people based on the birthdays.
        Arrays.sort(people, new ComparePerson() {
        });
        System.out.println(Arrays.toString(people));

        // Passing a lambda expression as a Comparator.
        Arrays.sort(people, (p1, p2) -> p1.getBirthDay() - p2.getBirthDay());
        System.out.println(Arrays.toString(people));

        // Or
        Arrays.sort(people, Comparator.comparing(Person::getBirthDay));
        System.out.println(Arrays.toString(people));
    }
}
