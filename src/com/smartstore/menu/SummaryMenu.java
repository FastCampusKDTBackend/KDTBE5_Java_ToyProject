package com.smartstore.menu;

public class SummaryMenu {

    public static SummaryMenu getInstance() {
        if(instance == null){
            instance = new SummaryMenu();
        }
        return instance;
    }

    private static SummaryMenu instance;

}
