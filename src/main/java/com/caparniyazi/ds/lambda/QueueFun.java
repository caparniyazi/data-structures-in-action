package com.caparniyazi.ds.lambda;

public final class QueueFun<T> {
    // Data fields
    private final ListFun<T> front;
    private final ListFun<T> rear;

    // Creating an empty queue.
    private QueueFun() {
        this.front = ListFun.list();
        this.rear = ListFun.list();
    }

    /**
     * Static factory method to return the empty queue object by calling the private constructor.
     *
     * @param <T> The type
     * @return The empty queue object.
     */
    public static <T> QueueFun<T> queue() {
        return new QueueFun<T>();
    }

    private QueueFun(QueueFun<T> queue, T element) {
        boolean frontIsEmpty = queue.front.isEmpty();
        this.front = frontIsEmpty ? queue.front.addElement(element) : queue.front;
        this.rear = frontIsEmpty ? queue.rear : queue.rear.addElement(element);
    }

    public QueueFun<T> enqueue(T element) {
        return new QueueFun<T>(this, element);
    }

    private QueueFun(ListFun<T> front, ListFun<T> rear) {
        final boolean frontIsEmpty = front.isEmpty();
        this.front = frontIsEmpty ? rear.reverseList() : front;
        this.rear = frontIsEmpty ? front : rear;
    }

    public QueueFun<T> dequeue() {
        return new QueueFun<>(this.front.tail(), this.rear);
    }

    void forEach(Consumer<? super T> consumer) {
        T current = this.front.head();
        ListFun<T> temp = ListFun.concat(this.front.tail(), this.rear.reverseList());

        while (temp != null) {
            consumer.accept(current);
            current = temp.head();
            temp = temp.tail();
        }
    }

    public int size() {
        return front.length() + rear.length();
    }

    public T peek() {
        if (this.size() == 0) {
            return null;
        } else {
            return this.front.head();
        }
    }

    public boolean isEmpty() {
        return this.front.isEmpty() == this.rear.isEmpty();
    }
}
