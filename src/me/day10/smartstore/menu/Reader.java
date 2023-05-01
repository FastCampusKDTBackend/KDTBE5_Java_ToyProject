package me.day10.smartstore.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class Reader {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final Reader INSTANCE = new Reader();
    private Reader() {}
    public static Reader getInstance() { return INSTANCE; }

    public int inputInteger() throws NumberFormatException {
        StringTokenizer st = new StringTokenizer(nextLine());
        try {
            return Integer.parseInt(st.nextToken());
        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
    }

    private String nextLine() throws RuntimeException {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
