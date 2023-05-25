package me.smartstore;

import me.smartstore.customer.Customers;
import me.smartstore.group.Groups;
import me.smartstore.menu.MainMenu;

public class SmartStoreApp {
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
    private static SmartStoreApp smartStoreApp;
    private final MainMenu mainMenu = MainMenu.getInstance();

    public static SmartStoreApp getInstance() {
        if (smartStoreApp == null) {
            smartStoreApp = new SmartStoreApp();
        }
        return smartStoreApp;
    }
    private SmartStoreApp() {}

    public void details(){
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.04.27");
        System.out.println(" Copyright 2023 Eunbin All rights reserved.");
        System.out.println("===========================================\n");
    }

    public void run(){
        details();
        mainMenu.manage();
    }
}
