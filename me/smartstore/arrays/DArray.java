package me.smartstore.arrays;

import me.smartstore.exception.ElementNotFoundException;
import me.smartstore.exception.EmptyArrayException;
import me.smartstore.exception.NullArgumentException;

public class DArray<T> implements Collections<T> { // Dynamic Array
    protected static final int DEFAULT = 10;

    protected T[] arrays;
    protected int size; //배열 원소의 개수
    protected int capacity; //배열의 전체 크기

    public DArray() throws ClassCastException {
        arrays = (T[]) new Object[DEFAULT];
        capacity = DEFAULT;
    }

    public DArray(int initial) throws ClassCastException {
        arrays = (T[]) new Object[initial];
        capacity = initial;
    }

    public DArray(T[] arrays) {
        this.arrays = arrays;
        capacity = arrays.length;
        size = arrays.length;
    }

    /////////////////////////////////////////
    // add, set, get, pop, indexOf, size, capacity (for dynamic-sized array)

    @Override
    public int size() {
        return size;
    }

    // 배열에 얼마나 capacity 남아있는지 외부에 알려줄 필요가 없기 때문에 <protected>으로 정의
    protected int capacity() {
        return capacity;
    }


    //arrays의 index 위치에 있는 값을 반환
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return arrays[index];
    }

    /**
     * @param: ...
     * @return: ...
     * @throws: IndexOutOfBoundsException
     * @throws: NullArgumentException
     * */
    @Override
    public void set(int index, T object) throws IndexOutOfBoundsException, NullArgumentException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (object == null) throw new NullArgumentException();

        arrays[index] = object;
    }


    //object의 index를 반환
    @Override
    public int indexOf(T object) throws NullArgumentException, ElementNotFoundException {
        if (object == null) throw new NullArgumentException(); // not found (instead of throwing exception)

        for (int i = 0; i < size; i++) {
            if (arrays[i] == null) continue;
            if (arrays[i].equals(object)) return i;
        }
        throw new ElementNotFoundException(); // not found
    }

    // 배열의 cap이 부족한 경우
    @Override
    public void add(T object) throws NullArgumentException {
        if (object == null) throw new NullArgumentException(); // if argument is null, do not add null value in array

        if (size < capacity) {
            arrays[size] = object;
            size++;
        } else {
            grow();
            add(object);
        }
    }

    //원하는 index 위치로 데이터 넣기
    @Override
    public void add(int index, T object) throws IndexOutOfBoundsException, NullArgumentException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (object == null) throw new NullArgumentException();

        if (size < capacity) {
            for (int i = size-1; i >= index ; i--) {
                arrays[i+1] = arrays[i];
            }
            arrays[index] = object;
            size++;
        } else {
            grow();
            add(index, object);
        }
    }


    //배열의 맨 끝 원소 pop
    @Override
    public T pop() {
//        if (size == 0) return null;
//
//        T popElement = arrays[size-1];
//        arrays[size-1] = null;
//        size--;
//        return popElement;
        return pop(size-1);
    }

    //index의 원소 pop
    @Override
    public T pop(int index) throws IndexOutOfBoundsException {
        if (size == 0) throw new EmptyArrayException();
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        T popElement = arrays[index];
        arrays[index] = null; // 삭제됨을 명시적으로 표현

        for (int i = index+1; i < size; i++) {
            arrays[i-1] = arrays[i];
        }
        arrays[size-1] = null;
        size--;
        return popElement;
    }

    //원하는 데이터 pop
    @Override
    public T pop(T object) {
        return pop(indexOf(object));
    }

    protected void grow() {
        capacity *= 2; // doubling
        arrays = java.util.Arrays.copyOf(arrays, capacity);

        // size는 그대로
    }

    @Override
    public String toString() {
        String toStr = "";
        for (int i = 0; i < size; i++) {
            toStr += (arrays[i] + "\n");
        }
        return toStr;
    }
}