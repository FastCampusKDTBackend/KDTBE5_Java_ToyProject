package com.smartstore.menu;

public class GradeMenu implements Menu, MenuController{
    @Override
    public void handleChoice(int menuNumber) {

    }

    public static GradeMenu getInstance() {
        if(instance == null){
            instance = new GradeMenu();
        }
        return instance;
    }

    private static GradeMenu instance;

    @Override
    public void run() {

    }
}

