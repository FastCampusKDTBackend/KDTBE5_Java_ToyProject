import controller.MenuController;
import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import controller.menu.MainMenu;
import util.Console;

import java.util.Objects;

public class SmartStore {

    private static SmartStore smartStore;

    public static SmartStore getInstance() {
        if (Objects.isNull(smartStore)){
            smartStore = new SmartStore();
        }
        return smartStore;
    }

    private SmartStore() {

    }

    private void details() {
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.05.05");
        System.out.println("===========================================\n");
    }

    public void run() {
        details();
        MenuController mainMenuController = new MenuController();
        mainMenuController.setMenu(MainMenu.getInstance()).start();
        System.out.println("Program Finished.");
        Console.close();
    }
}
