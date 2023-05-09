package me.smartstore.collections;

import java.util.Arrays;

public class MyArrayList<T> implements MyList<T> {
    private int size;
    private static final int DEFAULT_CAPACITY = 50;
    private T[] elements;

    public MyArrayList() {
        this.size = 0;
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T t) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * 2);
        }
        this.elements[size++] = t;
    }

    //@Todo: 테스트해야됨
    @Override
    public void add(int index, T t) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * 2);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = t;
        size++;
    }

    @Override
    public void clear() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;

    }

    @Override
    public boolean contains(T t) {
        for (T element : elements) {
            if (element.equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(t)) {
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void set(int index, T t) {

    }

    @Override
    public int indexOf(T t) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }
}
