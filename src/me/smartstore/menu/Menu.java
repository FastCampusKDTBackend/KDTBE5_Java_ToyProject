package me.smartstore.menu;

import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;

import java.util.InputMismatchException;
import java.util.Scanner;


public interface Menu {
    Scanner scanner = new Scanner(System.in);

    default String nextLine() {
        return scanner.nextLine().toUpperCase();
    }

    default String nextLine(String messageEnd) throws InputEndException {
        System.out.println("\n** Press 'end', if you want to exit! **");
        String str = nextLine();

        if (str.equals(messageEnd))
            throw new InputEndException();
        return str;
    }

    default int displayMenu(String[] menus) {
        while (true) {
            try {
                System.out.println("\n==============================");
                for (int i = 0; i < menus.length; i++) {
                    System.out.printf(" %d. %s\n", new Object[] { Integer.valueOf(i + 1), menus[i] });
                }
                System.out.println("==============================");
                System.out.print("Choose One: ");

                int choice = Integer.parseInt(nextLine());

                if (choice >= 1 && choice <= menus.length) {
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
