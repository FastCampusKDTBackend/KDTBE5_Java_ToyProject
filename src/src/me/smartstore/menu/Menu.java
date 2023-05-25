package me.smartstore.menu;

import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.util.Message;

import java.util.InputMismatchException;
import java.util.Scanner;

import static me.smartstore.util.Message.ERR_MSG_INVALID_INPUT_FORMAT;

interface Menu {
    Scanner scanner = new Scanner(System.in);
    default String nextLine() {
        // end -> END , g - > G
        return scanner.nextLine().toUpperCase();
    }

    default String nextLine(String end) {
        System.out.println("** Press 'end', if you want to exit! **");
        String str = scanner.nextLine().toUpperCase();
        if (str.equals(end)) throw new InputEndException();
        return str;
    }
     default int chooseMenu(String[] menus ) {
        while (true) {
            try {
                System.out.println("===============================");
                for (int i = 0; i < menus.length; i++) {
                    System.out.printf(" %d. %s\n", i + 1, menus[i]);
                }
                System.out.println("===============================");
                System.out.print("Choose One: ");
                int choice = Integer.parseInt(nextLine());
                if (choice >= 1 && choice <= menus.length) return choice;
                throw new InputRangeException();
            }
            catch (NumberFormatException e){
                System.out.println(ERR_MSG_INVALID_INPUT_FORMAT);
            }
            catch (InputRangeException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
    default int formatInt(String inputData) {
        try {
            return Integer.parseInt(inputData);
        } catch (NumberFormatException e) {
            throw new InputMismatchException(ERR_MSG_INVALID_INPUT_FORMAT);
        }
    }

    void manage();
}
