package smartstore;

import smartstore.customer.Customer;
import smartstore.customer.Customers;
import smartstore.group.Group;
import smartstore.group.GroupType;
import smartstore.group.Groups;
import smartstore.group.Parameter;
import smartstore.menu.MainMenu;

public class SmartStoreApp {
    private final MainMenu mainMenu = MainMenu.getInstance();

    private final Groups groups = Groups.getInstance();
    private final Customers customers = Customers.getInstance();

    // singleton
    private static SmartStoreApp smartStoreApp;

    public static SmartStoreApp getInstance() {
        if (smartStoreApp == null) {
            smartStoreApp = new SmartStoreApp();
        }
        return smartStoreApp;
    }

    private SmartStoreApp() {}

    public void details() {
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.05.01");
        System.out.println(" Copyright 2023 YongHyun All rights reserved.");
        System.out.println("===========================================\n");
    }

    public SmartStoreApp test() {
        groups.getGroupSArray().add(new Group(new Parameter(10, 100000), GroupType.GENERAL));
        groups.getGroupSArray().add(new Group(new Parameter(20, 200000), GroupType.VIP));
        groups.getGroupSArray().add(new Group(new Parameter(30, 300000), GroupType.VVIP));

        for (int i = 0; i < 26; i++) {
//            allCustomers.add(new Customer(
//                    Character.toString(
//                            (char) ('a' + i)),
//                    (char) ('a' + i) + "123",
//                    ((int) (Math.random() * 5) + 1) * 10,
//                    ((int) (Math.random() * 5) + 1) * 100000));

            customers.getCustomerSArray().add(new Customer.Builder(Character.toString((char)('a' + i)), (char) ('a' + i) + "123")
                    .totalTime(((int)(Math.random() * 5) + 1) * 10)
                    .totalPay( ((int) (Math.random() * 5) + 1) * 100000)
                    .build());

        }

        System.out.println("customers = " + customers);
        System.out.println("groups = " + groups);

        // @TODO: refresh do not implemented yet.
//        allCustomers.refresh(allGroups);

        return this; // smartStoreApp
    }

    public void run() {
        details();
        mainMenu.manage();

    }
}
