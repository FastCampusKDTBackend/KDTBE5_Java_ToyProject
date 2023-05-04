package menu;

import exception.InputEndException;
import exception.InputRangeException;
import group.Groups;
import util.Message;

import java.util.Locale;
import java.util.Scanner;

public interface Menu {
    public Scanner scanner = new Scanner(System.in);

    default String nextLine(){ //String
        return scanner.nextLine().toUpperCase(); //대소문자 구분 없애기 위해
    }
    default String nextLine(String end) {
        System.out.println("** Press 'end', if you want to exit! **");
        String str = scanner.nextLine().toUpperCase();
        if (str.equals(end)) throw new InputEndException();
        return str;
    }
    /**public String nextLine(){ //Integer
        String str = scanner.nextLine().toUpperCase(); //대소문자 구분 없애기 위해
        return str;
    }*/
    default int chooseMenu(String[] menus){
        while(true) { //예외 복구 while
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
            } catch (NumberFormatException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            } catch (InputRangeException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }
    void manage(); //각 서브메뉴들을 관리하는 함수

}
