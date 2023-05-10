package com.smartstore.function;

import com.smartstore.function.mainmenu.Screen;

public class Back implements MenuHandler {
    private static Back instance;

    private Back(){

    }

    public static Back getInstance() {
        if(instance == null){
            instance = new Back();
        }
        return instance;
    }

    @Override
    public String runMenuSelectionLoop(String[] menus) {
        return null;
    }

    @Override
    public boolean handleChoice(String menuNumber) {
        return false;
    }

    @Override
    public void run(){

    }

    @Override
    public void run(int menu) {

    }

    @Override
    public int getCurrentMenuNumber() {
        return Screen.QUIT.getMenuNumber();
    }

}
