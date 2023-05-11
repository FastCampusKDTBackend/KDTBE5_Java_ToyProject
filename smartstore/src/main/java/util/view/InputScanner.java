package util.view;

import java.util.Scanner;

final public class InputScanner {
    private static final Scanner scanner = new Scanner(System.in);

    private InputScanner() {
    }

    public static Scanner get() {
        return scanner;
    }
}
