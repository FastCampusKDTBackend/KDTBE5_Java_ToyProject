import domain.customer.Customers;
import domain.group.Groups;
import menu.CustomerMenu;
import menu.GroupMenu;
import menu.MainMenu;
import menu.SummaryMenu;
import service.CustomerService;
import service.GroupService;
import service.SummaryService;
import util.Console;

import java.util.Objects;

public class SmartStore {

    private static SmartStore smartStore;

    private final MainMenu mainMenu;


    public static SmartStore getInstance() {
        if (Objects.isNull(smartStore)){
            smartStore = new SmartStore();
        }
        return smartStore;
    }

    private SmartStore() {
        // inject Dependency
        CustomerService customerService = new CustomerService(Customers.getInstance());
        GroupService groupService = new GroupService(Groups.getInstance());
        SummaryService summaryService = new SummaryService(customerService, groupService);

        CustomerMenu customerMenu = new CustomerMenu(customerService, summaryService);
        GroupMenu groupMenu = new GroupMenu(groupService, summaryService);
        SummaryMenu summaryMenu = new SummaryMenu(summaryService, groupService);

        mainMenu = new MainMenu(groupMenu, customerMenu, summaryMenu);
    }

    private void details() {
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.05.05");
        System.out.println("===========================================\n");
    }

    public void run() {
        details();
        mainMenu.service();
        System.out.println("Program Finished.");
        Console.close();
    }
}
