package com.smartstore.function;

public interface HandleableParam {
    <T> void run(T value);
}
