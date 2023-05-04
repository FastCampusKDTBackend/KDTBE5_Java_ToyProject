package view;

import domain.customer.Customer;
import domain.customer.Customers;
import domain.group.Group;

import java.util.ArrayList;
import java.util.List;

public class Output {

    public static void printErrorMessage(String message){
        System.out.println(message);
    }

    public static void printGroup(Group group){
        System.out.printf("%s Group Info : [ Time = %d | Pay = %d ]\n",
                group.getGroupType(), group.getParameter().getMinTime(), group.getParameter().getMinPay());
    }

    public static void customerList(Customers customers) {
        System.out.println("\n======= Customer Info. =======");

        if (customers.size() == 0) {
            System.out.println("NONE");
            return;
        }

        for (int i = 0; i < customers.size(); i++) {
            System.out.print("NO. " + (i+1) + " > ");
            System.out.println(customers.get(i));
        }
    }

    public static void customerClassifiedList(ArrayList<Customer> customers, Group group){
        System.out.println("\n==============================");
        System.out.printf("Group : %s ( Time : %d, Pay : %d )\n", group.getGroupType(), group.getParameter().getMinTime(), group.getParameter().getMinPay());
        System.out.println("==============================");

        if (customers.isEmpty()) {
            System.out.println("NONE");
            return;
        }

        int cnt = 1;
        for (Customer customer : customers) {
            System.out.printf("No. %-4d > ", cnt++);
            System.out.println(customer);
        }
    }

    public static void customerClassifiedList(ArrayList<ArrayList<Customer>> allCustomers, List<Group> list) {
        for (int i = 0; i < allCustomers.size(); i++) {
            customerClassifiedList(allCustomers.get(i), list.get(i));
        }
    }
}
