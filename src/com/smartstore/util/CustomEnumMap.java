package com.smartstore.util;

import java.util.Arrays;

public class CustomEnumMap<K extends Enum<K>, V> implements Map<K, V>{

    //type of key
    //keyType must be Enum
    private final Class<K> keyType;

    private final Object[] values;

    private int size = 0;

    //if key == null, throw nullPointException
    public CustomEnumMap(Class<K> keyType) {
        this.keyType = keyType;
        K[] keys = keyType.getEnumConstants();
        values = new Object[keys.length];
    }

    @Override
    public void put(K key, V value) {
        if(isValidKey(key)){
            int index = key.ordinal();
            if(values[index] == null){
                size++;
            }
            values[index] = value;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get( K key) {
        if(isValidKey(key)){
            return (V) values[key.ordinal()];
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        if(isValidKey(key)){
            int index = key.ordinal();
            if(values[index] != null){
                size--;
            }
            values[index] = null;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    private boolean isValidKey(K key){
        if(key == null){
            return false;
        }
        //check is key proper type
        return key.getClass() == keyType || key.getClass().getSuperclass() == keyType;
    }

    @Override
    public String toString() {
        return "CustomEnumMap{" +
                "keys=" + Arrays.toString(keyType.getEnumConstants()) +
                ", values=" + Arrays.toString(values) +
                '}';
    }

}
