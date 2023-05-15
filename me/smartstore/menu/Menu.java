package me.smartstore.menu;

import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.util.Message;

import java.util.Scanner;

public interface Menu {
    Scanner sc = new Scanner(System.in);

    default String nextLine(){
        return sc.nextLine().toUpperCase();

    }

    default String nextLine(String end){
        System.out.println("** Press 'end', if you want to exit! **");
        String str = nextLine().toUpperCase();
        if(str.equals(end)) throw new InputEndException();
        return str;

    }

    default int chooseMenu(String[] menus){
        while( true ){
            try{
                System.out.println("==============================");
                for(int i = 0; i < menus.length; i++){
                    System.out.printf(" %d. %s\n", i + 1, menus[i]);
                }
                System.out.println("==============================");
                System.out.print("Choose one: ");
                int choice = Integer.parseInt(nextLine());
                if(choice >= 1 && choice <= menus.length)
                    return choice;
                throw new InputRangeException(); //choice 범위 초과

            } catch (InputRangeException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
            } catch (NumberFormatException e){
                System.out.println(Message.ERR_MSG_INVALID_INPUT_FORMAT);
            }
        }
    }

    //각 서브 메뉴들을 관리하는 메소드
    void manage();
}
