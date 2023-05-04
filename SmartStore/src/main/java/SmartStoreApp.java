import Customer.Customers;
import Customer.Customer;
import Group.Group;
import Group.GroupType;
import Group.Groups;
import Group.Parameter;
import Menu.MainMenu;

public class SmartStoreApp {
    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
    private final MainMenu mainMenu = MainMenu.getInstance();

    private static SmartStoreApp allSmartStoreApp;

    public static SmartStoreApp getInstance() {
        if (allSmartStoreApp == null) {
            allSmartStoreApp = new SmartStoreApp();
        }
        return allSmartStoreApp;
    }

    private SmartStoreApp() {

    }

    public void details() {
        StringBuilder details = new StringBuilder();
        details.append("\n\n===========================================\n");
        details.append(" Title : SmartStore Customer Classification\n");
        details.append(" Release Date : 23.05.02\n");
        details.append(" Copyright 2023 Dali186 All rights reserved.\n");
        details.append("===========================================\n\n");
        System.out.println(details);
    }

    public SmartStoreApp test() {

        allGroups.add(new Group(new Parameter(10, 100000), GroupType.GENERAL));
        allGroups.add(new Group(new Parameter(20, 200000), GroupType.VIP));
        allGroups.add(new Group(new Parameter(30, 300000), GroupType.VVIP));

        for (int i = 0; i < 26; i++) {
            allCustomers.add(new Customer(
                    Character.toString((char) ('a' + i)),
                    (char) ('a' + i) + "123",
                    ((int) (Math.random() * 5) + 1) * 10,
                    ((int) (Math.random() * 5) + 1) * 100000));
        }
        allCustomers.refresh();
        return this;
    }

    public void run() {
        details();
        //allGroups.init();
        mainMenu.manage();
    }
}
