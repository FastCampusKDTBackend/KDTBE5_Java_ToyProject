package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CustomArrayList<E> implements CustomCollection<E>{

    private Object[] customArray;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity;

    public CustomArrayList() {
        this.customArray = new Object[]{};
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.capacity = initialCapacity;
            this.size = 0;
            this.customArray = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            new CustomArrayList();
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }


    public int indexOf(Object o) {
        return indexofRange(o, 0, size);
    }

    private int indexofRange(Object o, int start, int end) {
        for (int i = start; i < end; i++) {
            if (o.equals(customArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean add(E e) {
        add(e, customArray, size);
        return true;
    }

    public boolean add(E e, int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (size == customArray.length) {
            customArray = grow();
        }

        for (int i = index; i < size; i++) {
            customArray[i + 1] = customArray[i];
        }
        customArray[index] = (Object) e;
        size = customArray.length;

        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return (E) customArray[index];
    }

    private void add(E e, Object[] customArray, int size) {
        if (size == customArray.length) {
            customArray = grow();
        }
        customArray[size++] = e;
    }

    private Object[] grow() {
        customArray = Arrays.copyOf(customArray, size * 2);
        capacity = size * 2;
        return customArray;
    }


    public boolean remove(Object o) {
        return false;
    }

    public void clear() {
        this.customArray = new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        size = this.customArray.length;
    }
}
