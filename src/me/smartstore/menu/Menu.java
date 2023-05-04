package me.smartstore.menu;

import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu {
    private static Menu menu;
    protected Scanner scanner = new Scanner(System.in);

    public static Menu getInstance() {
        if (menu == null)
            menu = new Menu();
        return menu;
    }

    public String nextLine() {
        return scanner.nextLine().toUpperCase();
    }

    public String nextLine(String messageForEnd) throws InputEndException {
        System.out.println("\n** Press 'end', if you want to exit! **");
        String str = this.scanner.nextLine().toUpperCase();

        if (str.equals(messageForEnd))
            throw new InputEndException();
        String[] strings = str.split("\\s");
        return (strings.length > 1) ? "" : str;
    }

    public int displayMenus(String[] menus) {
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
}
