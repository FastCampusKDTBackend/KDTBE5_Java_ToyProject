package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;

import java.util.Arrays;
import java.util.Comparator;

public class SummaryMenu implements Menu {
    // singleton
    private static SummaryMenu summaryMenu;

    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    public static SummaryMenu getInstance() {
        if (summaryMenu == null) {
            summaryMenu = new SummaryMenu();
        }
        return summaryMenu;
    }

    private SummaryMenu() {}

    @Override
    public void manage() {
        while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Pay)",
                    "Back"});
            if(choice == 1) summary();
            else if(choice == 2) summaryName();
            else if(choice == 3) summaryTime();
            else if(choice == 4) summaryPay();
            else break;
        }
    }

    private void summary() {
        for(int i = 0; i < allGroups.size(); i++) {
            System.out.print("==============================");
            Group findGroup = allGroups.get(i);
            System.out.printf("Group : %s ( Time : %d, Pay : %d )", findGroup.getGroupType(),
                    findGroup.getParameter().getMinTime(),
                    findGroup.getParameter().getMinPay());
            System.out.println("==============================");
            int count = 1;
            for(int j = 0; j < allCustomers.size(); j++) {
                if(allCustomers.get(j).getGroup() == findGroup) {
                    System.out.printf("No. %3d => %s \n", count++, allCustomers.get(j).toString());
                }
            }
            System.out.println();
        }
    }

    private void summaryName() {
        while(true) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String order = nextLine(Message.END_MSG);
                for(int i = 0; i < allGroups.size(); i++) {
                    System.out.print("==============================");
                    Group findGroup = allGroups.get(i);
                    System.out.printf("Group : %s ( Time : %d, Pay : %d )", findGroup.getGroupType(),
                            findGroup.getParameter().getMinTime(),
                            findGroup.getParameter().getMinPay());
                    System.out.println("==============================");
                    int size = 0;
                    for(int j = 0; j < allCustomers.size(); j++) {
                        if(allCustomers.get(j).getGroup() == findGroup) {
                            size++;
                        }
                    }
                    Customer[] customers = new Customer[size];
                    int index = 0;
                    for(int j = 0; j < allCustomers.size(); j++) {
                        if(allCustomers.get(j).getGroup() == findGroup) {
                            customers[index++] = allCustomers.get(j);
                        }
                    }

                    if(order.equals("A") || order.equals("ASCENDING")) {
                        Arrays.sort(customers, Comparator.comparing(Customer::getCusName));
                        for(int j = 0; j < customers.length; j++) {
                            System.out.printf("No. %3d => %s \n", j + 1, customers[j].toString());
                        }
                    } else {
                        Arrays.sort(customers, (o1, o2) -> o2.getCusName().compareTo(o1.getCusName()));
                        for(int j = 0; j < customers.length; j++) {
                            System.out.printf("No. %3d => %s \n", j + 1, customers[j].toString());
                        }
                    }
                }
            }catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }

    private void summaryTime() {
        while(true) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String order = nextLine(Message.END_MSG);
                for(int i = 0; i < allGroups.size(); i++) {
                    System.out.print("==============================");
                    Group findGroup = allGroups.get(i);
                    System.out.printf("Group : %s ( Time : %d, Pay : %d )", findGroup.getGroupType(),
                            findGroup.getParameter().getMinTime(),
                            findGroup.getParameter().getMinPay());
                    System.out.println("==============================");
                    int size = 0;
                    for(int j = 0; j < allCustomers.size(); j++) {
                        if(allCustomers.get(j).getGroup() == findGroup) {
                            size++;
                        }
                    }
                    Customer[] customers = new Customer[size];
                    int index = 0;
                    for(int j = 0; j < allCustomers.size(); j++) {
                        if(allCustomers.get(j).getGroup() == findGroup) {
                            customers[index++] = allCustomers.get(j);
                        }
                    }

                    if(order.equals("A") || order.equals("ASCENDING")) {
                        Arrays.sort(customers, Comparator.comparing(Customer::getCusTotalTime));
                        for(int j = 0; j < customers.length; j++) {
                            System.out.printf("No. %3d => %s \n", j + 1, customers[j].toString());
                        }
                    } else {
                        Arrays.sort(customers, (o1, o2) -> o2.getCusTotalTime().compareTo(o1.getCusTotalTime()));
                        for(int j = 0; j < customers.length; j++) {
                            System.out.printf("No. %3d => %s \n", j + 1, customers[j].toString());
                        }
                    }
                }
            }catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }

    private void summaryPay() {
        while(true) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String order = nextLine(Message.END_MSG);
                for(int i = 0; i < allGroups.size(); i++) {
                    System.out.print("==============================");
                    Group findGroup = allGroups.get(i);
                    System.out.printf("Group : %s ( Time : %d, Pay : %d )", findGroup.getGroupType(),
                            findGroup.getParameter().getMinTime(),
                            findGroup.getParameter().getMinPay());
                    System.out.println("==============================");
                    int size = 0;
                    for(int j = 0; j < allCustomers.size(); j++) {
                        if(allCustomers.get(j).getGroup() == findGroup) {
                            size++;
                        }
                    }
                    Customer[] customers = new Customer[size];
                    int index = 0;
                    for(int j = 0; j < allCustomers.size(); j++) {
                        if(allCustomers.get(j).getGroup() == findGroup) {
                            customers[index++] = allCustomers.get(j);
                        }
                    }

                    if(order.equals("A") || order.equals("ASCENDING")) {
                        Arrays.sort(customers, Comparator.comparing(Customer::getCusTotalPay));
                        for(int j = 0; j < customers.length; j++) {
                            System.out.printf("No. %3d => %s \n", j + 1, customers[j].toString());
                        }
                    } else {
                        Arrays.sort(customers, (o1, o2) -> o2.getCusTotalPay().compareTo(o1.getCusTotalPay()));
                        for(int j = 0; j < customers.length; j++) {
                            System.out.printf("No. %3d => %s \n", j + 1, customers[j].toString());
                        }
                    }
                }
            }catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }
}
