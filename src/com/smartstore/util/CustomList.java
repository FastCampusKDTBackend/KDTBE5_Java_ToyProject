package com.smartstore.util;

import java.util.Arrays;


public class CustomList<T> implements List<T> {
    @Override
    public int size() {
        return this.size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(T object) {
        return (T) elements[findIndex(object)];
    }


    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        isValidIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(int index, T object) {
        isValidIndex(index);

        if(elements[index] != null){
            elements[index] = object;
        }
    }

    @Override
    public int indexOf(T object) {
        return findIndex(object);
    }

    private int findIndex(Object o){
        if(o == null){
            throw new IllegalArgumentException("Null Can't be found");
        }
        for(int i = 0 ; i< size ; i++){
            if (o.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    @SuppressWarnings("unchked")
    public T[] toArray(T[] type){
        return (T[]) Arrays.copyOf(elements, size, type.getClass());
    }

    @Override
    @SuppressWarnings("unchked")
    public T[] toArray(){
        return (T[]) Arrays.copyOf(elements, size);
    }
    @Override
    public void add(T object) {
        if(size == elements.length){
            resize(size+1);
        }
        elements[size++] = object;
    }

    @Override
    public void add(int index, T object) {
        isValidIndex(index);
        if(size == elements.length){
            resize(size+1);
        }
        fastCopy(index, index + 1, elements.length - index);
        elements[index] = object;
    }

    @Override
    public boolean remove(int index) {
        isValidIndex(index);
        fastCopy(index + 1, index, elements.length - index - 1);
        resize(elements.length - 1);
        size--;
        return true;
    }

    @Override
    public boolean remove(T object) {
        if(object != null){
            int index = indexOf(object);
            if (index < 0){
                return false;
            }
            if(remove(index)){
                return true;
            };
        }
        throw new IllegalArgumentException("Null Can't be found");
    }

    public void addAll(int index, CustomList<? extends T> newElements){
        isValidIndex(index);
        Object[] e = newElements.toArray();
        int newSize = e.length;
        if(newSize == 0){
            return;
        }

        final int currentSize = this.elements.length;
        if(newSize > currentSize - size){
            resize(currentSize + newSize );
        }

        int movedIndex = currentSize - index;
        if(movedIndex > 0){
            fastCopy(index, index + newSize, movedIndex);
        }
        System.arraycopy(e, 0, elements, index, newSize);
        size = currentSize + newSize;
    }

    public CustomList<T> subList(int fromIndex, int toIndex){
        CustomList<T> subList = new CustomList<>();
        for(int i = fromIndex ; i <= toIndex ; i++){
            subList.add((T)elements[i]);
        }
        return subList;
    }

    private void fastCopy(int index, int desPosition, int movedIndex){
        System.arraycopy(elements, index, elements, desPosition, movedIndex);
    }
    private void resize(int newLength){
        //if requested size is overflow
        if(elements.length + newLength < 0){
            throw new OutOfMemoryError("Can't Resize Array.\n Integer overflow in requested size.\n");
        }
        elements = Arrays.copyOf(elements, newLength);
    }

    private void isValidIndex(int index){
        if(index < 0 || index >= size){
            throw new ArrayIndexOutOfBoundsException("Invalid Index");
        }
    }

    @Override
    public String toString() {
        if (elements == null)
            return "";

        int iMax = elements.length - 1;
        if (iMax == -1)
            return "";

        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(elements[i]);
            if (i == iMax)
                return b.toString();
        }
    }

    private int size;

    private Object[] elements;

    public CustomList() {
        this.elements = new Object[]{};
    }
}
