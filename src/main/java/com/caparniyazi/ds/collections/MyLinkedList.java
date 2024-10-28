package com.caparniyazi.ds.collections;

import jakarta.annotation.Nonnull;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Class that implements a double-linked list and a ListIterator.
 * It is a complete implementation of the List interface.
 */
public class MyLinkedList<E> extends AbstractSequentialList<E> {
    // Data fields
    private Node<E> head;   // Reference to list head.
    private Node<E> tail;   // Reference to list tail.
    private int size;   // The number of items in the list.

    /**
     * The building-block for a double-linked list.
     * We declare it to be static because there is no need for its methods
     * to access the data fields of its parent class.
     *
     * @param <E> The data the node references.
     */
    private static class Node<E> {
        // Data fields
        private E data; // The reference to the data.
        private Node<E> next;   // The reference to the next node.
        private Node<E> prev;   // The reference to the previous node.

        // Constructors

        /**
         * Create a new node with the given data value.
         *
         * @param data The data stored.
         */
        private Node(E data) {
            this.data = data;
        }
    }

    /**
     * Inner class that implements the ListIterator interface.
     * We cannot declare this inner class as static, because its methods access and modify
     * the data fields of MyLinkedList object that creates the MyListIterator object.
     * An inner class that is not static contains an implicit reference to its parent object,
     * just as it contains an implicit reference to itself.
     * Because MyListIterator is not static and can reference data fields of its parent class MyLinkedList<E>,
     * the type parameter <E> is considered to be previously defined; therefore, it cannot appear
     * as part of the class name.
     */
    private class MyListIterator implements ListIterator<E> {
        // Data fields
        private Node<E> nextItem;   // The reference to the next item.
        private Node<E> lastItemReturned;   // A reference to the node that was last returned by the next or previous.
        private int index;  // The iterator is positioned just before the item at index.

        // Constructors

        /**
         * Construct a MyListIterator that will reference the ith item.
         *
         * @param i The index of the item to be referenced.
         */
        public MyListIterator(int i) {
            // Validate the parameter
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException("Invalid index " + i);
            }
            lastItemReturned = null;    // No item returned yet.

            if (i == size) {
                index = size;
                nextItem = null;
            } else {
                nextItem = head;

                for (index = 0; index < i; index++) {
                    nextItem = nextItem.next;
                }
            }
        }

        /**
         * Method to indicate whether movement forward is defined.
         *
         * @return true if call to next will not throw NoSuchElementException.
         */
        @Override
        public boolean hasNext() {
            return nextItem != null;
        }

        /**
         * Move the iterator forward and return the next item.
         *
         * @return The next item in the list.
         * @throws NoSuchElementException if there is no such object.
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index++;

            return lastItemReturned.data;
        }

        /**
         * Method to indicate whether movement backward is defined.
         * When the iterator is at the end of the list, nextItem is null.
         * In this case, we can determine that there is a previous item by checking the size—a nonempty
         * list will have a previous item when the iterator is at the end.
         * If the iterator is not at the end, the nextItem is not null,
         * and we can check for a previous item by examining nextItem.prev.
         *
         * @return true if call to previous will not throw NoSuchElementException.
         */
        @Override
        public boolean hasPrevious() {
            return (nextItem == null && size != 0) || (nextItem != null && nextItem.prev != null);
        }

        /**
         * Move the iterator backward and return the previous item.
         *
         * @return The previous item in the list.
         * @throws NoSuchElementException if there is no such object.
         */
        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            // If the nextItem is null, the iterator is past the last element,
            // so nextItem is set to tail because the previous element must be the last list element.
            // If the nextItem is not null, nextItem is set to nextItem.prev.
            // Either way, lastItemReturned is set to nextItem, and index is decremented.
            // The data field of the node referenced by lastItemReturned is returned.
            if (nextItem == null) {
                nextItem = tail;    // Iterator is past the last element.
            } else {
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            index--;

            return lastItemReturned.data;
        }

        /**
         * Method to return the index of the next item to be returned.
         *
         * @return The index of the next item to be returned by nextItem.
         */
        @Override
        public int nextIndex() {
            return index;
        }

        /**
         * Method to return the index of the next item to be returned by previous.
         *
         * @return The index of the next item to be returned by previous.
         */
        @Override
        public int previousIndex() {
            return index - 1;
        }

        /**
         * Removes the last item returned. This can only be done once per call to next or previous.
         *
         * @throws IllegalStateException if next or prev was not called prior to calling this method.
         */
        @Override
        public void remove() {
            if (lastItemReturned == null) { // Invalid, must have an item to remove.
                throw new IllegalStateException();
            }

            lastItemReturned.next.prev = lastItemReturned.prev;
            lastItemReturned.prev.next = lastItemReturned.next;
            lastItemReturned = null;
        }

        /**
         * Replaces the last item returned with a new value.
         *
         * @param item the element with which to replace the last element returned by
         *             {@code next} or {@code previous}
         * @throws IllegalStateException if next or previous was not called prior to calling this method.
         */
        @Override
        public void set(E item) {
            if (lastItemReturned == null) {
                throw new IllegalStateException();
            }

            lastItemReturned.data = item;
            lastItemReturned = null;
        }

        /**
         * Add a new item between the item that will be returned by next and the item
         * that will be returned by previous. If prev is called after add, the element added
         * is returned.
         *
         * @param item the item to be inserted.
         */
        @Override
        public void add(E item) {
            if (head == null) { // Add to an empty list.
                head = new Node<>(item);
                tail = head;
            } else if (nextItem == head) {  // The insertion is at the head
                Node<E> newNode = new Node<>(item);
                // Link it to the next item.
                newNode.next = nextItem;
                // Link nextItem to the new node.
                nextItem.prev = newNode;
                // The  new node is now the head.
                head = newNode;
            } else if (nextItem == null) {  // The insertion is at the tail.
                // Create a new node
                Node<E> newNode = new Node<>(item);
                // link the tail to the new node
                tail.next = newNode;
                // Link the new node to the tail
                newNode.prev = tail;
                // The new node is the new tail.
                tail = newNode;
            } else {    // If none of the prev cases are true, then the addition is into the middle of the list.
                // Create a new node
                Node<E> newNode = new Node<>(item);

                // Link it to nexItem.prev
                newNode.prev = nextItem.prev;
                nextItem.prev.next = newNode;

                // Link it to the nextItem.
                newNode.next = nextItem;
                nextItem.prev = newNode;
            }
            // After the new node is inserted, both size & index are incremented and lastItemReturned is set to null.
            size++;
            index++;
            lastItemReturned = null;
        }   // End of method add.
    }

    // Methods

    /**
     * Method to return a list iterator at the head of the list.
     *
     * @return A list iterator at the head of the list.
     */
    @Nonnull
    public ListIterator<E> listIterator() {
        return new MyListIterator(0);
    }

    /**
     * Method to return a list iterator at the given position.
     *
     * @param index The index to start the list iterator at.
     * @return A list iterator at the specified index.
     */
    @Nonnull
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index);
    }

    /**
     * Method to add an item at position index.
     *
     * @param index The position at which the item is to be inserted.
     * @param data  The item to be inserted.
     */
    public void add(int index, E data) {
        listIterator(index).add(data);
    }

    /**
     * Method that inserts the item as the first element of the list.
     *
     * @param data The item to be inserted.s
     */
    public void addFirst(E data) {
        listIterator().add(data);
    }

    /**
     * Method that inserts the item as the last element of the list.
     *
     * @param data The item to be inserted.
     */
    public void addLast(E data) {
        listIterator(size).add(data);
    }

    /**
     * Method that returns the item at the specified index.
     *
     * @param index The index at which we will return the item.
     * @return The item at the specified index.
     */
    public E get(int index) {
        return listIterator(index).next();
    }

    /**
     * Method to return the item at the beginning of the list.
     *
     * @return The item at the beginning of the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * Method to return the item at the end of the list.
     *
     * @return The item at the end of the list.
     * @throws NoSuchElementException if the list is empty.
     */
    public E getLast() {
        return get(size - 1);
    }

    public int size() {
        return size;
    }
}
