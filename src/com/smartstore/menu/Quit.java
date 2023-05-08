package com.smartstore.menu;

public class Quit implements MenuController{
    @Override
    public void run() {

    }

    public static Quit getInstance() {
        if(instance == null){
            instance = new Quit();
        }
        return instance;
    }

    private static Quit instance;
}
