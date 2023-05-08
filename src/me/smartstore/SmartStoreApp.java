package me.smartstore;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.menu.MainMenu;

public class SmartStoreApp {
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
    private static SmartStoreApp smartStoreApp;

    private SmartStoreApp() {}

    public static SmartStoreApp getInstance() {
        if (smartStoreApp == null) {
            smartStoreApp = new SmartStoreApp();
        }

        return smartStoreApp;
    }

    public void details() {
        System.out.println("==========================================");
        System.out.println("Title : SmartStore Project");
        System.out.println("Release Date : 23.04.28");
        System.out.println("==========================================");
    }
    public SmartStoreApp test() {
        allGroups.add(new Group(GroupType.G, 10, 100000));
        allGroups.add(new Group(GroupType.V, 20, 200000));
        allGroups.add(new Group(GroupType.VV, 30, 300000));

        System.out.println("================== TEST GROUPS ==================");
        System.out.println(allGroups);

        for (int i = 0; i < 20; i++) {
            allCustomers.add(new Customer(Character.toString((char) ('a' + i)),
                    (char) ('a' + i) + "id",
                    ((int) (Math.random() * 5) + 1) * 10,
                    ((int) (Math.random() * 5) + 1) * 100000));
        }

        allCustomers.refresh();

        System.out.println("================== TEST CUSTOMERS ==================");
        System.out.println(allCustomers);
        return this;
    }

    public void run() {
        details();
        MainMenu.getInstance().show();
    }
}
