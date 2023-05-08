package me.smartstore.menu;

import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.util.Message;

import java.util.Scanner;

public interface Menu {
    Scanner scanner = new Scanner(System.in);

    default String nextLine() { // 하나의 프로그램 상에서 nextLine() 함수를 통해서 사용자 입력을 받음
        return scanner.nextLine().toUpperCase();
    }

    default String nextLine(String end) {
        System.out.println("** Press 'end', if you want to exit! **");
        String str = scanner.nextLine().toUpperCase();
        if (str.equals(end)) throw new InputEndException();
        return str;
    }

    default int chooseMenu(String[] menuText) {
        while(true) {
            try {
                System.out.println("\n=============================");
                for (int i = 0; i < menuText.length; i++) {
                    System.out.printf("%d. %s\n", i+1, menuText[i]);
                }
                System.out.println("=============================");
                System.out.print("Choose One: ");

                int inputNum = Integer.parseInt(nextLine());

                if (inputNum < 0 || inputNum > menuText.length) throw new InputRangeException();

                return inputNum;
            } catch (NumberFormatException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }

    void show();
}
