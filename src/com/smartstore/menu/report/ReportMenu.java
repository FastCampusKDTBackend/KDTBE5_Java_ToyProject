package com.smartstore.menu.report;

import com.smartstore.menu.Menu;

public class ReportMenu implements Menu {
    @Override
    public void handleChoice(int menuNumber) {

    }

    public static ReportMenu getInstance() {
        if(instance == null){
            instance = new ReportMenu();
        }
        return instance;
    }

    private static ReportMenu instance;


    @Override
    public void run(int menuNumber) {

    }
}
