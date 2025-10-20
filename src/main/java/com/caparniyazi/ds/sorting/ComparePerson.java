package com.caparniyazi.ds.sorting;

import java.util.Comparator;

public class ComparePerson implements Comparator<Person> {
    /**
     * Compare two person objects based on a birthday.
     *
     * @param left  the left-hand side of the comparison.
     * @param right the right-hand side of the comparison.
     * @return A negative integer if the left person's birthday precedes the right person's birthday;
     * 0 if the birthdays are the same;
     * a positive integer if the left person's birthday follows the right person's birthday.
     * <p/>
     * Comparing two birthdays is an O(1) operation.
     */
    @Override
    public int compare(Person left, Person right) {
        return left.getBirthDay() - right.getBirthDay();
    }
}
