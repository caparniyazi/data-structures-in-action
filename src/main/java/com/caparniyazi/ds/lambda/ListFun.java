package com.caparniyazi.ds.lambda;

import java.util.Collection;

/**
 * Class that implements a list data structure functional way.
 *
 * @param <T> The data type.
 */
public abstract class ListFun<T> {
    public abstract T head();

    public abstract ListFun<T> tail();

    public abstract boolean isEmpty();

    @SuppressWarnings("rawtypes")
    public static final ListFun NIL = new Nil();

    // The empty list representation
    private static class Nil<T> extends ListFun<T> {
        private Nil() {
        }

        @Override
        public T head() {
            return null;
        }

        @Override
        public ListFun<T> tail() {
            return null;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }

    /**
     * Class that constructs the ListFun<T>
     *
     * @param <T> The type
     */
    private static class Const<T> extends ListFun<T> {
        private final T head;
        private final ListFun<T> tail;

        Const(T element, ListFun<T> list) {
            this.head = element;
            this.tail = list;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public ListFun<T> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return length() == 0;
        }
    }

    public int length() {
        int length = 0;
        ListFun<T> temp = this;

        while (!temp.equals(NIL)) {
            length++;
            temp = temp.tail();
        }

        return length;
    }

    /**
     * Method to return the empty list.
     *
     * @param <T> The type parameter.
     * @return The empty list.
     */
    @SuppressWarnings("unchecked")
    public static <T> ListFun<T> list() {
        return NIL;
    }

    /**
     * Method to return a list for the given arguments.
     *
     * @param t   The var arguments.
     * @param <T> The type
     * @return a list for the given arguments.
     */
    @SafeVarargs
    public static <T> ListFun<T> list(T... t) {
        ListFun<T> temp = list();

        for (int i = t.length - 1; i >= 0; i--) {
            temp = new Const<>(t[i], temp);
        }

        return temp;
    }

    /**
     * Method to add an element to the existing list.
     *
     * @param element The element to add.
     * @return The new list.
     */
    public ListFun<T> addElement(T element) {
        // Always construct a new list.
        return new Const<>(element, this);
    }

    public void forEach(Consumer<? super T> consumer) {
        T current = this.head();
        ListFun<T> temp = this;

        for (int i = 0; i < length(); i++) {
            consumer.accept(current);
            temp = temp.tail();
            current = temp.head();
        }
    }

    /**
     * Method to remove the element from the list.
     *
     * @param element The element to remove.
     * @return The new list.
     */
    public ListFun<T> removeElement(T element) {
        if (this.length() == 0) {
            return this;
        } else if (element.equals(this.head())) {
            return tail();
        } else {
            ListFun<T> newTail = tail().removeElement(element);
            return new Const<>(head(), newTail);
        }
    }

    public ListFun<T> reverseList() {
        ListFun<T> list = list();
        T current = this.head();
        ListFun<T> temp = this;

        while (!temp.equals(NIL)) {
            list = list.addElement(current);
            temp = temp.tail();
            current = temp.head();
        }

        return list;
    }

    public static <T> ListFun<T> concat(ListFun<T> list1, ListFun<T> list2) {
        return list1.isEmpty() ? list2 : new Const<>(list1.head(), concat(list1.tail(), list2));
    }

    /**
     * Method to create a functional list out of any collection.
     *
     * @param list The collection argument.
     * @return the functional list containing all the elements of the given collection.
     */
    public ListFun<T> addAll(final Collection<? extends T> list) {
        ListFun<T> result = this;
        for (T element : list) {
            result = result.addElement(element);
        }

        return result;
    }
}
