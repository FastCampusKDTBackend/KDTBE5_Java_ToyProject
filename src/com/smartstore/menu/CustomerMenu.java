package com.smartstore.menu;

import com.smartstore.function.MenuController;

public class CustomerMenu implements MenuController {
    @Override
    public void handleChoice(String menuNumber) {

    }

    @Override
    public void run() {

    }

    public static CustomerMenu getInstance() {
        if(instance == null){
            instance = new CustomerMenu();
        }
        return instance;
    }

    private static CustomerMenu instance;
}
