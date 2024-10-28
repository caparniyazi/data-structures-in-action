package com.caparniyazi.ds.collections;

import lombok.Getter;

/**
 * Class to represent a linked list with a link from each node to the next node.
 * It does not implement the List interface.
 * <p/>
 * Single‐linked list data structure limitations:
 * 1. Insertion at the front of the list is O(1). Insertion at other positions is O(n).
 * 2. We can only insert a node only after a node for which we have a reference.
 * 3. We can remove a node only if we have a reference to its predecessor node.
 * 4. We can move only in ond direction, starting at the list head, whereas with the Arraylist
 * we can move forward(or backward) by incrementing(or decrementing) the index.
 * <p/>
 * We can overcome these limitations by adding a reference to the previous node
 * in the Node class, that's creating a Double-Linked list.
 * A double-linked list object consists of data fields head(a reference to the first list node), tail
 * (a reference to the last list node), and size.
 * Because both ends of the double-linked list are directly accessible, insertion at either end
 * is O(1); insertion elsewhere is still O(n).
 * <p/>
 * The List interface declares the method iterator, which returns an Iterator object that will
 * iterate over the elements of that list.
 * Please note that the LinkedList remove method removes only one element at a time,
 * so you would need to start at the beginning of the list each time and
 * advance down the list to each element that you wanted to remove (O(n2) process).
 * With the Iterator remove method, you can remove elements as they are accessed by the
 * Iterator object without having to go back to the beginning of the list (O(n) process).
 * <p/>
 * The enhanced for loop creates an Iterator object and implicitly calls its hasNext and next
 * methods. Other Iterator methods, such as remove, are not available.
 * <pre>
 *  count = 0;
 *
 *  for (String nextStr : myList) {
 *      if (target.equals(nextStr)) {
 *          count++;
 *      }
 *  }
 * </pre>
 * <p/>
 * The Iterator has some limitations. It can traverse the List only in the forward direction.
 * It also provides only a remove method, not an add method.
 * Also, to start an iterator somewhere other than at first list element, you must write
 * your own loop to advance the Iterator to the desired starting position.
 * The Java API also contains the ListIterator<E> interface, which is an extension of the
 * Iterator<E> interface that overcomes these limitations.
 * To obtain a ListIterator, you call the listIterator method of the LinkedList class.
 * <pre>
 * public ListIterator<E> listIterator()
 *  Returns a ListIterator that begins just before the first list element.
 * public ListIterator<E> listIterator(int index)
 *  Returns a ListIterator that begins just before the position index.
 * </pre>
 * <pre>
 * <p>
 *     The following code fragment searches for target in list myList and,
 *     if target is present, replaces its first occurrence with newItem.
 * </p>
 * ListIterator<String> myIter = myList.listIterator();
 *
 * while (myIter.hasNext()) {
 *      if (target.equals(myIter.next())) {
 *          myIter.set(newItem);
 *          break; // Exit loop
 *      }
 * }
 * </pre>
 * <p/>
 * Because the ListIterator<E> is a sub-interface of Iterator<E>, classes that implement ListIterator
 * must provide all the capabilities of both.
 * <p/>
 * Remember that the listIterator(int index) method creates the desired ListIterator
 * by creating a new ListIterator that starts at the beginning and then
 * walks along the list until the desired position is found. There is a
 * special case where index is equal to size(), but all others are an O(n) operation.
 */
public class MySingleLinkedList<E> {
    // Data fields
    private Node<E> head = null;    // Reference to list head.
    @Getter
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

    /**
     * Method to remove the first occurrence of element item.
     *
     * @param item The item to be removed.
     * @return true if item is found and removed; otherwise, return false.
     */
    public boolean remove(E item) {
        int index = indexOf(item);

        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
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
     * Method to return the index of a given item.
     *
     * @param item The item to look for.
     * @return The index of the node reference to the data.
     * Complexity: O(n)
     */
    public int indexOf(E item) {
        Node<E> node = head;
        int index = 0;

        while (node != null && node.data != item) {
            node = node.next;
            index++;
        }

        if (node != null) {
            return index;
        } else {
            return -1; // We did not find the target item.
        }
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
}
