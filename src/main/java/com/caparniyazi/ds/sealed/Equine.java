package com.caparniyazi.ds.sealed;

/**
 * Equine defines its own list of permitted subclasses.
 * Notice in this example that Zebra is an indirect subclass of Mammal but is not named in the Mammal class.
 */
public sealed class Equine extends Mammal permits Zebra {
}
