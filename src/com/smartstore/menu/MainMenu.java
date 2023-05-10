package com.smartstore.menu;

import com.smartstore.function.Function;
import com.smartstore.function.MenuHandler;
import com.smartstore.function.menu.MainMenuFunction;
import com.smartstore.function.menu.Screen;

import java.util.Arrays;

public class MainMenu implements MenuHandler {
    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == MainMenuFunction.QUIT.getMenuNumber()) {
            return true;
        }
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), MainMenuFunction.class).run();
        return false;
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
    /*public void run(int menuNumber){
        //print splash screen
        printSlashScreen();
        MenuHandler.super.run(menuNumber);
    }*/

    private static MainMenu instance;

    private MainMenu(){

    }
}
