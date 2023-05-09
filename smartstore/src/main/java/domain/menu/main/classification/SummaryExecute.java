package domain.menu.main.classification;

import domain.customer.ClassifiedCustomers;
import domain.customer.Customer;
import domain.group.GroupType;

import java.util.Comparator;

public interface SummaryExecute {
    static Runnable getMethod(Comparator<Customer> comparator) {
        return () -> {
            ClassifiedCustomers classifiedCustomers = ClassifiedCustomers.getInstance();

            classifiedCustomers.viewByClassifiedGroup(GroupType.NONE, comparator);
            classifiedCustomers.viewByClassifiedGroup(GroupType.GENERAL, comparator);
            classifiedCustomers.viewByClassifiedGroup(GroupType.VIP, comparator);
            classifiedCustomers.viewByClassifiedGroup(GroupType.VVIP, comparator);
        };
    }
}
