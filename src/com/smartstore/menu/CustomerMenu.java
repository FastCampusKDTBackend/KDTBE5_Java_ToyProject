package com.smartstore.menu;

import com.smartstore.function.Function;
import com.smartstore.function.mainmenu.MainMenuHandler;
import com.smartstore.function.customer.CustomerFunction;

public class CustomerMenu implements MainMenuHandler {
    public static CustomerMenu getInstance() {
        if(instance == null){
            instance = new CustomerMenu();
        }
        return instance;
    }

    private static CustomerMenu instance;

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == CustomerFunction.BACK.getMenuNumber()) {
            return true;
        }
        Function.of(Integer.parseInt(menuNumber), CustomerFunction.class).run();
        return false;
    }
}
