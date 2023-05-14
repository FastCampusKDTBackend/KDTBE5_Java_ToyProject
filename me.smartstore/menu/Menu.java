package menu;

import exception.InputEndException;
import exception.InputRangeException;
import util.Message;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public interface Menu {
    Scanner scanner = new Scanner(System.in);

    default String nextLine() {
        return scanner.nextLine().toUpperCase();
    }

    default Integer nextInt(String end)  {
        System.out.println("** Press 'end', if you want to exit! **");
        String numStr = scanner.nextLine();
        try {
            if (numStr.equals(end)) {
                throw new InputEndException();
            }
            return Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
    }

    default Integer nextInt()  {
        String numStr = scanner.nextLine();
        try {
            return Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
    }

    default String nextLine(String end) throws InputEndException {
        System.out.println("** Press 'end', if you want to exit! **");
        String str = scanner.nextLine().toUpperCase();
        if (str.equals(end)) throw new InputEndException();
        return str;
    }

    default int chooseMenu(String[] menus) {
        while ( true ) { // 예외 복구 while
            try {
                System.out.println("===============================");
                for (int i = 0; i < menus.length; i++) {
                    System.out.printf(" %d. %s\n", i + 1, menus[i]);
                }
                System.out.println("===============================");
                System.out.print("Choose One: ");
                String choiceStr = nextLine();
                int choice = 0;
                try {
                    choice = Integer.parseInt(choiceStr);
                } catch (NumberFormatException e) {
                    throw new InputMismatchException();
                }
                if (choice >= 1 && choice <= menus.length) return choice;
                throw new InputRangeException(); // choice 가 범위에 벗어남

            } catch (InputMismatchException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }


    }

    void manage();
}
