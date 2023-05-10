package com.smartstore.function;

import com.smartstore.function.menu.MainMenuFunction;
import com.smartstore.function.menu.Screen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface MenuHandler extends DisplayMenuByNumber{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    default String runMenuSelectionLoop(String[] menus){
        int menu = -1;
        while (true){
            try {
                System.out.print("Input : ");
                menu = Integer.parseInt(br.readLine());
                if (menu <= 0 || menu > menus.length) {
                    // TODO: 2023-05-08 throw other exception, catch it
                    throw new NumberFormatException("Invalid Menu");
                }
                break;

            } catch (IOException | NumberFormatException e) {
                System.out.println("Invalid Menu");
            }
        }
        return String.valueOf(menu);
    }

    boolean handleChoice(String menuNumber);

    default void run(int menuNumber) {
        boolean isExit = false;
        while (!isExit){
            displayMenu(Screen.of(menuNumber).getMenus());
            //get menu number from user until valid menu number
            isExit = handleChoice(runMenuSelectionLoop(Screen.of(menuNumber).getMenus()));
        }

    }

    default void run(){

    }

}
