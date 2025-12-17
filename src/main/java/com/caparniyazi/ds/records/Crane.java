package com.caparniyazi.ds.records;

/**
 * A record is a special type of data-oriented class in which the compiler inserts the boilerplate code for us,
 * including method equals(), hashCode(), and toString().
 * <p>
 * Behind the scenes, it creates a constructor for us with the parameters in the same order in which they appear in
 * the record declaration.
 * For each field, it also creates an accessor as the field name, plus a set of parentheses.
 * Unlike traditional POJOs or JavaBeans, the methods don’t have the prefix get or is.
 * </p>
 * <p>
 * A record is ideal when a class is:
 * 1. Primarily data-carrying.
 * 2. Has immutable identity fields.
 * 3. May contain mutable internal state
 * 4. Benefits from generated equals(), hashCode(), and toString() methods.
 * </p>
 *
 * @param numberEggs The number of eggs.
 * @param name       The name.
 */
public record Crane(int numberEggs, String name) {
    // May declare optional constructors, methods, and constants.

    public static void main(String[] args) {
        var mommy = new Crane(4, "Cammy");
        System.out.println(mommy.numberEggs());
        System.out.println(mommy.name());
        System.out.println(mommy);
    }
}
