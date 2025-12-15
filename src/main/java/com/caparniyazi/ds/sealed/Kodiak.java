package com.caparniyazi.ds.sealed;

/**
 * We often use "final" with sealed subclasses.
 * <p>
 * Every class that directly extends a sealed class must specify exactly one of the following
 * three modifiers: final, sealed, or non-sealed.
 * </p>
 * <p>
 * Just as with a regular class, the final modifier prevents the subclass Kodiak from being
 * extended further.
 * </p>
 */
public final class Kodiak extends Bear {
}
