package com.smartstore.menu.mainmenu;

import com.smartstore.menu.Menu;
import com.smartstore.menu.Screen;
import com.smartstore.util.Function;

import java.util.Arrays;

public class MainMenu implements Menu {
    @Override
    public void handleChoice(String menuNumber) {
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), MainMenuFunction.class).run();
    }

    @Override
    public void run() {
        //Do nothing
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
        Menu.super.run(menuNumber);
    }

    private static MainMenu instance;

    private MainMenu(){

    }
}
