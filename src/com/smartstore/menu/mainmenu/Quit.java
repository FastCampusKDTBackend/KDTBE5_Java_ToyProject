package com.smartstore.menu.mainmenu;

import com.smartstore.menu.Menu;
import com.smartstore.menu.Screen;

import java.util.Arrays;

public class Quit implements Menu {
    @Override
    public void handleChoice(int menuNumber) {

    }

    @Override
    public void run(int menuNumber) {
        System.out.println(Arrays.toString(Screen.QUIT.getMenus()));
        System.exit(1);
    }

    public static Quit getInstance() {
        if(instance == null){
            instance = new Quit();
        }
        return instance;
    }

    private static Quit instance;
}
