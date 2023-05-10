package com.smartstore.function.mainmenu;

public class SummaryMenu {

    public static SummaryMenu getInstance() {
        if(instance == null){
            instance = new SummaryMenu();
        }
        return instance;
    }

    private static SummaryMenu instance;

}
