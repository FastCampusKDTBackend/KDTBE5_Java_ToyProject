package com.smartstore.util;

public interface Map<K, V> {

    void put(K key, V value);

    V get(K key);

    boolean remove(K key);

    default V getOrDefault(K key, V defaultValue){
        V value = get(key);
        //if key is not exits, return defaultValue
        if(value == null){
            return defaultValue;
        }
        return value;
    }



}
