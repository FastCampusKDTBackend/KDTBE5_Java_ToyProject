package com.smartstore.menu.customer;

import com.smartstore.menu.Menu;

public class CustomerMenu implements Menu {
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
    public void run(int menuNumber) {

    }
}