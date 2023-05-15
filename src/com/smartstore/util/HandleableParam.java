package com.smartstore.util;

public interface HandleableParam {
    <T> void run(T value);
}
