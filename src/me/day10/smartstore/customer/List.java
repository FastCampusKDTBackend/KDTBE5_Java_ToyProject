package me.day10.smartstore.customer;


public abstract class List<E> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAX_CAPACITY = 100;

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

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            String line = String.format("No. %2d => %s\n", i, list[i].toString());
            sb.append(line);
        }
        return sb.toString();
    }
}
