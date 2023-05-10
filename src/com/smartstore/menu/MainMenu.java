package com.smartstore.menu;

import com.smartstore.function.Function;
import com.smartstore.function.mainmenu.MainMenuHandler;
import com.smartstore.function.mainmenu.MainMenuFunction;
import com.smartstore.function.mainmenu.Screen;

import java.util.Arrays;

public class MainMenu implements MainMenuHandler {
    private static MainMenu instance;
    public static MainMenu getInstance() {
        if(instance == null){
            instance = new MainMenu();
        }
        return instance;
    }

    private MainMenu(){

    }

    private void printSlashScreen(){
        System.out.println(Arrays.toString(Screen.of(-1).getMenus()));
    }

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == MainMenuFunction.QUIT.getMenuNumber()) {
            return true;
        }
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), MainMenuFunction.class).run();
        return false;
    }

    @Override
    public void run() {
        printSlashScreen();
        MainMenuHandler.super.run();
    }

}
