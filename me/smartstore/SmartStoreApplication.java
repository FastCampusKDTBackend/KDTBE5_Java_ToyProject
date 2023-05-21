package me.smartstore;

import me.smartstore.customers.Customer;
import me.smartstore.customers.Customers;
import me.smartstore.groups.Group;
import me.smartstore.groups.GroupType;
import me.smartstore.groups.Groups;
import me.smartstore.groups.Parameter;
import me.smartstore.menu.CustomerMenu;
import me.smartstore.menu.GroupMenu;
import me.smartstore.menu.Menu;
import me.smartstore.menu.SummarizedMenu;

public class SmartStoreApplication {
    private static SmartStoreApplication smartStoreApp;

    public static SmartStoreApplication getInstance() {
        if (smartStoreApp == null)
            smartStoreApp = new SmartStoreApplication();
        return smartStoreApp;
    }

    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();
    private final Menu menu = Menu.getInstance();
    private final CustomerMenu customerMenu = CustomerMenu.getInstance();
    private final GroupMenu groupMenu = GroupMenu.getInstance();
    private final SummarizedMenu classifiedMenu = SummarizedMenu.getInstance();

    public SmartStoreApplication test() {
        allGroups.add(new Group(GroupType.GENERAL, new Parameter(Integer.valueOf(10), Integer.valueOf(100000))));
        allGroups.add(new Group(GroupType.VIP, new Parameter(Integer.valueOf(20), Integer.valueOf(200000))));
        allGroups.add(new Group(GroupType.VVIP, new Parameter(Integer.valueOf(30), Integer.valueOf(300000))));

        for (int i = 0; i < 20; i++) {
            allCustomers.add(new Customer(
                    Character.toString((char) (97 + i)),
                    "" + (char) (97 + i) + "123",
                    ((int) (Math.random() * 5.0D) + 1) * 10,
                    ((int) (Math.random() * 5.0D) + 1) * 100000));
        }
        allCustomers.refresh(allGroups);

        return this;
    }

    private void details() {
        System.out.println("\n\n===========================================");
        System.out.println(" 스마트스토어_구조화");
        System.out.println(" @MebukiYamashi/Fastcampus_1st_ToyProject");
        System.out.println("===========================================\n");
    }

    public void run() {
        details();

        while (true) {
            int choice = menu.displayMenus(new String[]{"매개변수 설정", "고객 데이터 관리", "데이터 분류/요약", "종료"});

            if (choice == 1) {
                groupMenu.manageParameterMenu();
                continue;
            }

            if (choice == 2) {
                customerMenu.manageCustomerMenu();
                continue;
            }

            if (choice == 3) {
                classifiedMenu.manageSummaryMenu();
                continue;
            }

            if (choice == 4) {
                System.out.println("\n프로그램을 종료합니다.");
                return;
            }
        }
    }
}
