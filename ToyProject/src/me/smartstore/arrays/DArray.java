package me.smartstore.arrays;

import me.smartstore.exception.EmptyArrayException;
import me.smartstore.group.Group;
import me.smartstore.util.Message;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.Arrays.copyOf;

public class DArray<T> implements Collections<T> {

    protected static final int DEFAULT = 10;

    protected T[] arrays;
    protected int size;
    protected int capacity;

    /**
     * ClassCastException 예외 던지는 이유
     * DArray Constructor에서 T타입이 Object 타입으로 캐스팅되는 과정에서 발생 가능
     *
     * 즉, T타입이 Object 타입과 호환되지 않아서 ClassCastException 예외 발생 가능
     */
    public DArray() throws ClassCastException {
        arrays = (T[]) new Object[DEFAULT];
        capacity = DEFAULT;
    }

    public DArray(int initial) throws ClassCastException {
        arrays = (T[]) new Object[initial];
        capacity = initial;
    }

    /**
     * ClassCastException 안 던지는 이유
     * T[] 배열을 이미 파라미터로 직접 전달 받음 > 예외 발생 가능성 없음
     */
    public DArray(T[] arrays) {
        this.arrays = arrays;
        capacity = arrays.length;
        size = arrays.length;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     *
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     *
     * 예외 처리를 두 번 해주는 이유
     * 메서드 호출자가 index value를 잘못 전달할 경우 걸러지지 않음.
     * if문 안에서는 인덱스 값이 유효하지 않은 경우에 대한 예외 처리 담당
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return arrays[index];
    }

    @Override
    public void set(int index, T object) {

    }

    @Override
    public int indexOf(T object) {
        return 0;
    }

    @Override
    public void add(T object) {
//        if (object == null) {
//            throw new
//        }
        if (size < capacity) {
            arrays[size] = object;
            size++;
        } else {
            extend();
            add(object);
        }
    }

    @Override
    public void add(int index, T object) {

    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public T pop(int index) throws EmptyArrayException, IndexOutOfBoundsException {
        if (size==0) throw new EmptyArrayException(Message.ERR_MSG_ARR_EMPTY);
        if (index < 0 || index > size) throw new IndexOutOfBoundsException(Message.ERR_MSG_ARR_OUT_OF_BOUNDARY);
        T item = arrays[index];
        for (int i = index; i < size; i++) {
            arrays[i] = arrays[i+1];
        }
        size--;
        return item;
    }

//    public boolean isEmpty() {
//
//    }

    @Override
    public T pop(T object) throws EmptyArrayException {
        return pop(size-1);
    }

    /**
     * 목적 : 리스트의 capacity가 모자랄 경우, *2만큼 증분하여 리스트 길이를 증가
     */
    @Override
    public void extend() {
        capacity *= 2;
        arrays = copyOf(arrays, capacity);
    }


//    @Override
//    public Iterator<T> iterator() {
//        return IntStream.range(0, size)
//                .mapToObj(i -> arrays[i])
//                .iterator();
//    }


}
