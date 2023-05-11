package domain.menu;
//각각의 메뉴는 객체가 하나만 있나?
//각 메뉴는 싱글톤으로 가야 하나?

import handler.exception.InputEndException;
import handler.exception.InputFormatException;
import handler.exception.InputRangeException;

import java.io.IOException;
import java.util.Scanner;

public interface Menu {

    //    String menuNumFormat = "^[1-9]*$";
    Scanner sc = new Scanner(System.in);

    default String nextLine() {
        return sc.nextLine();
    }

    default String nextLine(String END_MSG) {
        System.out.println("** Press 'end', if you want to exit! **");
        String str = sc.nextLine();
        if (str.toUpperCase().equals(END_MSG)) {
            throw new InputEndException();
        }
        return str;
    }

    default String nextLineUpper() {
        return sc.nextLine().toUpperCase();
    }

    default String nextLineUpper(String END_MSG) {
        System.out.println("** Press 'end', if you want to exit! **");
        String str = sc.nextLine().toUpperCase();
        if (str.equals(END_MSG)) {
            throw new InputEndException();
        }
        return str;
    }

    default int nextInt() throws InputFormatException {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new InputFormatException();
        }
    }

    default int nextInt(String END_MSG) throws InputFormatException, InputEndException {
        try {
            System.out.println("** Press 'end', if you want to exit! **");
            String str = sc.nextLine().toUpperCase();

            if (str.equals(END_MSG)) {
                throw new InputEndException();
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new InputFormatException();
        }
    }

    default boolean isInvalidRange(int choice, int limit) {
        return (choice < 0 || choice > limit);
    }

    default void showMenu(String[] menus) {
        System.out.println("\n===============================");
        for (int i = 0; i < menus.length; i++) {
            System.out.printf(" %d. %s\n", i + 1, menus[i]);
        }
        System.out.println("===============================");
        System.out.print("Choose One: ");
    }

    default int selectMenu(String[] menus) {
        while (true) {
            try {
                showMenu(menus);
                int choice = nextInt();
                //사용자가 입력한 메뉴번호가 범위에서 벗어나면 Exception을 던진다.
                if (isInvalidRange(choice, menus.length)) throw new InputRangeException();

                return choice;

            } catch (InputRangeException | InputFormatException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void manage();
}//end of class
