package me.smartstore.domain.menu;

import me.smartstore.utils.exception.InputEndException;
import me.smartstore.utils.exception.InputRangeException;

import java.util.InputMismatchException;
import java.util.Scanner;

import static me.smartstore.utils.constant.Message.*;

public interface Menu {
    Scanner scanner = new Scanner(System.in);
    default String nextLine() {
        return scanner.next().toUpperCase();
    }

    default String nextLine(String end) {
        System.out.println("** Press 'end', if you want to exit! **");
        String inputData = nextLine();
        if (inputData.equals(end)) {
            throw new InputEndException();
        }
        return inputData;
    }

    default int convertInt(String inputData) {
        try {
            return Integer.parseInt(inputData);
        } catch (NumberFormatException e) {
            throw new InputMismatchException(ERR_MSG_INVALID_INPUT_FORMAT.getMessage());
        }
    }

    default void displayMenu(String[] options) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n===============================").append("\n");
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
                int choice = convertInt(nextLine());
                if (choice >= 1 && choice <= options.length) {
                    return choice;
                }
                throw new InputRangeException();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch (InputRangeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void manage();
}
