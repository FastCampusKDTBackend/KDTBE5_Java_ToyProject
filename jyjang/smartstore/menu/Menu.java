package jyjang.smartstore.menu;

import jyjang.smartstore.exception.InputEndException;
import jyjang.smartstore.exception.InputRangeException;
import jyjang.smartstore.util.Message;

import java.util.Scanner;

public interface Menu {

    Scanner scanner = new Scanner(System.in);

    // 필요 없으면 사용하지 않아도 괜찮다고 하심
    default String nextLine() {
        return scanner.nextLine().toUpperCase();
    }

    default String nextLine(String end) throws InputEndException {
        System.out.println("** Press 'end', if you want to exit! **");
        String inputString = scanner.nextLine().toUpperCase();
        if (inputString.equals(end)) {
            throw new InputEndException();
        }

        return inputString;
    }


    // 예외 복구 While
    default int displayMenu(String[] menus) {

        while (true) {

            try {
                System.out.println("==============================");
                for (int  i = 0; i < menus.length; i++) {
                    System.out.printf(" %d. %s\n", i + 1, menus[i]);
                }

                System.out.println("==============================");
                System.out.print("Choose One : ");
                int choice = Integer.parseInt(nextLine());
                if (choice >= 1 && choice <= menus.length) {
                    return choice;
                }

                throw new InputRangeException();

            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);

            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);

            }
        }
    }

    // 각 서브메뉴를 관리하는 함수(서브 메뉴의 최상위 함수)
    void manage();
}
