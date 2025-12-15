package com.caparniyazi.ds.sealed;

/**
 * non-sealed:
 * Applied to a class or interface that extends a sealed class, indicating that it
 * can be extended by unspecified classes
 * <p>
 * Every class that directly extends a sealed class must specify exactly one of the following
 * three modifiers: final, sealed, or non-sealed.
 * </p>
 * <p>
 * The non-sealed modifier is used to open a sealed parent class to potentially unknown subclasses.
 * </p>
 */
public non-sealed class Panda extends Bear {
}
