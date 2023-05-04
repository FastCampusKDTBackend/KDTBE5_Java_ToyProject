package me.day10.smartstore.menu;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Printer {
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Printer INSTANCE = new Printer();
    private Printer() {}
    public static Printer getInstance() { return INSTANCE; }

    public void print(String str) throws RuntimeException {
        try {
            bw.write(str);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
