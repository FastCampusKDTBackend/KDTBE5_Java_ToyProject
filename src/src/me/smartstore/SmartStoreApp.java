package me.smartstore;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
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
    public SmartStoreApp test(){
        allGroups.add(new Group(new Parameter(10,100000), GroupType.GENERAL));
        allGroups.add(new Group(new Parameter(20,200000), GroupType.VIP));
        allGroups.add(new Group(new Parameter(30,300000), GroupType.VVIP));
        for (int i = 0; i < 26; i++) {
            allCustomers.add(new Customer(
                    Character.toString(
                            (char) ('a' + i)),
                    (char) ('a' + i) + "123",
                    ((int) (Math.random() * 5) + 1) * 10,
                    ((int) (Math.random() * 5) + 1) * 100000));
        }

        System.out.println("allCustomers = " + allCustomers);
        System.out.println("allGroups = " + allGroups);
//        allCustomers.refresh(allGroups);

        return this;
    }

    public void run(){
        details();
        mainMenu.manage();
    }
}
