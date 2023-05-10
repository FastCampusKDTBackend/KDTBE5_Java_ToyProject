package com.smartstore.function.mainmenu;

import com.smartstore.function.MenuHandler;
import com.smartstore.function.MenuPrintable;
import com.smartstore.function.MenuValidator;

import java.io.IOException;

public interface MainMenuHandler extends MenuPrintable, MenuHandler, MenuValidator {

    default void run() {
        boolean isExit = false;
        while (!isExit){
            printMenu(Screen.of(getCurrentMenuNumber()).getMenus());
            //get menu number from user until valid menu number
            isExit = handleChoice(getMenuNumber(Screen.of(getCurrentMenuNumber()).getMenus()));
        }
    }

    @Override
    default void run(int menuNumber) {
        boolean isExit = false;
        while (!isExit){
            printMenu(Screen.of(menuNumber).getMenus());
            //get menu number from user until valid menu number
            isExit = handleChoice(getMenuNumber(Screen.of(menuNumber).getMenus()));
        }
    }


    @Override
    default int getCurrentMenuNumber(){
        return Screen.MAIN_MENU.getMenuNumber();
    };

}
