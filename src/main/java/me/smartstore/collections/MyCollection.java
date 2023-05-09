package me.smartstore.collections;

public interface MyCollection<T> {
    void add(T t);

    void clear();

    boolean contains(T t);

    boolean isEmpty();

    boolean remove(T t);

    int size();
}
