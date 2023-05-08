package com.smartstore.menu;

public class ReportMenu implements Menu, MenuController{
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
    public void run() {

    }
}
