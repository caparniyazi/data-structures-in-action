package com.caparniyazi.ds.sealed;

/**
 * A sealed class is a class that restricts which other classes may directly
 * extend it. These are brand new to Java 17.
 * <p>
 * Seal indicates that a class or interface may only be extended/implemented by named classes or interfaces.
 * <p>
 * Permits, used with the sealed keyword to list the classes and interfaces allowed.
 * <p>
 * Sealed classes are commonly declared with the abstract modifier, although this is certainly not required.
 * </p>
 */
public abstract sealed class Bear permits Kodiak, Panda {
}
