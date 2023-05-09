package com.smartstore.menu;

import com.smartstore.function.MenuController;

public class ReportMenu implements MenuController {
    @Override
    public void handleChoice(String menuNumber) {

    }

    @Override
    public void run() {

    }

    public static ReportMenu getInstance() {
        if(instance == null){
            instance = new ReportMenu();
        }
        return instance;
    }

    private static ReportMenu instance;

}
