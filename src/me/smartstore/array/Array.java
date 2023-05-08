package me.smartstore.array;

import java.util.Arrays;

public class Array<T> implements MyCollection<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity;
    protected T[] arrays;

    public Array() throws ClassCastException{
        this.arrays = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void add(T t) {
        if (size < arrays.length) {
            arrays[size] = t;
            size++;
        } else {
            grow();
            add(t);
        }
    }

    @Override
    public void remove() {
        remove(size-1);
    }

    @Override
    public T remove(int index) {
        T tempElement = arrays[index];

        for (int i = index+1; i < size; i++) {
            arrays[i-1] = arrays[i];
        }
        arrays[size-1] = null;
        size--;

        return tempElement;
    }

    @Override
    public void remove(T t) {
        remove(indexOf(t));
    }

    @Override
    public void clear() {
        arrays = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    @Override
    public int indexOf(T t) {
        for (int i = 0; i < size; i++) {
            if (arrays[i] == null) continue;

            if (arrays[i].equals(t)) return i;
        }

        return -1;
    }

    @Override
    public T get(int index) {
        return arrays[index];
    }

    @Override
    public void set(int index, T t) {
        arrays[index] = t;
    }

    private void grow() {
        capacity *= 2;

        arrays = Arrays.copyOf(arrays, capacity);
    }
}
