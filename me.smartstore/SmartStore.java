import controller.MenuController;
import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import domain.group.Parameter;
import controller.menu.MainMenu;
import util.Console;

import java.util.Objects;

public class SmartStore {

    private static SmartStore smartStore;
    private final Customers allCustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();

    public static SmartStore getInstance() {
        if (Objects.isNull(smartStore)){
            smartStore = new SmartStore();
        }
        return smartStore;
    }

    private SmartStore() {

    }

    public void details() {
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.05.04");
        System.out.println("===========================================\n");
    }

    SmartStore test() {
        allGroups.add(new Group(new Parameter(0, 0), GroupType.NONE));
        allGroups.add(new Group(new Parameter(10, 100000), GroupType.GENERAL));
        allGroups.add(new Group(new Parameter(30, 200000), GroupType.VIP));
        allGroups.add(new Group(new Parameter(50, 300000), GroupType.VVIP));

        for (int i = 0; i < 26; i++) {
            allCustomers.add(new Customer(
                    Character.toString(
                            (char) ('a' + i)),
                    (char) ('a' + i) + "123",
                    ((int) (Math.random() * 5) + 1) * 10,
                    ((int) (Math.random() * 5) + 1) * 100000));
        }
        return this;
    }

    void run() {
        details();

        MenuController mainMenuController = new MenuController();
        mainMenuController.setMenu(MainMenu.getInstance()).start();

        System.out.println("Program Finished.");
        Console.close();
    }
}
