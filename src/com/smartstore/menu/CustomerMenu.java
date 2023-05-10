package com.smartstore.menu;

import com.smartstore.function.MenuHandler;
import com.smartstore.function.customer.CustomerMenuHandler;

public class CustomerMenu {
    public static CustomerMenu getInstance() {
        if(instance == null){
            instance = new CustomerMenu();
        }
        return instance;
    }

    private static CustomerMenu instance;
}
