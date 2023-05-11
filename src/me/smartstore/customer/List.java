package me.smartstore.customer;


import me.smartstore.customer.exception.MaxCapacityReachedException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class List<E> implements Iterable<E> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAX_CAPACITY = 100;
    private static final int MIN_CAPACITY = 16;

    private E[] list;
    private int size = 0;
    private int reduceCapacityThreshold;

    List() { this(DEFAULT_CAPACITY); }

    @SuppressWarnings("unchecked")
    List(int initCapacity) {
        if (initCapacity > MAX_CAPACITY)
            initCapacity = MAX_CAPACITY;
        list = (E[]) new Object[initCapacity];
        reduceCapacityThreshold = initCapacity >>> 2;
    }

    class ListIterator implements Iterator<E> {
        private int idx = 0;

        @Override
        public boolean hasNext() {
            return idx < size;
        }

        @Override
        public E next() {
            return list[idx++];
        }
    }

    public void checkIfReachedMaxCapacity() throws MaxCapacityReachedException {
        if (isReachedMaxCapacity())
            throw new MaxCapacityReachedException("The maximum capable number of elements was reached.\n");
    }

    public boolean isReachedMaxCapacity() { return size == MAX_CAPACITY; }

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    public E get(int idx) throws ArrayIndexOutOfBoundsException {
        checkIfOutOfBounds(idx);
        return list[idx];
    }

    public void add(E e) throws IllegalArgumentException, MaxCapacityReachedException {
        checkIfNull(e);
        checkIfReachedMaxCapacity();
        if (isFull())
            increaseCapacity();
        list[size++] = e;
    }

    private void checkIfNull(E e) {
        if (e == null)
            throw new IllegalArgumentException("Input element is null");
    }

    private boolean isFull() { return size == list.length; }

    private void increaseCapacity() {
        int newLength = Math.min(MAX_CAPACITY, list.length << 1);
        list = Arrays.copyOf(list, newLength);
    }

    public E remove(int idx) throws ArrayIndexOutOfBoundsException {
        checkIfOutOfBounds(idx);

        E ret = list[idx];
        if (size - 1 <= reduceCapacityThreshold) {
            reduceCapacity(idx);
        } else {
            System.arraycopy(list, idx + 1, list, idx, size - idx);
            size--;
        }
        return ret;
    }

    private void reduceCapacity(int idx) {
        int newCapacity = Math.max(MIN_CAPACITY, list.length >>> 1);
        @SuppressWarnings("unchecked")
        E[] newList = (E[]) new Object[newCapacity];
        System.arraycopy(list, 0, newList, 0, idx);
        System.arraycopy(list, idx + 1, newList, idx, size - 1 - idx);
        list = newList;
        size--;
        reduceCapacityThreshold = newCapacity >>> 2;
    }

    private void checkIfOutOfBounds(int idx) {
        if (idx < 0 || idx >= size) {
            String msg = String.format("Acceptable range: (0~%d), but input: %d", size - 1, idx);
            throw new ArrayIndexOutOfBoundsException(msg);
        }
    }

    public void sort(Comparator<E> comparator) {
        for (int i = 1; i < size; ++i) {
            int j = i;
            E src = list[j--];
            while (j >= 0 && comparator.compare(list[j], src) > 0) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = src;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("======= Stored Customers Info. =======\n");
        for (int i = 0; i < size; ++i) {
            String line = String.format("No. %2d => %s\n", i + 1, list[i].toString());
            sb.append(line);
        }
        return sb.toString();
    }
}
