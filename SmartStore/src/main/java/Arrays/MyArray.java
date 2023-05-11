package Arrays;

import Arrays.Exceptions.ElementNotFoundException;
import Arrays.Exceptions.EmptyArrayException;
import Arrays.Exceptions.NullArgumentException;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyArray<T> implements Collections<T> {

    protected static final int DEFAULT = 10;
    protected T[] arrays;
    protected int size;
    protected int capacity;

    //기본값으로 생성 후 초기화
    public MyArray() throws ClassCastException {
        this.arrays = (T[]) new Object[DEFAULT];
        this.capacity = DEFAULT;
    }

    //배열의 크기를 받아서 생성 후 초기화
    public MyArray(int initial) throws ClassCastException {
        this.arrays = (T[]) new Object[initial];
        this.capacity = initial;
    }

    //배열을 받아서 초기화
    public MyArray(T[] arrays) {
        this.arrays = arrays;
        this.capacity = arrays.length;
        this.size = arrays.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    protected int capacity() {
        return this.capacity;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException, ElementNotFoundException {
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException("Wrong Index");
        if (arrays[index] == null) throw new ElementNotFoundException("Not Found");

        return this.arrays[index];
    }

    @Override
    public void set(int index, T object) throws IndexOutOfBoundsException, NullArgumentException {
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException("Wrong Index");
        if (object == null) throw new NullArgumentException("Object is Null");

        this.arrays[index] = object;
    }

    @Override
    public int indexOf(T object) throws NullArgumentException {
        if (object == null) throw new NullArgumentException("Object is Null");

        return IntStream.range(0, this.size)
                .filter(i -> this.arrays[i].equals(object))
                .findFirst()
                .orElseThrow(() -> new ElementNotFoundException("Not Found"));
    }

    @Override
    public void add(T object) throws NullArgumentException {
        if (object == null) throw new NullArgumentException("Object is Null");

        if (this.size < this.capacity) {
            this.arrays[size] = object;
            this.size++;
        } else {
            grow();
            add(object);
        }
    }

    @Override
    public void add(int index, T object) throws NullArgumentException {
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException("Wrong Index");
        if (object == null) throw new NullArgumentException("Object is Null");

        if (this.size < this.capacity) {
            for (int i = this.size - 1; i >= index; i--) {
                this.arrays[i + 1] = this.arrays[i];
            }
            this.arrays[index] = object;
            this.size++;
        } else {
            grow();
            add(index, object);
        }
    }

    @Override
    public T pop() {
        return pop(this.size - 1);
    }

    @Override
    public T pop(int index) throws EmptyArrayException, IndexOutOfBoundsException {
        if (this.size == 0) throw new EmptyArrayException("Arrays is Null");
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException("Wrong Index");

        T popElement = this.arrays[index];
        this.arrays[index] = null;

        for (int i = index + 1; i < this.size; i++) {
            this.arrays[i - 1] = this.arrays[i];
        }
        this.arrays[this.size - 1] = null;
        this.size--;

        return popElement;
    }

    @Override
    public T pop(T object) {
        return pop(indexOf(object));
    }

    @Override
    public String toString() {
        StringBuilder toStr = new StringBuilder();
        for (T element : this.arrays) {
            toStr.append(element + "\n");
        }

        return toStr.toString();
    }

    private void grow() {
        this.capacity *= 2;
        this.arrays = Arrays.copyOf(this.arrays, this.capacity);
    }
}
