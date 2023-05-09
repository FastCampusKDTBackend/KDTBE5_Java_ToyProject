package me.smartstore.collections;

public interface MyList<T> extends MyCollection<T> {
    void add(int index, T t);
    void set(int index, T t);

    int indexOf(T t);

    T get(int index);
}
