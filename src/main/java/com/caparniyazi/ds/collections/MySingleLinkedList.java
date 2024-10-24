package com.caparniyazi.ds.collections;

/**
 * Class to represent a linked list with a link from each node to the next node.
 * It does not implement the List interface.
 */
public class MySingleLinkedList<E> {
    // Data fields
    private Node<E> head = null;    // Reference to list head.
    private int size = 0;   // The number of items in the list.

    private static class Node<E> {
        // Data fields
        private E data; // The reference to the data.
        private Node<E> next;   // The reference to the next node.

        // Constructors

        /**
         * Creates a new node with a null next field.
         *
         * @param data The data stored.
         */
        private Node(E data) {
            this.data = data;
            next = null;
        }

        /**
         * Creates a new node that references another node.
         *
         * @param data The data stored.
         * @param next The node referenced by new node.
         */
        private Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    // Methods

    /**
     * Method that inserts one element at a time to the front of the list,
     * thereby changing the node pointed to by head.
     *
     * @param item The item to be added.
     */
    public void addFirst(E item) {
        head = new Node<>(item, head);
        size++;
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
            return temp.data;
        }
    }

    /**
     * Method to remove the first node from the list.
     *
     * @return The removed node's data, or null if the list is empty.
     */
    private E removeFirst() {
        Node<E> temp = head;

        if (head != null) {
            head = head.next;
        }

        // Return data at old head, or null if list is empty.
        if (temp != null) {
            size--;
            return temp.data;
        } else {
            return null;
        }
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
     * Method to insert the specified item at index.
     *
     * @param index The position where item is to be inserted.
     * @param item  The item to be inserted.
     * @throws IndexOutOfBoundsException if index is out of range.
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
     * Method to append item to the end of the list.
     *
     * @param item The item to be appended.
     * @return true (as specified by the Collection interface).
     */
    public boolean add(E item) {
        add(size, item);
        return true;
    }
}
