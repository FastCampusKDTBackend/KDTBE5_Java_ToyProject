package com.smartstore.menu;

import java.util.Arrays;

public class MainMenu implements Menu {

    @Override
    public void handleChoice(int menuNumber) {
        System.out.println("Handel called");
        Screen screen = Screen.of(menuNumber);
        System.out.println(Arrays.toString(screen.getMenus()));
    }

    public static MainMenu getInstance() {
        if(instance == null){
            instance = new MainMenu();
        }
        return instance;
    }

    private void printSlashScreen(){
        System.out.println(Arrays.toString(Screen.of(-1).getMenus()));
    }

    public void run(int menuNumber){
        //print splash screen

        printSlashScreen();
        //print menu
        displayMenu(Screen.of(menuNumber).getMenus());
        //get menu number from user until valid menu number
        runMenuSelectionLoop(Screen.of(menuNumber).getMenus().length);
    }
    private static MainMenu instance;

    private MainMenu(){

    }
}
