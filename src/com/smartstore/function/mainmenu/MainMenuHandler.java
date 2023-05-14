package com.smartstore.function.mainmenu;

import com.smartstore.function.*;

public interface MainMenuHandler extends MenuPrintable, EnumValueProvider, Handler, MenuValidator, HandleableParam {

    default void run() {
        boolean isExit = false;
        while (!isExit){
            printMenu(getMenuListFromEnum(MainMenuFunction.class, MainMenuFunction.MEMBERSHIP_MANAGEMENT.getMenuNumber(), MainMenuFunction.QUIT.getMenuNumber()));
            //get menu number from user until valid menu number
            //isExit = handleChoice(getMenuNumber(Screen.of(getCurrentMenuNumber()).getMenus()));
            isExit = handleChoice(getMenuNumber(getMenuListFromEnum(MainMenuFunction.class, MainMenuFunction.MEMBERSHIP_MANAGEMENT.getMenuNumber(), MainMenuFunction.QUIT.getMenuNumber())));

        }
    }

    @Override
    default void run(int menuNumber) {
        boolean isExit = false;
        while (!isExit){
            printMenu(getMenuListFromEnum(MainMenuFunction.class, MainMenuFunction.MEMBERSHIP_MANAGEMENT.getMenuNumber(), MainMenuFunction.QUIT.getMenuNumber()));
            //get menu number from user until valid menu number
            isExit = handleChoice(String.valueOf(menuNumber));
        }
    }


    @Override
    default int getCurrentMenuNumber(){
        return Screen.MAIN_MENU.getMenuNumber();
    }

}
