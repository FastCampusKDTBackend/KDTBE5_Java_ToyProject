package me.smartstore;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.menu.MainMenu;

public class SmartStoreApp {
    private static SmartStoreApp smartStoreApp;
    private final MainMenu mainMenu = MainMenu.getInstance();
    private static final Groups groups = Groups.getInstance();
    private static final Customers customers = Customers.getInstance();

    private SmartStoreApp() {
    }

    public static SmartStoreApp getInstance() {
        if (smartStoreApp == null) {
            smartStoreApp = new SmartStoreApp();
        }
        return smartStoreApp;
    }

    public void details() {
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.04.27");
        System.out.println(" Copyright 2023 Chaewon Eom All rights reserved.");
        System.out.println("===========================================\n");
    }

    public SmartStoreApp test() {
        groups.findByGroupType(GroupType.GENERAL).setParameter(new Parameter(10, 100000));
        groups.findByGroupType(GroupType.VIP).setParameter(new Parameter(20, 200000));
        groups.findByGroupType(GroupType.VVIP).setParameter(new Parameter(30, 300000));

        for (int i = 0; i < 26; i++) {
            customers.add(new Customer(
                Character.toString((char)('a' + i)),
                (char)('a' + i) + "123",
                ((int)(Math.random() * 5) + 1) * 10,
                ((int)(Math.random() * 5) + 1) * 100000));
        }

        customers.refreshGroupType(groups);

        System.out.println("Customers \n" + customers);
        System.out.println("Groups \n" + groups);

        return this; // smartStoreApp
    }

    public void run() {
        details();
        mainMenu.root();
    }

}
