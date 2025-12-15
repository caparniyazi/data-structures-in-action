package com.caparniyazi.ds.sealed;

/**
 * Remember that interfaces are implicitly abstract and cannot be marked final. For
 * this reason, interfaces that extend a sealed interface can only be marked sealed or
 * non-sealed. They cannot be marked final.
 */
public non-sealed interface Floats extends Swims {
}
