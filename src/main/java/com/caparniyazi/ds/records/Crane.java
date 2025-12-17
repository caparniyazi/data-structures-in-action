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
 * <p>
 * Every field is inherently final and cannot be modified after it has been written in the constructor.
 * In order to "modify" a record, you have to make a new object and copy all of the data you want to preserve.
 * Just as interfaces are implicitly abstract, records are also implicitly final.
 * The final modifier is optional but assumed. Like enums, that means you can’t extend or inherit a record.
 * </p>
 * <p>
 * There are some good reasons to make data-oriented classes immutable.
 * Doing so can lead to less error-prone code, as a new object is established any time the data is modified.
 * It also makes them inherently thread-safe and usable in concurrent frameworks.
 * </p>
 * Records actually support many of the same features as a class:
 * Here are some of the members that records can include:
 * ■■ Overloaded and compact constructors
 * ■■ Instance methods including overriding any provided methods (accessors, equals(),
 * hashCode(), toString())
 * ■■ Nested classes, interfaces, annotations, enum, and records
 * <p>
 * While you can add methods, static fields, you cannot add instance
 * fields outside the record declaration, even if they are private.
 * Doing so defeats the purpose of using a record and could break immutability!
 * <pre>
 *    public record Crane(int numberEggs, String name) {
 *       private static int type = 10;
 *       public int size; // DOES NOT COMPILE
 *       private boolean friendly; // DOES NOT COMPILE
 *    }
 * </pre>
 * Records also do not support instance initializers. All initializations for the fields of a
 * record must happen in a constructor.
 * <p>
 * While it’s a useful feature that records support many of the same members
 * as a class, try to keep them simple. Like the POJOs and JavaBeans
 * they were born out of, the more complicated they get, the less usable
 * they become.
 *
 * @param numberEggs The number of eggs.
 * @param name       The name.
 */
public record Crane(int numberEggs, String name) {
    // May declare optional constructors, methods, and constants.

    /*
     * We can just declare the constructor the compiler normally inserts automatically, which
     * we refer to as the long constructor.
     * <p>
     * The compiler will not insert a constructor if you define one with the same list of parameters
     * in the same order.
     * Since each field is final, the constructor must set every field.
     *
     * @param numberEggs The number of eggs.
     * @param name       The name of crane.
     */
//    public Crane(int numberEggs, String name) {
//        if (numberEggs < 0) {
//            throw new IllegalArgumentException("numberEggs cannot be negative");
//        }
//
//        this.numberEggs = numberEggs;
//        this.name = name;
//    }

    /**
     * Luckily, the authors of Java added the ability to define a compact constructor for records. A
     * compact constructor is a special type of constructor used for records to process validation
     * and transformations succinctly. It takes no parameters and implicitly sets all fields.
     *
     * @param numberEggs The number of eggs.
     * @param name       The name of crane.
     */
    public Crane {
        // Custom validation.
        if (numberEggs < 0) {
            throw new IllegalArgumentException("numberEggs cannot be negative");
        }

        /*
         * Compact constructors give you the opportunity to apply transformations to any of the input
         * values.
         * Java calls the full constructor after the compact constructor but with the modified
         * constructor parameters.
         * While compact constructors can modify the constructor parameters, they cannot modify
         * the fields of the record.
         * It is highly recommended that you stick with the compact form unless you have a good reason not to.
         */
        name = name.toUpperCase();
        // Long constructor implicitly called at the end of compact constructor.
    }

    /**
     * You can also create overloaded constructors that take a completely different list of parameters.
     * They are more closely related to the long-form constructor
     * and don’t use any of the syntactical features of compact constructors.
     * <p>
     * The first line of an overloaded constructor must be an explicit call to another constructor
     * via this().
     * If there are no other constructors, the long constructor must be called.
     * Also, unlike compact constructors, you can only transform the data on the first line.
     * After the first line, all the fields will already be assigned, and the object is immutable.
     *
     * @param firstName The first name
     * @param lastName The last name
     */
    public Crane(String firstName, String lastName) {
        this(0, firstName + " " + lastName);
    }
}
