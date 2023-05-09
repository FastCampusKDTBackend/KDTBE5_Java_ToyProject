package com.smartstore.function;

import com.smartstore.function.menu.MainMenuFunction;
import com.smartstore.function.menu.Screen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface MenuController {
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int currentMenuNumber = 0;

    default void displayMenu(String[] menus){
        for(int i = 0 ; i < menus.length ; i++){
            System.out.printf("%d. %s\n",i+1,menus[i]);
        }
    }

    default void runMenuSelectionLoop(String[] menus){
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
        handleChoice(String.valueOf(menu));
    }

    void handleChoice(String menuNumber);

    void run();

    default void run(int menuNumber) {
        displayMenu(Screen.of(menuNumber).getMenus());
        //get menu number from user until valid menu number
        runMenuSelectionLoop(Screen.of(menuNumber).getMenus());
    }

    default void returnToPrevMenu(int menuNumber){
        Function.of(menuNumber, MainMenuFunction.class).run();
    }

}
