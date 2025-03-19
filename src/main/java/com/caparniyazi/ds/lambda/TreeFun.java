package com.caparniyazi.ds.lambda;

@SuppressWarnings("rawtypes")
public class TreeFun<E extends Comparable<E>> {
    // Data fields
    private final E data;   // The root node
    private final TreeFun<E> left;
    private final TreeFun<E> right;
    public int size;
    public int height;

    private static final TreeFun NIL = new TreeFun();

    // Constructors
    @SuppressWarnings("unchecked")
    private TreeFun() {
        data = null;
        left = NIL;
        right = NIL;
        size = 0;
        height = -1;
    }

    private TreeFun(TreeFun<E> left, E data, TreeFun<E> right) {
        this.left = left;
        this.data = data;
        this.right = right;
        this.size = 1 + this.left.size + this.right.size;
        this.height = 1 + Math.max(this.left.height, this.right.height);
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <E extends Comparable<E>> TreeFun<E> of(E... data) {
        TreeFun<E> tree = NIL;

        for (E element : data) {
            tree = tree.add(element);
        }

        return tree;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private TreeFun<E> add(E newData) {
        if (isEmpty()) {
            return new TreeFun<E>(NIL, newData, NIL);
        } else {
            if (newData.compareTo(this.data) < 0) {
                return new TreeFun<>(this.left.add(newData), this.data, this.right);
            } else if (newData.compareTo(this.data) > 0) {
                return new TreeFun<>(this.left, this.data, this.right.add(newData));
            } else {
                return new TreeFun<>(this.left, newData, this.right);
            }
        }
    }

    public TreeFun<E> remove(E element) {
        if (element.compareTo(this.data) < 0) {
            return new TreeFun<>(this.left.remove(element), this.data, this.right);
        } else if (element.compareTo(this.data) > 0) {
            return new TreeFun<>(this.left, this.data, this.right.remove(element));
        } else {
            return this.left.merge(this.right);
        }
    }

    private TreeFun<E> merge(TreeFun<E> right) {
        if (this.isEmpty()) {
            return right;
        } else if (right.isEmpty()) {
            return this;
        } else {
            return new TreeFun<>(this.left.merge(this.right), this.data, right);
        }
    }

    public boolean contains(E data) {
        if (this.data != null) {
            if (data.compareTo(this.data) < 0) {
                return left.contains(data);
            } else if (data.compareTo(this.data) > 0) {
                return right.contains(data);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public E max() {
        return this.right.equals(NIL) ? this.data : this.right.max();
    }

    @Override
    public String toString() {
        return this.data != null ? String.format("( %s %s %s )", left, data, right) : "";
    }
}
