package me.smartstore;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.GroupConditions;
import me.smartstore.menu.MainMenu;

public class SmartStoreApp {

    private Groups allGroups;
    private Customers allCustomers;
    private MainMenu mainMenu;
    private static SmartStoreApp smartStoreApp;

    public static SmartStoreApp getInstance() {
        if (smartStoreApp == null) {
            smartStoreApp = new SmartStoreApp();
        }
        return smartStoreApp;
    }

    private SmartStoreApp() {
        this.allCustomers = Customers.getInstance();
        this.allGroups = Groups.getInstance();
        this.mainMenu = MainMenu.getInstance();
    }

    public void details() {
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.05.10");
        System.out.println(" Copyright 2023 taeHyoung All rights reserved.");
        System.out.println("===========================================\n");
    }

    public SmartStoreApp test() {
        allGroups.add(new Group(new GroupConditions(10, 100000), GroupType.GENERAL));
        allGroups.add(new Group(new GroupConditions(20, 200000), GroupType.VIP));
        allGroups.add(new Group(new GroupConditions(30, 300000), GroupType.VVIP));

        for (int i = 0; i < 26; i++) {
            allCustomers.add(new Customer(
                    Character.toString((char) ('a' + i)),
                    (char) ('a' + i) + "123",
                    ((int) (Math.random() * 10) + 1) * 5,
                    ((int) (Math.random() * 5) + 1) * 100000));
        }
        allCustomers.refresh(allGroups);

        return this;
    }

    public void run() {
        details();
        mainMenu.manage();
    }
}
