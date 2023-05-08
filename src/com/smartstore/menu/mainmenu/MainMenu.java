package com.smartstore.menu.mainmenu;

import com.smartstore.menu.Menu;
import com.smartstore.menu.Screen;

import java.util.Arrays;

public class MainMenu implements Menu {

    @Override
    public void handleChoice(int menuNumber) {
        System.out.println("Handel called");
        //call Menu with menuNumber
        MainMenuFunction function = MainMenuFunction.of(menuNumber);
        function.run();
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

    @Override
    public void run(int menuNumber){
        //print splash screen
        printSlashScreen();
        //call run(super)
        Menu.super.run(menuNumber);
    }
    private static MainMenu instance;

    private MainMenu(){

    }
}
