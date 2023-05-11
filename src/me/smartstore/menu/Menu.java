package me.smartstore.menu;

import me.smartstore.exception.InputEmptyException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.exception.constant.Message;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface Menu {
    Scanner scanner = new Scanner(System.in);

    default String nextLine() {
        String data = this.scanner.nextLine().toUpperCase();
        String[] strings = data.split("\\s");
        return (strings.length > 1) ? "" : data;
    }

    default String nextLine(String end) {
        System.out.println("\n** Press 'end', if you want to exit! **");
        String str = scanner.nextLine().toUpperCase();
        if (str.equals(end)) throw new InputEndException();
        return str;
    }

    default void displayMenu(String[] menus) {
        System.out.println("\n==============================");
        for (int i = 0; i < menus.length; i++) {
            System.out.printf(" %d. %s\n", Integer.valueOf(i + 1), menus[i]);
        }
        System.out.println("==============================");
        System.out.print("Choose One: ");

    }
    default int chooseMenu(String[] menus) {
        while (true) {
            try {
                displayMenu(menus);

                int choice = Integer.parseInt(nextLine());

                if (choice >= 1 && choice <= menus.length) {
                    return choice;
                }
                throw new InputRangeException();
            } catch (InputMismatchException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_TYPE.getMessage());
            } catch (InputEmptyException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT.getMessage());
            }
        }
    }
    void manage();
}
