package domain.customer;

import domain.group.GroupType;

import java.util.Comparator;
import java.util.List;

public class ClassifiedCustomers {
    private static ClassifiedCustomers classifiedCustomers = null;
    private static final Customers customerRepository = Customers.getInstance();

    private ClassifiedCustomers() {
    }

    public static ClassifiedCustomers getInstance() {
        if (classifiedCustomers == null) {
            classifiedCustomers = new ClassifiedCustomers();
        }
        return classifiedCustomers;
    }

    public void viewByClassifiedGroup(GroupType groupType, Comparator<Customer> comparator) {
        List<Customer> customerList = customerRepository.findByGroup(groupType);

        if (comparator == null) {
            System.out.println(groupType);
            for (int i = 0; i < customerList.size(); i++) {
                System.out.println("No " + i + ". " + customerList.get(i));
            }
            return;
        }

        System.out.println(groupType);
        customerList.sort(comparator);
        System.out.println(customerList);

    }
}
