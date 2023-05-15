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
        if(allCustomers.size() == 0) {
            System.out.println(Message.ERR_MSG_INVALID_ARR_EMPTY);
        }else {
            while ( true ) { // 서브 메뉴 페이지를 유지하기 위한 while
                int choice = chooseMenu(new String[]{
                        "Summary",
                        "Summary (Sorted By Name)",
                        "Summary (Sorted By Time)",
                        "Summary (Sorted By Pay)",
                        "Back"});
                if (choice == 1) summary();
                else if (choice == 2) summaryName();
                else if (choice == 3) summaryTime();
                else if (choice == 4) summaryPay();
                else break;
            }
        }
    }

    private void summary() {
        int groupSize = allGroups.size();
        int customerSize = allCustomers.size();
        for (int i = 0; i < groupSize; i++) {
            Group findGroup = showGroupInfo(i);
            int count = 1;
            for (int j = 0; j < customerSize; j++) {
                if (allCustomers.get(j).getGroup() == findGroup) {
                    System.out.printf("No. %3d => %s \n", count++, allCustomers.get(j).toString());
                }
            }
            System.out.println();
        }
    }

    private void summaryName() {
        int groupSize = allGroups.size();
        while(true) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String order = nextLine(Message.END_MSG);

                OrderType orderType = findOrder(order);
                if(orderType == null) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                }else {
                    for(int i = 0; i < groupSize; i++) {
                        Group findGroup = showGroupInfo(i);

                        Customer[] customers = allCustomers.findCustomerByGroup(findGroup);

                        if (String.valueOf(orderType).equals("ASCENDING")) {
                            Arrays.sort(customers, Comparator.comparing(Customer::getCusName));
                            for (int j = 0; j < customers.length; j++) {
                                System.out.printf("No. %3d => %s \n", j + 1, customers[j].toString());
                            }
                        } else {
                            Arrays.sort(customers, (o1, o2) -> o2.getCusName().compareTo(o1.getCusName()));
                            for (int j = 0; j < customers.length; j++) {
                                System.out.printf("No. %3d => %s \n", j + 1, customers[j].toString());
                            }
                        }
                    }
                }
            }catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }catch (IndexOutOfBoundsException e) {
                System.out.println(Message.ERR_MSG_INVALID_INPUT_EMPTY);
                break;
            }
        }
    }

    private OrderType findOrder(String order) {
        OrderType[] values = OrderType.values();
        for(int i = 0; i < values.length; i++) {
            if(values[i].toString().equals(order)) {
               return values[i].replaceFullName();
            }
        }
        return null;
    }

    private void summaryTime() {
        int groupSize = allGroups.size();
        while(true) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String order = nextLine(Message.END_MSG);

                OrderType orderType = findOrder(order);
                if(orderType == null) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                }else {
                    for(int i = 0; i < groupSize; i++) {
                        Group findGroup = showGroupInfo(i);

                        Customer[] customers = allCustomers.findCustomerByGroup(findGroup);

                        if (String.valueOf(orderType).equals("ASCENDING")) {
                            Arrays.sort(customers, Comparator.comparing(Customer::getCusTotalTime));
                            for (int j = 0; j < customers.length; j++) {
                                System.out.printf("No. %3d => %s \n", j + 1, customers[j].toString());
                            }
                        } else {
                            Arrays.sort(customers, (o1, o2) -> o2.getCusTotalTime().compareTo(o1.getCusTotalTime()));
                            for (int j = 0; j < customers.length; j++) {
                                System.out.printf("No. %3d => %s \n", j + 1, customers[j].toString());
                            }
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
        int groupSize = allGroups.size();
        while(true) {
            try {
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String order = nextLine(Message.END_MSG);

                OrderType orderType = findOrder(order);

                if(orderType == null) {
                    System.out.println(Message.ERR_MSG_INVALID_INPUT_RANGE);
                }else {
                    for(int i = 0; i < groupSize; i++) {
                        Group findGroup = showGroupInfo(i);

                        Customer[] customers = allCustomers.findCustomerByGroup(findGroup);

                        if(String.valueOf(orderType).equals("ASCENDING")) {
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
                }
            }catch (InputEndException e) {
                System.out.println(Message.ERR_MSG_INPUT_END);
                break;
            }
        }
    }

    private Group showGroupInfo(int i) {
        System.out.print("==============================");
        Group findGroup = allGroups.get(i);
        System.out.printf("Group : %s ( Time : %d, Pay : %d )", findGroup.getGroupType(),
                findGroup.getParameter().getMinTime(),
                findGroup.getParameter().getMinPay());
        System.out.println("==============================");
        return findGroup;
    }
}
