import customer.Customer;
import customer.Customers;
import group.Group;
import group.GroupType;
import group.Groups;
import group.Parameter;
import menu.MainMenu;

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

    private SmartStoreApp() {
    }

    public void details() {
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.04.27");
        System.out.println(" Copyright 2023 Eunbin All rights reserved.");
        System.out.println("===========================================\n");

    }

    public SmartStoreApp test() {
        allGroups.add(new Group(new Parameter(0,0), GroupType.NONE));
        allGroups.add(new Group(new Parameter(10, 100000), GroupType.GENERAL));
        allGroups.add(new Group(new Parameter(20, 200000), GroupType.VIP));
        allGroups.add(new Group(new Parameter(30, 300000), GroupType.VVIP));

        for (int i = 0; i < 26; i++) {
            Customer customer = new Customer(
                    Character.toString(
                            (char) ('a' + i)),
                    (char) ('a' + i) + "123",
                    ((int) (Math.random() * 5) + 1) * 10,
                    ((int) (Math.random() * 5) + 1) * 100000);

            Group highestGroup = null;
            for (int j = 0; j < allGroups.size(); j++) {
                Group group = allGroups.get(j);
                Parameter parameter = group.getParameter();
                Integer minTime = parameter.getMinTime();
                Integer minPay = parameter.getMinPay();

                if (minTime <= customer.getTotalTime() && minPay <= customer.getTotalPay()) {
                    if (highestGroup == null || (parameter.getMinPay() > highestGroup.getParameter().getMinPay() && parameter.getMinTime() > highestGroup.getParameter().getMinTime() )) {
                        highestGroup = group;
                    }
                }
            }
            if (highestGroup != null) {
                customer.setGroup(highestGroup);
            }

            allCustomers.add(customer);
        }


        System.out.println("allCustomers = " + allCustomers);
        System.out.println("allGroups = " + allGroups);

        allCustomers.refresh(allGroups);

        return this;

    }

    public void run() {
        details();
//        allGroups.add(new Group(new Parameter(0,0), GroupType.NONE));
        mainMenu.manage();
    }
}
