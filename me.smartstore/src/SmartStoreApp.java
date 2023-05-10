import Service.CustomerService;
import Service.SummaryService;
import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;
import domain.group.GroupType;
import domain.group.Groups;
import domain.group.Parameter;
import domain.menu.MainMenu;

public class SmartStoreApp {

    private final Groups groups = Groups.getInstance();
    private final Customers customers = Customers.getInstance();
    private final MainMenu mainMenu = MainMenu.getInstance();
    private final CustomerService customerService = CustomerService.getInstance();
    private final SummaryService summaryService = SummaryService.getInstance();


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
        System.out.println(" Release Date : 23.05.10");
        System.out.println(" Revision by matrixpower1004@gmail.com");
        System.out.println("===========================================\n");
    }

    public SmartStoreApp test() {
        groups.add(new Group(new Parameter(10, 100000), GroupType.GENERAL));
        groups.add(new Group(new Parameter(20, 200000), GroupType.VIP));
        groups.add(new Group(new Parameter(30, 300000), GroupType.VVIP));

        for (int i = 0; i < 26; i++) {
            customers.add(new Customer(
                    Character.toString(
                            (char) ('a' + i)),
                    (char) ('a' + i) + "123",
                    ((int) (Math.random() * 5) + 1) * 10,
                    ((int) (Math.random() * 5) + 1) * 100000));
        }

        customerService.refresh(groups);

        System.out.println("allCustomers = " + customers);
        System.out.println("allGroups = " + groups);

        return this; // smartStoreApp
    }

    public void run() {
        details();
        mainMenu.manage();
    }
}
