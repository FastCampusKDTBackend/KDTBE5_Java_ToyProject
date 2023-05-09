package com.smartstore.function.menu;

import com.smartstore.function.MenuController;

import java.util.Arrays;

public class Quit implements MenuController {
    @Override
    public void handleChoice(String menuNumber) {

    }

    @Override
    public void run() {

    }

    @Override
    public void run(int menuNumber) {
        System.out.println(Arrays.toString(Screen.QUIT.getMenus()));
        System.exit(1);
    }
}
