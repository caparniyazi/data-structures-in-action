package com.caparniyazi.ds.recursion;

/**
 * A recursive linked-list class with recursive methods.
 */

public class LinkedListRec<E> {
    // Data fields
    private Node<E> head;   // The list head.

    private static class Node<E> {
        // Data fields
        private E theData;
        private Node<E> next;

        private Node(E theData, Node<E> next) {
            this.theData = theData;
            this.next = next;
        }

        private Node(E theData) {
            this(theData, null);
        }
    }

    // Methods

    /**
     * Finds the size of a list.
     *
     * @param head The head of the current list.
     * @return The size of the current list.
     */
    private int size(Node<E> head) {
        if (head == null) {
            return 0;
        } else {
            return 1 + size(head.next);
        }
    }

    /**
     * Wrapper method for finding the size of a list.
     *
     * @return The size of the list.
     */
    public int size() {
        return size(head);
    }

    /**
     * Returns the string representation of a list.
     *
     * @param head The head of the current list.
     * @return The state of the current list.
     */
    private String toString(Node<E> head) {
        if (head == null) {
            return "";
        } else {
            return head.theData + "\n" + toString(head.next);
        }
    }

    /**
     * Wrapper method for returning the string representation of a list.
     *
     * @return The string representation of the list.
     */
    public String toString() {
        return toString(head);
    }

    /**
     * Replaces all occurrences of oldData with newData.
     *
     * @param head    The head of the current list.
     * @param oldData The item being removed.
     * @param newData The item being inserted.
     * @post Each occurrence of oldData has been replaced by newData.
     */
    private void replace(Node<E> head, E oldData, E newData) {
        if (head != null) {
            if (oldData.equals(head.theData)) {
                head.theData = newData;
            } else {
                replace(head.next, oldData, newData);
            }
        }
    }

    /**
     * Wrapper method for replacing oldData with newData.
     *
     * @param oldData The item being removed.
     * @param newData The item being inserted.
     * @post Each occurrence of oldData has been replaced by newData.
     */
    public void replace(E oldData, E newData) {
        replace(head, oldData, newData);
    }

    /**
     * Adds a new node to the end of the list.
     * <p/>
     * For each node referenced by argument head, the recursive method tests to see whether the
     * node referenced by argument head is the last node in the list (head.next is null). If so, the
     * method add then resets head.next() to reference a new node that contains the data being inserted.
     *
     * @param head The head of the current list.
     * @param data The data for the new node.
     */
    private void add(Node<E> head, E data) {
        // If the list has just one item, add to it.

        if (head.next == null) {
            head.next = new Node<>(data);
        } else {
            add(head.next, data);   // Add to rest of list.
        }
    }

    /**
     * Wrapper method for adding a new node to the end of a list.
     *
     * @param data The data for the new node.
     */
    public void add(E data) {
        if (head == null) {
            head = new Node<>(data);    // List has 1 node.
        } else {
            add(head, data);
        }
    }

    /**
     * Removes a node from a list.
     *
     * @param head    The head of the current list.
     * @param pred    The predecessor of the list head.
     * @param outData The data to be removed.
     * @return true if the item is removed, false otherwise.
     * @post The first occurrence of outData is removed.
     */
    private boolean remove(Node<E> head, Node<E> pred, E outData) {
        if (head == null) { // Base case - empty list.
            return false;
        } else if (head.theData.equals(outData)) {  // Second base case
            pred.next = head.next;  // Remove head.
            return true;
        } else {
            return remove(head.next, pred, outData);
        }
    }

    /**
     * Wrapper method for removing a node.
     *
     * @param outData The data to be removed.
     * @return true if the item is removed, false otherwise.
     * @post The first occurrence of outData is removed.
     */
    public boolean remove(E outData) {
        if (head == null) {
            return false;
        } else if (head.theData.equals(outData)) {
            head = head.next;
            return true;
        } else {
            return remove(head.next, head, outData);
        }
    }

    /**
     * Recursive method to determine if two LinkedListRec objects are equal.
     *
     * @param node1 The current node in this list
     * @param node2 The current node in the other list.
     * @return true if the two lists are equal, false otherwise.
     */
    private boolean equals(Node<E> node1, Node<E> node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null) {
            return false;
        }

        if (node2 == null) {
            return false;
        }

        if (node1.theData.equals(node2.theData)) {
            return equals(node1.next, node2.next);
        }
        return false;
    }

    /**
     * Method to determine if two LinkedListRec objects are equal.
     * Two lists are equal if they have the same number of nodes and store
     * the same information at each node.
     *
     * @param obj The other object.
     * @return true if the other object is equal to this.
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        LinkedListRec<E> other = (LinkedListRec<E>) obj;
        return equals(head, other.head);
    }

    /**
     * Recursive method to insert a specified data object before
     * the first occurrence of another specified data object. If the object is not found,
     * then the item is inserted at the end of the list.
     *
     * @param target The item that inData is to be inserted before.
     * @param inData The item to be inserted.
     * @param node   The current node.
     */
    private void insertBefore(E target, E inData, Node<E> node) {
        if (node.next == null) {
            node.next = new Node<>(inData, null);
            return;
        }

        if (target.equals(node.next.theData)) {
            node.next = new Node<>(inData, node.next);
            return;
        }

        insertBefore(target, inData, node.next);
    }

    /**
     * Method to insert a specified data object before the first occurrence
     * of another specified data object. If the data object is not found, then the item
     * is inserted at the end of the list.
     *
     * @param target The item that inData is to be inserted before.
     * @param inData The item to be inserted.
     */
    public void insertBefore(E target, E inData) {
        if (head == null) {
            head = new Node<>(inData, null);
            return;
        }

        if (head.theData.equals(target)) {
            head = new Node<>(inData, head);
            return;
        }

        insertBefore(target, inData, head);
    }

    /**
     * Method to insert an object at a specified index.
     *
     * @param obj   The object to be inserted.
     * @param pred  The node preceding the node at the current index.
     * @param index The current index.
     */
    private void insert(E obj, Node<E> pred, int index) {
        if (pred == null) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            pred.next = new Node<>(obj, pred.next);
        } else {
            insert(obj, pred.next, index - 1);
        }
    }

    /**
     * Method to insert an object at a specified index.
     *
     * @param obj   The object to be inserted.
     * @param index The index.
     */
    public void insert(E obj, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            head = new Node<>(obj, head);
        } else {
            insert(obj, head, index - 1);
        }
    }

    /**
     * Recursive method to search a LinkedListRec.
     *
     * @param obj  The item being sought.
     * @param node The current node.
     * @return true if the item being sought is found.
     */
    private boolean search(E obj, Node<E> node) {
        if (node == null) {
            return false;
        }

        if (node.theData.equals(obj)) {
            return true;
        }

        return search(obj, node.next);
    }

    /**
     * Method that returns true if its argument is stored as the data field of a
     * LinkedListRec node, and returns false if its argument is not stored in any node.
     *
     * @param obj The item being sought.
     * @return true if the item is in the list, false otherwise.
     */
    public boolean search(E obj) {
        return search(obj, head);
    }
}
