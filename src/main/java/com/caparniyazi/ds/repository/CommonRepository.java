package com.caparniyazi.ds.repository;

import java.util.Collection;

/**
 * The interface that can be used as a base for any other persistence implementation.
 * This is just an example of how to create something that is extensible.
 *
 * @param <T>
 */
public interface CommonRepository<T> {
    T save(T domain);

    public Iterable<T> save(Collection<T> domains);

    public void delete(T domain);

    public T findById(String id);

    public Iterable<T> findAll();
}
