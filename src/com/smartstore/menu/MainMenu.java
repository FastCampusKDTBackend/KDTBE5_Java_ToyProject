package com.smartstore.menu;

import java.util.Arrays;

public class MainMenu<T> implements Menu<T>, MenuController{

    @Override
    public void handleChoice(int menuNumber) {
        System.out.println("Handel called");
    }

    public static MainMenu getInstance() {
        if(instance == null){
            instance = new MainMenu();
        }
        return instance;
    }


    // TODO: 2023-05-04 make awesome splash screen
    private void printSplashScreen(){
        System.out.println(Arrays.toString(Screen.SPLASH.getMenus()));
    }

    public void run(){
        printSplashScreen();
        displayMenu(Screen.MAIN_MENU.getMenus());
        runMenuSelectionLoop();
    }
    private static MainMenu instance;

    private MainMenu(){

    }
}
