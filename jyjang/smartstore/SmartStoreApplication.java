package jyjang.smartstore;

import jyjang.smartstore.customer.Customers;
import jyjang.smartstore.group.Groups;
import jyjang.smartstore.menu.*;

public class SmartStoreApplication {

    private static SmartStoreApplication smartStoreApplication;

    public static SmartStoreApplication getInstance() {
        if (smartStoreApplication == null)
            smartStoreApplication = new SmartStoreApplication();
        return smartStoreApplication;
    }



    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    private final MainMenu mainMenu = MainMenu.getInstance();

    public SmartStoreApplication test() {
//        this.allGroups.add(new Group(GroupType.GENERAL, new Parameter(Integer.valueOf(10), Integer.valueOf(100000))));
//        this.allGroups.add(new Group(GroupType.VIP, new Parameter(Integer.valueOf(20), Integer.valueOf(200000))));
//        this.allGroups.add(new Group(GroupType.VVIP, new Parameter(Integer.valueOf(30), Integer.valueOf(300000))));
//        for (int i = 0; i < 20; i++)
//            this.allCustomers.add(new Customer(
//                    Character.toString((char)(97 + i)), "" + (char)(97 + i) + "123", (
//
//                    (int)(Math.random() * 5.0D) + 1) * 10, (
//                    (int)(Math.random() * 5.0D) + 1) * 100000));
//        this.allCustomers.refresh(this.allGroups);
        return this;
    }

    private SmartStoreApplication() {

    }

    public void run() {
        mainMenu.manage();
    }
}
