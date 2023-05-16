import customers.Customer;
import customers.Customers;
import groups.*;
import menu.*;

public class SmartStoreApplication {

    private static SmartStoreApplication smartStoreApp;

    public static SmartStoreApplication getInstance() {
        if (smartStoreApp == null) {
            smartStoreApp = new SmartStoreApplication();
        }
        return smartStoreApp;
    }

    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
    private final MainMenu mainMenu = MainMenu.getInstance();


    public SmartStoreApplication test() {
        allGroups.add(new Group( GroupType.GENERAL, new Parameter(10, 100000)) );
        allGroups.add(new Group( GroupType.VIP, new Parameter(20, 200000)) );
        allGroups.add(new Group( GroupType.VVIP, new Parameter(30, 300000)) );

        for (int i = 0; i < 20; ++i) {
            allCustomers.add(new Customer(
                    Character.toString(
                            (char) ('a' + i)),
                    (char) ('a' + i) + "123",
                    ((int) (Math.random() * 5) + 1) * 10,
                    ((int) (Math.random() * 5) + 1) * 100000));
        }

        allCustomers.refresh(allGroups);
        return this;
    }

    private void details() {
        System.out.println("\n===========================================");
        System.out.println(" Title : SmartStore Customer Segmentation");
        System.out.println(" Refer to EunBinChoi's GitHub.");
        System.out.println("===========================================\n");
    }

    public void run() {
        details();

        mainMenu.manage();
    }
}
