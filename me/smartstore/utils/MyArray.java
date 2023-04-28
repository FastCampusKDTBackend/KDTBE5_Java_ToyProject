package me.smartstore.utils;

import me.smartstore.utils.exception.ElementNotFoundException;
import me.smartstore.utils.exception.EmptyArrayException;
import me.smartstore.utils.exception.NullArgumentException;

/**
 * 사용자 정의 동적 배열 클래스
 */
public class MyArray<T> implements Collections<T> {

    protected static final int DEFAULT = 10;

    protected T[] arrays;
    protected int size;
    protected int capacity;

    /**
     * 기본 설정 크기로 배열을 생성할 수 있는 빈 생성자입니다.
     * @throws ClassCastException 입력된 제너릭 타입의 배열로 만들지 못할 때 예외를 발생합니다.
     */
    public MyArray() throws ClassCastException {
        arrays = (T[]) new Object[DEFAULT];
        capacity = DEFAULT;
    }

    /**
     * 초기 배열 크기를 지정할 수 있는 생성자입니다.
     * @param initial 배열의 초기 Size를 넣습니다.
     * @throws ClassCastException 입력된 제너릭 타입의 배열로 만들지 못할 때 예외를 발생합니다.
     */
    public MyArray(int initial) throws ClassCastException {
        arrays = (T[]) new Object[initial];
        capacity = initial;
    }

    /**
     * 배열을 입력받아 초기 배열 상태를 초기화할 수 있는 생성자입니다.
     * @param arrays 배열을 입력받아 초기 배열 상태를 초기화합니다.
     */
    public MyArray(T[] arrays) {
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

    /**
     * 입력된 index에 위치하는 데이터를 반환해주는 메서드입니다.
     * @param index 찾고자하는 index를 정수형으로 입력해주세요.
     * @return 해당 index에 존재하는 데이터를 반환합니다.
     * @throws IndexOutOfBoundsException 입력된 index의 범위가 배열의 범위를 벗어났을 때 예외를 발생합니다.
     * @throws ElementNotFoundException 입력된 index에 null 값이 존재할 때 예외를 발생합니다.
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException, ElementNotFoundException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index 범위를 벗어났습니다.");
        }
        if (arrays[index] == null) {
            throw new ElementNotFoundException("해당 index에 데이터가 존재하지 않습니다.");
        }
        return arrays[index];
    }

    /**
     * 배열에 데이터를 입력할 수 있는 메서드입니다.
     * @param index 배열에 넣고자 하는 인덱스를 정수값으로 입력합니다.
     * @param object 해당 인덱스에 넣고자하는 객체를 입력합니다.
     * @throws IndexOutOfBoundsException 입력된 index의 범위가 배열의 범위를 벗어났을 때 예외를 발생합니다.
     * @throws NullArgumentException 입력된 object가 null일 때 예외를 발생합니다.
     */
    @Override
    public void set(int index, T object) throws IndexOutOfBoundsException, NullArgumentException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index 범위를 벗어났습니다.");
        }
        if (object == null) {
            throw new NullArgumentException("입력된 object가 Null입니다.");
        }

        arrays[index] = object;
    }

    /**
     * 배열에서 입력된 object의 index를 반환해주는 메서드입니다.
     * @param object 배열에서 찾고자하는 object를 입력하세요.
     * @return 배열에서 최초 발견된 object의 index가 반환됩니다.
     * @throws NullArgumentException 입력된 object가 null일 때 예외를 발생합니다.
     * @throws ElementNotFoundException 입력된 object가 배열에 존재하지 않을 때 예외를 발생합니다.
     */
    @Override
    public int indexOf(T object) throws NullArgumentException, ElementNotFoundException {
        if (object == null) {
            throw new NullArgumentException("입력된 object가 Null입니다.");
        }

        for (int i = 0; i < size; i++) {
            if (arrays[i] == null) continue;
            if (arrays[i].equals(object)) return i;
        }
        throw new ElementNotFoundException("해당 객체(데이터)는 배열에 존재하지 않습니다.");
    }

    /**
     * 배열의 가장 뒤에 데이터를 추가할 수 있는 메서드입니다.
     * @param object 배열에 추가하고자하는 object를 입력해주세요.
     * @throws NullArgumentException 입력된 object가 Null일 때 예외를 발생합니다.
     */
    @Override
    public void add(T object) throws NullArgumentException {
        if (object == null) {
            throw new NullArgumentException("입력된 object가 Null입니다.");
        }

        if (size < capacity) {
            arrays[size] = object;
            size++;
        } else {
            grow();
            add(object);
        }
    }

    /**
     * 배열의 원하는 index에 데이터를 추가할 수 있는 메서드입니다.
     * @param index 데이터를 넣고자하는 index를 정수형으로 입력해주세요.
     * @param object 배열에 추가하고자하는 object를 입력해주세요.
     * @throws IndexOutOfBoundsException 입력된 index의 범위가 배열의 범위를 벗어났을 때 예외를 발생합니다.
     * @throws NullArgumentException 입력된 object가 Null일 때 예외를 발생합니다.
     */
    @Override
    public void add(int index, T object) throws IndexOutOfBoundsException, NullArgumentException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index 범위를 벗어났습니다.");
        }
        if (object == null) {
            throw new NullArgumentException("입력된 object가 Null입니다.");
        }

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

    /**
     * 배열의 가장 마지막에 있는 데이터를 반환합니다. 이때 해당 데이터는 배열에서 삭제됩니다.
     * @return 배열의 가장 마지막에 있는 데이터를 반환합니다.
     * @throws EmptyArrayException 배열이 비어있는 경우 예외를 발생합니다.
     */
    @Override
    public T pop() throws EmptyArrayException {
        return pop(size-1);
    }

    /**
     * 배열에서 원하는 index에 있는 데이터를 반환합니다. 이때 해당 데이터는 배열에서 삭제됩니다.
     * @param index 원하는 index를 정수형으로 입력해주세요.
     * @return 배열에서 index 위치에 있는 데이터를 반환합니다.
     * @throws IndexOutOfBoundsException 입력된 index의 범위가 배열의 범위를 벗어났을 때 예외를 발생합니다.
     * @throws EmptyArrayException 배열이 비어있는 경우 예외를 발생합니다.
     */
    @Override
    public T pop(int index) throws IndexOutOfBoundsException, EmptyArrayException {
        if (size == 0) {
            throw new EmptyArrayException("배열이 비어있습니다.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index 범위를 벗어났습니다.");
        }

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

    /**
     * 배열에 데이터를 입력 시 용량이 부족할 때, 증가시켜주는 내부 메서드입니다.
     */
    private void grow() {
        capacity *= 2;
        arrays = java.util.Arrays.copyOf(arrays, capacity);
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
