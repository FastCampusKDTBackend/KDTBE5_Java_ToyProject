package com.smartstore.util;

public class List implements Collections{
    @Override
    public int size() {
        return CAPACITY_SIZE;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public void set(int index, Object object) {

    }

    @Override
    public int indexOf(Object object) {
        return 0;
    }

    @Override
    public void add(Object object) {

    }

    @Override
    public void add(int index, Object object) {

    }

    @Override
    public Object pop() {
        return null;
    }

    @Override
    public Object pop(int index) {
        return null;
    }

    @Override
    public Object pop(Object object) {
        return null;
    }

    private final int CAPACITY_SIZE = 20;
}
