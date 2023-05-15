package com.smartstore.function.mainmenu;

import com.smartstore.util.*;

public interface MainMenuHandler extends EnumValueProvider, Handler, HandleableParam {

    default void run() {
        boolean isExit = false;
        while (!isExit) {
            Printer.printMenu(getMenuListFromEnum(MainMenuFunction.class, MainMenuFunction.MEMBERSHIP_MANAGEMENT.getMenuNumber(), MainMenuFunction.QUIT.getMenuNumber()));
            //get menu number from user until valid menu number
            isExit = handleChoice(Validator.getMenuNumber(getMenuListFromEnum(MainMenuFunction.class, MainMenuFunction.MEMBERSHIP_MANAGEMENT.getMenuNumber(), MainMenuFunction.QUIT.getMenuNumber())));

        }

    }

    @Override
    default <T> void run(T value) {
        boolean isExit = false;
        while (!isExit){
            Printer.printMenu(getMenuListFromEnum(MainMenuFunction.class, MainMenuFunction.MEMBERSHIP_MANAGEMENT.getMenuNumber(), MainMenuFunction.QUIT.getMenuNumber()));
            //get menu number from user until valid menu number
            isExit = handleChoice(String.valueOf(value));
        }
    }


    @Override
    default int getCurrentMenuNumber(){
        return MainMenuFunction.MAIN_MENU.getMenuNumber();
    }

}
