package com.caparniyazi.ds.lambda;

import java.util.Collection;
import java.util.Objects;

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
            temp = new Const<>(t[i], temp); // Prepend
        }

        return temp;
    }

    /**
     * Method to add(prepend) an element to the existing list.
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

    /**
     * Method to reverse the given functional list data structure.
     *
     * @return the functional list in reverse order.
     * Complexity: O(n)
     */
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

    /**
     * Method that returns the index of the first occurrence of the specified element in this list
     * or -1 if this list does not contain the element.
     *
     * @param element The element to search for.
     * @param from    Starting index
     * @return The index of the first occurrence of the specified element in this list or -1.
     */
    public int indexOf(T element, int from) {
        int index = 0;
        for (ListFun<T> list = this; !list.isEmpty(); list = list.tail(), index++) {

            if (index >= from && Objects.equals(list.head(), element)) {
                return index;
            }
        }

        return -1;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list.Shifts the element currently at that position (if any) and
     * any later elements to the right (adds one to their indices).
     *
     * @param index   The index at which the specified element is to be inserted.
     * @param element The element to be inserted.
     * @return The list with the inserted element.
     * @throws IndexOutOfBoundsException if the given index is out of bounds.
     */

    public ListFun<T> add(int index, T element) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("add(" + index + ", e)");
        }

        ListFun<T> list = list();   // Create an empty list.
        ListFun<T> tail = this;

        for (int i = index; i > 0; i--, tail = tail.tail()) {
            if (tail.isEmpty()) {
                throw new IndexOutOfBoundsException("add(" + index + ", e) on List of length " + length());
            }

            list = list.addElement(tail.head());
        }

        ListFun<T> result = tail.addElement(element);

        while (!list.equals(NIL)) {
            result = result.addElement(list.head());
            list = list.tail();
        }

        return result;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   The index of the element to replace.
     * @param element Element to be stored at the specified position.
     * @return The updated list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public ListFun<T> set(int index, T element) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("update(" + index + ", e) on Nil");
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("update(" + index + ", e)");
        }

        ListFun<T> list = list();
        ListFun<T> tail = this;

        for (int i = index; i > 0; i--, tail = tail.tail()) {
            if (tail.isEmpty()) {
                throw new IndexOutOfBoundsException("update(" + index + ", e) on List of length " + length());
            }

            list = list.addElement(tail.head());
        }

        if (tail.isEmpty()) {
            throw new IndexOutOfBoundsException("update(" + index + ", e) on List of length " + length());
        }

        // Skip the current head element, because it is replaced.
        ListFun<T> result = tail.tail().addElement(element);

        while (!list.equals(NIL)) {
            result = result.addElement(list.head());
            list = list.tail();
        }

        return result;
    }
}
