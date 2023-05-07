package me.day10.smartstore.customer;


import java.util.Arrays;

public class List<E> {

    protected static final int DEFAULT_CAPACITY = 16;
    protected static final int MAX_CAPACITY = 100;

    // list[0] is always null: dummy
    private E[] list;
    private int size = 0;

    List() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    List(int initCapacity) {
        if (initCapacity > MAX_CAPACITY)
            initCapacity = MAX_CAPACITY;
        list = (E[]) new Object[initCapacity];
    }

    public void checkIfReachedMaxCapacity() throws MaxCapacityReachedException {
        if (isReachedMaxCapacity())
            throw new MaxCapacityReachedException("The maximum capable number of elements was reached.\n");
    }

    public boolean isReachedMaxCapacity() {
        return size == MAX_CAPACITY - 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() { return size; }

    public E get(int idx) { return list[idx]; }

    public void add(E e) throws IllegalArgumentException, MaxCapacityReachedException {
        checkIfNull(e);
        checkIfReachedMaxCapacity();
        if (isFull())
            increaseCapacity();
        list[++size] = e;
    }

    private void checkIfNull(E e) {
        if (e == null)
            throw new IllegalArgumentException("Input element is null");
    }

    private boolean isFull() {
        return size == list.length;
    }

    private void increaseCapacity() {
        int newLength = Math.min(MAX_CAPACITY, list.length << 1);
        list = Arrays.copyOf(list, newLength);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= size; ++i) {
            String line = String.format("No. %2d => %s\n", i, list[i].toString());
            sb.append(line);
        }
        return sb.toString();
    }
}
