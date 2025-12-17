package com.caparniyazi.ds.sealed;

/**
 * Despite allowing indirect subclasses not named in Mammal, the list of classes that can
 * inherit Mammal is still fixed. If you have a reference to a Mammal object, it must be a Mammal,
 * Equine, or Zebra.
 */
public final class Zebra extends Equine {
}
