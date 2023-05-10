package com.smartstore.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface Readable {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
}
