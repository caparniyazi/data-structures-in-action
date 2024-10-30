package com.caparniyazi.ds.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A circular‐linked list has no need of a head or tail. Instead, you need only a reference to a current
 * node, which is the nextNode returned by the Iterator.
 * For a nonempty list, the Iterator.hasNext method will always return true.
 */
public class CircularList<E> {
    // Data fields
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * The building-block for a list.
     *
     * @param <E> The data the node references.
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;

        // Constructors
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        public Node(E data) {
            this(data, null);
        }
    }

    /**
     * The inner class that implements the Iterator interface.
     */
    private class MyListIterator implements Iterator<E> {
        // Data fields
        private Node<E> nextNode;

        public MyListIterator() {
            nextNode = head;
        }

        public boolean hasNext() {
            return nextNode != null;
        }

        public E next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }

            E item = nextNode.data;
            nextNode = nextNode.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Methods

    /**
     * Method to return an Iterator to the list.
     *
     * @return An Iterator to the list.
     */
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> node = head;

        while (node != null) {
            sb.append(node.data.toString());

            if (node.next != null) {
                sb.append(", ");
            }
            node = node.next;
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Method to append item to the end of the list.
     *
     * @param item The item to be appended.
     * @return true (as specified by the Collection interface).
     * Complexity: O(n) => No shifting (faster than ArrayList), but must iterate.
     */
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    /**
     * Method to insert a new item before the one at position index,
     * starting at 0 for list head.
     *
     * @param index The position where the new item is to be inserted.
     * @param item  The item to be inserted.
     * @throws IndexOutOfBoundsException if index is out of range.
     *                                   Complexity: O(n) => No shifting, adding is a constant time op, but must still iterate.
     */
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        if (index == 0) {
            addFirst(item);
        } else {
            Node<E> node = getNode(index - 1);
            addAfter(node, item);
        }
    }

    /**
     * Method to add a node after a given node.
     * The method is private since it should not be called from outside the class.
     * This is because we want to keep the internal structure of the class hidden.
     * Such private methods are known as helper methods because they will help
     * implement the public methods of the class.
     *
     * @param node The node preceding the new item.
     * @param item The item to insert.
     */
    private void addAfter(Node<E> node, E item) {
        node.next = new Node<>(item, node.next);
        size++;
    }

    /**
     * Method that inserts one element at a time to the front of the list,
     * thereby changing the node pointed to by head.
     *
     * @param item The item to be added.
     */
    public void addFirst(E item) {
        head = new Node<>(item, head);
        size++;
        tail = getNode(size - 1);   // Get the tail node.
        tail.next = head;   // Connect the last list item to the first item in O(1) time.
    }

    /**
     * Helper method to find the node at a specified position.
     *
     * @param index The position of the node sought.
     * @return The node at index, or null if it does not exist.
     */
    private Node<E> getNode(int index) {
        Node<E> node = head;

        for (int i = 0; i < index && node != null; i++) {
            node = node.next;
        }

        return node;
    }

    /**
     * Method to get the data at index.
     *
     * @param index The position of the data to return.
     * @return The data at index.
     * @throws IndexOutOfBoundsException if index is out of range.
     *                                   Complexity: O(n) => Only runs as far as index, but must iterate.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        Node<E> node = getNode(index);
        return node.data;
    }

    /**
     * Method to store a reference to an entry in the element at position index.
     *
     * @param index    The position of the item to change.
     * @param newValue The new data.
     * @return The data previously at index.
     * @throws IndexOutOfBoundsException if index is out of range.
     *                                   Complexity: O(n) => No shifting elements, but must iterate to the item.
     */
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        Node<E> node = getNode(index);
        E oldData = node.data;
        node.data = newValue;
        return oldData;
    }

    /**
     * Method to remove the first node from the list.
     *
     * @return The removed node's data, or null if the list is empty.
     */
    public E removeFirst() {
        Node<E> temp = head;

        if (head != null) {
            head = head.next;
        }

        // Return data at old head, or null if list is empty.
        if (temp != null) {
            size--;
            tail = getNode(size - 1);
            tail.next = head;
            return temp.data;
        } else {
            return null;
        }
    }

    /**
     * Method to remove the node after a given node.
     * This method works on all nodes except for the first one.
     * For that we need a special method, removeFirst().
     *
     * @param node The node before the one to be removed.
     * @return The data from the removed node, or null if there is no node to remove.
     */
    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;

        if (temp == null) {
            return null;
        } else {
            node.next = temp.next;
            size--;
            tail = getNode(size - 1);
            tail.next = temp;
            return temp.data;
        }
    }

    /**
     * Method to remove the item at index using getNode(), removeFirst(), and removeAfter() methods.
     *
     * @param index The position of the item to be removed.
     * @return The data removed.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        if (index == 0) {
            return removeFirst();
        } else {
            Node<E> node = getNode(index - 1);
            return removeAfter(node);
        }
    }
}
