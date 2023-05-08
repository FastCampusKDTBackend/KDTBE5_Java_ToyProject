package util.arrays;

import util.arrays.exception.ElementNotFoundException;
import util.arrays.exception.EmptyArrayException;
import util.arrays.exception.NullArgumentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class MyArray<T> implements Collections<T>{

    protected T[] arrays;
    protected static final int DEFAULT = 10;
    protected int size;
    protected int capacity;

    public MyArray() throws ClassCastException {
        arrays = (T[]) new Object[DEFAULT];
        capacity = DEFAULT;
    }

    public MyArray(int initial) throws ClassCastException {
        arrays = (T[]) new Object[initial];
        capacity = initial;
    }

    public MyArray(T[] arrays) {
        this.arrays = arrays;
        capacity = arrays.length;
        size = arrays.length;
    }

    /**
     * 객체 배열의 요소 수를 반환합니다.
     *
     * @return 객체 배열의 요소 수
     */
    @Override
    public int size() {
        return size;
    }

    protected int capacity() {
        return capacity;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException{
        checkIndex(index);
        return arrays[index];
    }

    /**
     * @params: int, T
     * @throws: IndexOutOfBoundsException<br>NullArgumentException
     * */
    @Override
    public T set(int index, T object) throws NullArgumentException {
        if (Objects.isNull(object)) throw new NullArgumentException();
        checkIndex(index);
        T oldValue = arraysData(index);
        arrays[index] = object;
        return oldValue;
    }

    @Override
    public int indexOf(T object) throws NullArgumentException, ElementNotFoundException {
        if (Objects.isNull(object)) throw new NullArgumentException();
        for (int i = 0; i < size; i++) {
            if (object.equals(arrays[i]))
                return i;
        }
        throw new ElementNotFoundException();
    }

    @Override
    public void add(T object) throws NullArgumentException {
        if (Objects.isNull(object)) throw new NullArgumentException();

        if (size == arrays.length) {
            grow();
            add(object);
        } else {
            arrays[size++] = object;
        }
    }

    @Override
    public void add(int index, T object) throws IndexOutOfBoundsException, NullArgumentException{
        if (Objects.isNull(object)) throw new NullArgumentException();
        checkIndex(index);

        if (size < capacity) {
            for (int i = size-1; i >= index; i--) {
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
        return pop(size-1);
    }

    @Override
    public T pop(int index) throws IndexOutOfBoundsException {
        if (size == 0) throw new EmptyArrayException();
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        T popElement = arrays[index];
        arrays[index] = null;

        for (int i = index+1; i < size; i++) {
            arrays[i-1] = arrays[i];
        }
        arrays[size-1] = null;
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
        StringBuilder toStr = new StringBuilder();
        for (int i = 0; i < size; i++) {
            toStr.append(arrays[i]).append("\n");
        }
        return toStr.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }

    private T arraysData(int index) {
        return arrays[index];
    }

    public List<T> toList(){
        List<T> list = new ArrayList<>();
        for (T t : arrays) {
            if (!Objects.isNull(t))
                list.add(t);
        }
        return list;
    }
}
