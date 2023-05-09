package com.smartstore.function.menu;

import com.smartstore.function.Function;
import com.smartstore.function.MenuController;

public class Back implements MenuController {

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
