package com.smartstore.function.menu;

import com.smartstore.function.Function;
import com.smartstore.function.MenuController;

import java.util.Arrays;

public class MenuManager implements MenuController {
    @Override
    public void handleChoice(String menuNumber) {
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), MainMenuFunction.class).run();
    }

    @Override
    public void run() {
        //Do nothing
    }

    public static MenuManager getInstance() {
        if(instance == null){
            instance = new MenuManager();
        }
        return instance;
    }

    private void printSlashScreen(){
        System.out.println(Arrays.toString(Screen.of(-1).getMenus()));
    }
    public void run(int menuNumber){
        //print splash screen
        printSlashScreen();
        MenuController.super.run(menuNumber);
    }

    private static MenuManager instance;

    private MenuManager(){

    }
}
