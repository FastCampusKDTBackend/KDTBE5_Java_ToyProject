package me.smartstore.menu;

import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;
import me.smartstore.group.Group;
import me.smartstore.group.Groups;
import me.smartstore.util.Message;

import java.util.Arrays;
import java.util.Comparator;

public class SummaryMenu implements Menu {

    //singleton
    private static SummaryMenu summaryMenu;

    private final Groups allGroups = Groups.getInstance();
    private final Customers allCustomers = Customers.getInstance();

    public static SummaryMenu getInstance(){
        if(summaryMenu == null)
            summaryMenu = new SummaryMenu();
        return summaryMenu;
    }

    private SummaryMenu(){}

    @Override
    public void manage() {
        while ( true ){
            int choice = chooseMenu(new String[]{
                    "Summary",
                    "Summary (Sorted By Name)",
                    "Summary (Sorted By Time)",
                    "Summary (Sorted By Pay)",
                    "Back"});

            if(choice == 1)
                summary();
            else if(choice == 2)
                summaryByName();
            else if(choice == 3)
                summaryByTime();
            else if(choice == 4)
                summaryByPay();
            else break; // choice == 5
        }
    }

    public void summary(){
        Customer[] customers = toArray();
        for(int i = 0; i < allGroups.size(); i++){
            Group group = allGroups.get(i);
            System.out.println("==============================");
            System.out.println("Group : " + group.getGroupType() +
                    " ( Time : " + group.getParameter().getMinTime() + ", Pay : "
                    + group.getParameter().getMinPay() + " )");
            System.out.println("==============================");

            for(int j = 0; j < customers.length; j++){
                if(allGroups.get(i).getGroupType().equals(customers[j].getGroup().getGroupType())){
                    System.out.println("No. " + (j + 1) + " => " + customers[j]);
                }
            }
        }
    }

    public void summaryByName(){
        Customer[] customers = toArray();
        System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
        String order = nextLine(Message.END_MSG);

        if(order.equals("A"))
            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.getCusName().compareTo(o2.getCusName());
                }
            });
        if(order.equals("D"))
            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.getCusName().compareTo(o2.getCusName()) * -1;
                }
            });

        for(int i = 0; i < allGroups.size(); i++){
            Group group = allGroups.get(i);
            System.out.println("==============================");
            System.out.println("Group : " + group.getGroupType() +
                    " ( Time : " + group.getParameter().getMinTime() + ", Pay : "
                    + group.getParameter().getMinPay() + " )");
            System.out.println("==============================");

            for(int j = 0; j < customers.length; j++){
                if(allGroups.get(i).getGroupType().equals(customers[j].getGroup().getGroupType())){
                    System.out.println("No. " + (j + 1) + " => " + customers[j]);
                }
            }
        }
    }

    private void summaryByTime() {
        Customer[] customers = toArray();
        System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
        String order = nextLine(Message.END_MSG);

        if(order.equals("A"))
            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.getCusTotalTime().compareTo(o2.getCusTotalTime());
                }
            });
        if(order.equals("D"))
            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.getCusTotalTime().compareTo(o2.getCusTotalTime()) * -1;
                }
            });

        for(int i = 0; i < allGroups.size(); i++){
            Group group = allGroups.get(i);
            System.out.println("==============================");
            System.out.println("Group : " + group.getGroupType() +
                    " ( Time : " + group.getParameter().getMinTime() + ", Pay : "
                    + group.getParameter().getMinPay() + " )");
            System.out.println("==============================");

            for(int j = 0; j < customers.length; j++){
                if(allGroups.get(i).getGroupType().equals(customers[j].getGroup().getGroupType())){
                    System.out.println("No. " + (j + 1) + " => " + customers[j]);
                }
            }
        }
    }

    public void summaryByPay(){
        Customer[] customers = toArray();
        System.out.println("Which order (ASCENDING (A), DESCENDING (D))?");
        String order = nextLine(Message.END_MSG);

        if(order.equals("A"))
            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.getCusTotalPay().compareTo(o2.getCusTotalPay());
                }
            });
        if(order.equals("D"))
            Arrays.sort(customers, new Comparator<Customer>() {
                @Override
                public int compare(Customer o1, Customer o2) {
                    return o1.getCusTotalPay().compareTo(o2.getCusTotalPay()) * -1;
                }
            });

        for(int i = 0; i < allGroups.size(); i++){
            Group group = allGroups.get(i);
            System.out.println("==============================");
            System.out.println("Group : " + group.getGroupType() +
                    " ( Time : " + group.getParameter().getMinTime() + ", Pay : "
                    + group.getParameter().getMinPay() + " )");
            System.out.println("==============================");

            for(int j = 0; j < customers.length; j++){
                if(allGroups.get(i).getGroupType().equals(customers[j].getGroup().getGroupType())){
                    System.out.println("No. " + (j + 1) + " => " + customers[j]);
                }
            }
        }
    }
    public Customer[] toArray(){
        Customer[] customers = new Customer[allCustomers.size()];
        for(int i = 0; i < allCustomers.size(); i++){
            customers[i] = allCustomers.get(i);
        }

        return customers;
    }

}
