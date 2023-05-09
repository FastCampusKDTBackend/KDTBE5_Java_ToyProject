package com.smartstore.menu;

import com.smartstore.menu.mainmenu.MainMenuFunction;
import com.smartstore.util.Function;

public class Back implements Menu {

    public boolean isExit(){
        return true;
    }

    @Override
    public void handleChoice(String menuNumber) {

    }

    @Override
    public void run() {

    }

    @Override
    public void run(int menuNumber) {
        MainMenuFunction mainMenuFunction = Function.of(menuNumber, MainMenuFunction.class);
        mainMenuFunction.run();
    }
}
