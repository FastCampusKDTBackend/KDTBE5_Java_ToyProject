package me.smartstore.domain.menu;

import me.smartstore.exception.InputRangeException;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface Menu {
    Scanner scanner = new Scanner(System.in);
    private String nextLine() {
        return scanner.next().toUpperCase();
    }

    private void displayMenu(String[] options) {
        StringBuilder builder = new StringBuilder();
        builder.append("===============================").append("\n");
        for (int i = 0; i < options.length; i++) {
            builder.append(String.format(" %d. %s\n", i + 1, options[i]));
        }
        builder.append("===============================").append("\n");
        System.out.println(builder);
        System.out.print("Choose One: ");
    }

    default int chooseMenu(String[] options) {
        while (true) {
            try {
                displayMenu(options);
                try {
                    int choice = Integer.parseInt(nextLine());
                    if (choice >= 1 && choice <= options.length) {
                        return choice;
                    }
                    throw new InputRangeException("입력한 숫자가 범위를 벗어났습니다.");
                } catch (NumberFormatException e) {
                    throw new InputMismatchException("입력한 데이터가 올바르지 않습니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch (InputRangeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void manage();
}
