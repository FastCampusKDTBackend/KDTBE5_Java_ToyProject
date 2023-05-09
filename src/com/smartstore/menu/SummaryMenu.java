package com.smartstore.menu;

import com.smartstore.function.MenuController;

public class SummaryMenu implements MenuController {
    @Override
    public void handleChoice(String menuNumber) {

    }

    @Override
    public void run() {

    }

    public static SummaryMenu getInstance() {
        if(instance == null){
            instance = new SummaryMenu();
        }
        return instance;
    }

    private static SummaryMenu instance;

}
