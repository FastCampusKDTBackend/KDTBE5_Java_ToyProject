package me.smartstore.menu;

import me.smartstore.util.exception.InputEndException;
import me.smartstore.util.exception.InputRangeException;
import me.smartstore.util.Message;

import java.util.Scanner;

public interface Menu {
    Scanner scanner = new Scanner(System.in);

    default String nextLine() {
        return scanner.nextLine().toUpperCase();
    }

    default String nextLine(String end) {
        System.out.println(" ** Press 'end', if you want to exit! ** ");
        String str = scanner.nextLine().toUpperCase();
        if (str.equals(end)) throw new InputEndException();
        return str;
    }

    // TODO Empty 들어올 경우

    default void displayMenu(String[] menus) {
        System.out.println("===========================================");
        for (int i = 0; i < menus.length; i++) {
            System.out.printf(" %d. %s\n", i + 1, menus[i]);
        }
        System.out.println("===========================================");
        System.out.print("Choose One: ");

    }

    /*
    * @nextInt()를 안쓰는 이유 : 문자가 들어오면 한번더 체크 해주는 이슈
    * @NumberFormatException : 잘못된 값 들어온 경우 (문자, 특수문자... etc)
    * @InputRangeException : 숫자인데 범위 넘어간 경우
    * */
    default int chooseMenu(String[] menus) {
        while (true) {
            try {
                displayMenu(menus);
                int choice = Integer.parseInt(nextLine());
                System.out.println();
                if (choice >= 1 && choice <= menus.length) return choice;
                throw new InputRangeException();

            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
                System.out.println();

            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                System.out.println();
            }
        }
    }
    /*
    * @manage() : 서브 메뉴들 관리하는 함수
    * */

    void manage();
}