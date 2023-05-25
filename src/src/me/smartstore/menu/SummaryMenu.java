package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.EmptyArrayException;
import me.smartstore.exception.InputEndException;
import me.smartstore.exception.InputRangeException;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;

import java.util.Arrays;
import java.util.Comparator;

import static me.smartstore.util.Message.*;

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
        while ( true ) {
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

    private void summary() {
        try {
            for(int i =0; i< allGroups.size();i++){
                System.out.println("==============================");
                Group findGroup = allGroups.get(i);
                System.out.printf("Group : %s ( Time : %d, Pay : %d )%n", findGroup.getGroupType(),
                        findGroup.getParameter().getMinTime(),
                        findGroup.getParameter().getMinPay());
                System.out.println("==============================");
                int num = 1;
                for (int j = 0; j < allCustomers.size(); j++) {
                    if(findGroup == allCustomers.get(j).getGroup()){
                        System.out.printf("No. %3d => %s \n", num++, allCustomers.get(j).toString());
                    }
                }
            }
        } catch (EmptyArrayException e) {
            System.out.println(ERR_MSG_NULL_ARR_ELEMENT);
        }
    }
    private SortType sort(){
        while (true){
            try{
                System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
                String data = nextLine(END_MSG);

                if (!data.equals("A") && !data.equals("D")) {
                    throw new InputRangeException();
                }

                if (data.equals("A")){
                    return SortType.ASCENDING;
                }
                else{
                    return SortType.DESCENDING;
                }
            }
            catch (InputRangeException e){
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
            }
            catch (InputEndException e){
                System.out.println(ERR_MSG_INPUT_END);
                break;
            }
        }
        return SortType.DESCENDING;
    }

    private void summaryName(){
        while (true){
            SortType sortType = sort();
            Customer[] sortedCustomers = allCustomers.getCustomers().clone();

            if (sortType == SortType.ASCENDING) {
                Arrays.sort(sortedCustomers, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer c1, Customer c2) {
                        return c1.getCusName().compareTo(c2.getCusName());
                    }
                });
            } else if (sortType == SortType.DESCENDING) {
                Arrays.sort(sortedCustomers, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer c1, Customer c2) {
                        return c2.getCusName().compareTo(c1.getCusName());
                    }
                });
            } else {
                break;
            }
            for (int i = 0; i < allGroups.size(); i++) {
                System.out.println("==============================");
                Group findGroup = allGroups.get(i);
                System.out.printf("Group : %s ( Time : %d, Pay : %d )%n", findGroup.getGroupType(),
                        findGroup.getParameter().getMinTime(),
                        findGroup.getParameter().getMinPay());
                System.out.println("==============================");
                int num = 1;
                for (Customer customer : sortedCustomers) {
                    if (customer.getGroup().equals(findGroup)) {
                        System.out.printf("No. %3d => %s \n", num++, customer);
                    }
                }
            }
        }
    }

    private void summaryTime(){
        while (true){
            SortType sortType = sort();
            Customer[] sortedCustomers = allCustomers.getCustomers().clone();

            if (sortType == SortType.ASCENDING) {
                Arrays.sort(sortedCustomers, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer c1, Customer c2) {
                        return c1.getCusTotalTime().compareTo(c2.getCusTotalTime());
                    }
                });
            } else if (sortType == SortType.DESCENDING) {
                Arrays.sort(sortedCustomers, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer c1, Customer c2) {
                        return c2.getCusTotalTime().compareTo(c1.getCusTotalTime());
                    }
                });
            } else {
                break;
            }
            for (int i = 0; i < allGroups.size(); i++) {
                System.out.println("==============================");
                Group findGroup = allGroups.get(i);
                System.out.printf("Group : %s ( Time : %d, Pay : %d )%n", findGroup.getGroupType(),
                        findGroup.getParameter().getMinTime(),
                        findGroup.getParameter().getMinPay());
                System.out.println("==============================");
                int num = 1;
                for (Customer customer : sortedCustomers) {
                    if (customer.getGroup().equals(findGroup)) {
                        System.out.printf("No. %3d => %s \n", num++, customer);
                    }
                }
            }
        }
    }

    private void summaryPay(){
        while (true){
            SortType sortType = sort();
            Customer[] sortedCustomers = allCustomers.getCustomers().clone();

            if (sortType == SortType.ASCENDING) {
                Arrays.sort(sortedCustomers, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer c1, Customer c2) {
                        return c1.getCusTotalPay().compareTo(c2.getCusTotalPay());
                    }
                });
            } else if (sortType == SortType.DESCENDING) {
                Arrays.sort(sortedCustomers, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer c1, Customer c2) {
                        return c2.getCusTotalPay().compareTo(c1.getCusTotalPay());
                    }
                });
            } else {
                break;
            }
            for (int i = 0; i < allGroups.size(); i++) {
                System.out.println("==============================");
                Group findGroup = allGroups.get(i);
                System.out.printf("Group : %s ( Time : %d, Pay : %d )%n", findGroup.getGroupType(),
                        findGroup.getParameter().getMinTime(),
                        findGroup.getParameter().getMinPay());
                System.out.println("==============================");
                int num = 1;
                for (Customer customer : sortedCustomers) {
                    if (customer.getGroup().equals(findGroup)) {
                        System.out.printf("No. %3d => %s \n", num++, customer);
                    }
                }
            }
        }
    }
}