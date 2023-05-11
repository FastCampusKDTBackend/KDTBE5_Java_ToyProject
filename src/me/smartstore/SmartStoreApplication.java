package me.smartstore;

import me.smartstore.customer.Customers;
import me.smartstore.group.Groups;
import me.smartstore.menu.*;

public class SmartStoreApplication {
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    private final MainMenu mainMenu = MainMenu.getInstance();

    private static SmartStoreApplication smartStoreApp;

    public static SmartStoreApplication getInstance() {
        if (smartStoreApp == null) {
            smartStoreApp = new SmartStoreApplication();
        }
        return smartStoreApp;
    }

    private SmartStoreApplication() {}

    public void details() {
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.05.04");
        System.out.println(" Copyright 2023 HyeJi All rights reserved.");
        System.out.println("===========================================\n");
    }

    public void run() {
        details();
        mainMenu.manage();
    }
}