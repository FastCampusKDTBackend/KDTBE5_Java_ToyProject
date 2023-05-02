package smartstore.arrays;

import smartstore.exception.ElementNotFoundException;
import smartstore.exception.EmptyArrayException;
import smartstore.exception.NullArgumentException;

import java.util.Arrays;

public class SArray<T> implements CustomCollections<T> {

    protected static final int DEFAULT_CAPACITY = 10;

    protected T[] arrays;
    protected int size;
    protected int capacity;


    public SArray() {
        arrays = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    public SArray(int initial) {
        arrays = (T[]) new Object[initial];
        capacity = initial;
    }

    public SArray(T[] arrays, int size, int capacity) {
        this.arrays = arrays;
        this.size = size;
        this.capacity = capacity;
    }

    @Override
    public int size() {
        return size;
    }

    protected int getCapacity() {
        return capacity;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) arrays[index];
    }

    @Override
    public void set(int index, T object) {
        checkIndex(index);
        checkNull(object, new NullArgumentException("null 값은 넣을 수 없습니다."));

        arrays[index] = object;
    }

    @Override
    public int indexOf(T object) {
        checkNull(object, new NullArgumentException());

        for(int i = 0; i < size; i++) {
            if(arrays[i] == null) continue;
            if(arrays[i].equals(object)) return i;
        }
        throw new ElementNotFoundException();
    }

    @Override
    public void add(T object) {
        checkNull(object, new NullArgumentException());

        if(size < capacity) {
            arrays[size] = object;
            size++;
        }else {
            grow();
            add(object);
        }

    }

    @Override
    public void add(int index, T object) {
        checkIndex(index);
        checkNull(object, new NullArgumentException());

        if(size < capacity) {
            for(int i = size - 1; i >= index; i--) {
                arrays[i + 1] = arrays[i];
            }
            arrays[index] = object;
            size++;
        }else {
            grow();
            add(index, object);
        }

    }

    private void grow() {
        capacity *= 2;
        arrays = Arrays.copyOf(arrays, capacity);
    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public T pop(int index) {
        if(size == 0) throw new EmptyArrayException();
        checkIndex(index);

        T popElement = (T) arrays[index];
        arrays[index] = null;

        for(int i = index + 1; i < size; i++) {
            arrays[i - 1] = arrays[i];
        }
        arrays[size - 1] = null;
        size--;
        return popElement;
    }

    @Override
    public T pop(T object) {
        return pop(indexOf(object));
    }

    private void checkIndex(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    private void checkNull(T object, NullArgumentException x) {
        if(object == null) throw x;
    }

    @Override
    public String toString() {
        String toStr = "";
        for(int i = 0; i < size; i++) {
            toStr += (arrays[i] + "\n");
        }
        return toStr;
    }
}
