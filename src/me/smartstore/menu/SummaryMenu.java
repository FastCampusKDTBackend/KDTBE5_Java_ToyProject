package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.exception.InputEndException;
import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;

import java.util.*;

import static me.smartstore.util.Message.ERR_MSG_INPUT_END;
import static me.smartstore.util.Message.ERR_MSG_INVALID_INPUT_RANGE;

public class SummaryMenu implements Menu{
    private final Customers allCustomers = Customers.getInstance();
    private final Groups allGroups = Groups.getInstance();



    // singleton
    private static SummaryMenu summaryMenu;

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
            if (choice == 1) sortByGroup(allCustomers.getCustomers());
            else if (choice == 2) sortByName(allCustomers.getCustomers());
            else if (choice == 3) sortByTime(allCustomers.getCustomers());
            else if (choice == 4) sortByPay(allCustomers.getCustomers());
            else break;
        }
    }
    public void printGroupInfo(Group group){
        System.out.println("\n==============================");
        System.out.printf("Group: %s ( Time : %d, Pay : %d )", group.getGroupType(), group.getParameter().getMinTime(), group.getParameter().getMinPay());
        System.out.println("\n==============================");
    }

    public void printCustomerInfo(Customer customer){
        System.out.printf("Customer{userId='%s', name='%s', spentTime=%d, totalPay=%d, "
                ,customer.getCustomerId(),customer.getCustomerName(),customer.getCustomerTotalTime(),customer.getCustomerTotalPay());
        if (customer.getGroup().getGroupType() == GroupType.NONE){
            System.out.println("group=null}");
        }
        else{
            System.out.printf("group=GroupType: %s\nParameter: Parameter{minimumSpentTime=%d, minimumTotalPay=%d}}\n"
                    , customer.getGroup().getGroupType(), customer.getGroup().getParameter().getMinTime(), customer.getGroup().getParameter().getMinPay());
        }
    }


    public int forSortbyGroup(int i, int count, Customer[] customers){
        printGroupInfo(allGroups.get(i));
        for (int j = 0; j < customers.length; j++) {
            if (customers[j].getGroup().getGroupType() == allGroups.get(i).getGroupType()){
                System.out.printf("No. %d => ", count);
                printCustomerInfo(customers[j]);
                count++;
            }
        }
        System.out.println("===============================\n");

        return count;
    }



    public void sortByGroup(Customer[] customers){
        GroupType[] groupTypes = new GroupType[]{GroupType.NONE, GroupType.GENERAL, GroupType.VIP, GroupType.VVIP};
        for (int j = 0; j < groupTypes.length; j++) {
            for (int i = 0; i < allGroups.size(); i++) {
                int count = 1;
                if (allGroups.get(i).getGroupType() == groupTypes[j]) {
                    count = forSortbyGroup(i, count, customers);
                }
            }

        }
    }

    public void sortByName(Customer[] customers){
        while (true){
            try {
                String standard = selectOrderStandard();
                Customer[] result = new Customer[]{};
                if(standard.equals("A")){ // 오름차순
                    result = Arrays.stream(customers).sorted(Comparator.comparing(Customer::getCustomerName)).toArray(Customer[]::new);
                } else if (standard.equals("D")) { // 내림차순
                    result = Arrays.stream(customers).sorted(Comparator.comparing(Customer::getCustomerName).reversed()).toArray(Customer[]::new);
                }
                sortByGroup(result);
            } catch (InputEndException e){
                System.out.println(ERR_MSG_INPUT_END);
                break;
            }

        }
    }

    public void sortByTime(Customer[] customers){
        while (true){
            try {
                String standard = selectOrderStandard();
                Customer[] result = new Customer[]{};
                if(standard.equals("A")){ // 오름차순
                    result = Arrays.stream(customers).sorted(Comparator.comparing(Customer::getCustomerTotalTime)).toArray(Customer[]::new);
                } else if (standard.equals("D")) { // 내림차순
                    result = Arrays.stream(customers).sorted(Comparator.comparing(Customer::getCustomerTotalTime).reversed()).toArray(Customer[]::new);
                }
                sortByGroup(result);
            } catch (InputEndException e){
                System.out.println(ERR_MSG_INPUT_END);
                break;
            }

        }

    }

    public void sortByPay(Customer[] customers){
        while (true){
            try {
                String standard = selectOrderStandard();
                Customer[] result = new Customer[]{};
                if(standard.equals("A")){ // 오름차순
                    result = Arrays.stream(customers).sorted(Comparator.comparing(Customer::getCustomerTotalPay)).toArray(Customer[]::new);
                } else if (standard.equals("D")) { // 내림차순
                    result = Arrays.stream(customers).sorted(Comparator.comparing(Customer::getCustomerTotalPay).reversed()).toArray(Customer[]::new);
                }
                sortByGroup(result);
            } catch (InputEndException e){
                System.out.println(ERR_MSG_INPUT_END);
                break;
            }

        }

    }

    public String selectOrderStandard() {
        String standard;
        while(true){
            try {
                System.out.println("\nWhich order (ASCENDING (A), DESCENDING (D))?");
                standard = nextLine(Message.END_MSG);
                if (standard.length() > 1) throw new InputMismatchException();
                else if (standard.equals("A") || standard.equals("D"))
                    return standard;
            } catch (InputMismatchException e) {
                System.out.println(ERR_MSG_INVALID_INPUT_RANGE);
            }
        }
    }


}
