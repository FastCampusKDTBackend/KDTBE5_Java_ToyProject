package me.smartstore.arrays;

import me.smartstore.exception.ElementNotFoundException;
import me.smartstore.exception.NullArgumentException;
import java.util.EmptyStackException;

public class DArray<T> implements Collections<T> {
    
    protected static final int DEFAULT = 10;

    protected T[] arrays;
    protected int size;
    protected int capacity;

    public static int getDefault() {
        return DEFAULT;
    }

    public T[] getArrays() {
        return arrays;
    }

    public void setArrays(T[] arrays) {
        this.arrays = arrays;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    @SuppressWarnings("unchecked")
    public DArray() {
        arrays = (T[]) new Object[DEFAULT];
        capacity = DEFAULT;
    }

    @SuppressWarnings("unchecked")
    public DArray(int initial) {
        arrays = (T[]) new Object[initial];
        capacity = initial;
    }

    public DArray(T[] arrays) {
        this.arrays = arrays;
        capacity = arrays.length;
        size = arrays.length;
    }

    @Override
    public int size() {
        return size;
    }

    protected int capacity() {
        return capacity;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if(index<0 || index>=size) throw new IndexOutOfBoundsException();
        return arrays[index];
    }

    @Override
    public void set(int index, T object) throws IndexOutOfBoundsException, NullArgumentException {
        if(index<0 || index>=size) throw new IndexOutOfBoundsException();
        if(object == null) throw new NullArgumentException();
        arrays[index] = object;
    }

    @Override
    public int indexOf(T object) throws NullArgumentException, ElementNotFoundException {
        if(object == null) throw new NullArgumentException();

        for(int i=0; i<size; i++) {
            if(arrays[i] == null) continue;
            if(arrays[i].equals(object)) return i;
        }
        throw new ElementNotFoundException();
    }

    @Override
    public void add(T object) throws NullArgumentException {
        if(object == null) throw new NullArgumentException();
        if(size<capacity) {
            arrays[size] = object;
            size++;
        } else {
            grow();
            add(object);
        }
    }

    @Override
    public void add(int index, T object) throws IndexOutOfBoundsException, NullPointerException {
        if(index<0 || index>=size) throw new IndexOutOfBoundsException();
        if(object == null) throw new NullArgumentException();
        if(size<capacity) {
            for(int i=size-1; i>=index; i--) {
                arrays[i+1] = arrays[i];
            }
            arrays[index] = object;
            size++;
        } else {
            grow();
            add(index, object);
        }
    }

    @Override
    public T pop() {
        return pop(size -1);
    }

    @Override
    public T pop(int index) throws IndexOutOfBoundsException {
        if(size == 0) throw new EmptyStackException();
        if(index<0 || index>=size) throw new IndexOutOfBoundsException();

        T popElement = arrays[index];
        arrays[index] = null;

        for(int i=index+1; i<size; i++) {
            arrays[i -1] = arrays[i];
        }
        arrays[size -1] = null;
        size--;
        return popElement;
    }

    @Override
    public T pop(T object) {
        return pop(indexOf(object));
    }

    protected void grow() {
        capacity *= 2;
        arrays = java.util.Arrays.copyOf(arrays, capacity);

    }

    @Override
    public String toString() {
        String toStr = "";
        for(int i=0; i<size; i++) {
            toStr += (arrays[i] + "\n");
        }
        return toStr;
    }
   
}
