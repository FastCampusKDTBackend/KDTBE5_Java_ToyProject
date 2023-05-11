package me.smartstore.array;

public interface MyCollection<T> {
    int size();
    boolean isEmpty();
    void add(T t);
    void remove();
    T remove(int index);
    void remove(T t);
    void clear();
    int indexOf(T t);
    T get(int index);
    void set(int index, T t);
}
