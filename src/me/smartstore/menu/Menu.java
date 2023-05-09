package me.smartstore.menu;

import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.group.GroupType;
import me.smartstore.utils.Message;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface Menu {
    Scanner scanner = new Scanner(System.in);

    default String nextLine() {
        return scanner.nextLine().toUpperCase();
    }

    default String nextLine(String end) {
        System.out.println("** Press 'end', if you want to exit! **");
        String str = scanner.nextLine().toUpperCase();
        if (str.equals(end)) throw new InputEndException();
        return str;
    }

    default Integer nextLine(Integer size){
        while ( true ) {
            try {
                System.out.println("Which customer ( 1 ~ " + size + " )?");
                Integer num = scanner.nextInt();
                if(num <= 0 || num > size) throw new InputRangeException();
                return num;
                // chooseMenu 로 통합하고 출력문만 변경?
            } catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                return null;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    default int chooseMenu(String[] menus) {
        while (true){
            try {
                System.out.println("===============================");
                for (int i = 0; i < menus.length; i++) {
                    System.out.printf(" %d. %s\n", i + 1, menus[i]);
                }
                System.out.println("===============================");
                System.out.print("Choose One: ");
                int choice = Integer.parseInt(nextLine());
                if (choice >= 1 && choice <= menus.length) return choice;
                throw new InputRangeException(); // choice 가 범위에 벗어남
            } catch (InputMismatchException | NumberFormatException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    void manage(); // 각 서브메뉴들을 관리하는 함수 (각 서브메뉴의 최상위 메뉴)
}
