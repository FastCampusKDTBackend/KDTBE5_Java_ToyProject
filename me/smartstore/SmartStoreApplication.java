package me.smartstore;

import me.smartstore.domain.customer.Customer;
import me.smartstore.domain.customer.Customers;
import me.smartstore.domain.group.Group;
import me.smartstore.domain.group.GroupType;
import me.smartstore.domain.group.Groups;
import me.smartstore.domain.group.Parameter;
import me.smartstore.domain.menu.MainMenu;

public class SmartStoreApplication {
    private static SmartStoreApplication smartStoreApplication;
    private final MainMenu mainMenu;
    private final Customers allCustomers;
    private final Groups allGroups;

    public static SmartStoreApplication getInstance() {
        if (smartStoreApplication == null) {
            smartStoreApplication = new SmartStoreApplication();
        }
        return smartStoreApplication;
    }
    private SmartStoreApplication() {
        this.mainMenu = MainMenu.getInstance();
        this.allCustomers = Customers.getInstance();
        this.allGroups = Groups.getInstance();
    }

    public void details() {
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.04.27");
        System.out.println(" Copyright 2023 HoonSubKim All rights reserved.");
        System.out.println("===========================================\n");
    }

    public void run() {
        details();
        mainMenu.manage();
    }

}
