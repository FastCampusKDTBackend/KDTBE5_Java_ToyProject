package me.day10.smartstore.customer;


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

    public boolean isReachedMaxCapacity() {
        return list.length == MAX_CAPACITY;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() { return size; }

    public E get(int idx) { return list[idx]; }

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
