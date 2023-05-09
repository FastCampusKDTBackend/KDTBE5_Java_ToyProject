package com.smartstore.util;

public interface List<T> {
    int size();
    T get(T object);
    T get(int index);
    void set(int index, T object);
    int indexOf(T object);
    void add(T object);
    void add(int index, T object);
    boolean remove(int index);
    boolean remove(T object);

    Object[] toArray();
    Object[] toArray(Class<T[]> type);
}