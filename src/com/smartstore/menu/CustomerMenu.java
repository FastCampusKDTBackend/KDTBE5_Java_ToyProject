package com.smartstore.menu;

public class CustomerMenu implements Menu, MenuController {
    @Override
    public void handleChoice(int menuNumber) {

    }

    public static CustomerMenu getInstance() {
        if(instance == null){
            instance = new CustomerMenu();
        }
        return instance;
    }

    private static CustomerMenu instance;

    @Override
    public void run() {

    }
}