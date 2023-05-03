package me.smartstore.menu;

import me.smartstore.exception.InputExitException;
import me.smartstore.exception.InputFormatException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.util.Message;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public interface Menu {
    Scanner scanner= new Scanner(System.in);

    default String nextLine(){
        return scanner.nextLine().toUpperCase();
    }

    default String nextLine(String exitFlag) {
        System.out.println("** Press '"+exitFlag+"', if you want to exit! **");
        String str = scanner.nextLine().toUpperCase();
        if (str.equals(exitFlag.toUpperCase())){
            throw new InputExitException();
        }
        return str;
    }

    default int nextInt(){
        try{
            return Integer.parseInt(scanner.nextLine().toUpperCase());
        } catch (NumberFormatException e) {
            throw new InputFormatException();
        }
    }

    default int nextInt(String exitFlag){
        try{
            System.out.println("** Press '"+exitFlag+"', if you want to exit! **");
            String str = scanner.nextLine().toUpperCase();
            if (str.equals(exitFlag.toUpperCase())){
                throw new InputExitException();
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new InputFormatException();
        }
    }

    void root();    // 각 메뉴의 최상위

    default int chooseMenu(String[] menus){
        while ( true ) {
            try {
                System.out.println("\n==============================");
                Arrays.stream(menus).forEach(System.out::println);
                System.out.println("==============================");

                System.out.print("Choose One: ");
                int choice = nextInt();

                return choice;
            } catch (InputFormatException e) {
                System.out.println(Message.ERR_INVALID_INPUT_FORMAT);
            }
        }
    }

    static boolean back(){
        return true;
    }
}
