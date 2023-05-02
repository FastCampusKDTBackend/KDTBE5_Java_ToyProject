package com.smartstore.util;

public interface Entry<K, V>{
    K getKey();
    V getValue();
    void setValue(V value);
}
