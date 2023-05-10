package me.smartstore.core;

import me.smartstore.core.domain.Customer;
import me.smartstore.enums.CustomerType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static me.smartstore.enums.CustomerType.*;
import static me.smartstore.enums.CustomerType.VVIP;

class CustomerManagerTest {
  private final Customer[] testCustomers =
      new Customer[] {
        new Customer("c1name", "c1id", 60, 6000000, CustomerType.GENERAL),
        new Customer("c2name", "c2id", 30, 7000000, CustomerType.VIP),
        new Customer("c3name", "c3id", 40, 3000000, CustomerType.VVIP),
        new Customer("c4name", "c4id", 20, 1000000, CustomerType.VVIP),
        new Customer("c5name", "c5id", 10, 5000000, CustomerType.NONE)
      };

  @Test
  void printSummary() {
    Comparator<Customer> nameComparator = (c1, c2) -> c1.getName().compareTo(c2.getName());
    Comparator<Customer> timeComparator =
        (c1, c2) -> c1.getSpentTime().compareTo(c2.getSpentTime());
    Comparator<Customer> amountComparator =
        (c1, c2) -> c1.getPayAmount().compareTo(c2.getPayAmount());

    Arrays.sort(testCustomers, nameComparator);
    System.out.println(Arrays.toString(testCustomers));
    System.out.println();

    Arrays.sort(testCustomers, nameComparator.reversed());
    System.out.println(Arrays.toString(testCustomers));
    System.out.println();

    Arrays.sort(testCustomers, timeComparator);
    System.out.println(Arrays.toString(testCustomers));
    System.out.println();

    Arrays.sort(testCustomers, timeComparator.reversed());
    System.out.println(Arrays.toString(testCustomers));
    System.out.println();

    Arrays.sort(testCustomers, amountComparator);
    System.out.println(Arrays.toString(testCustomers));
    System.out.println();

    Arrays.sort(testCustomers, amountComparator.reversed());
    System.out.println(Arrays.toString(testCustomers));
    System.out.println();
  }

  @Test
  void filterTest() {
    Customer[] noneGroup =
        Arrays.stream(testCustomers)
            .filter(customer -> customer.getCustomerType() == NONE)
            .toArray(Customer[]::new);
    Customer[] generalGroup =
        Arrays.stream(testCustomers)
            .filter(customer -> customer.getCustomerType() == GENERAL)
            .toArray(Customer[]::new);
    Customer[] vipGroup =
        Arrays.stream(testCustomers)
            .filter(customer -> customer.getCustomerType() == VIP)
            .toArray(Customer[]::new);
    Customer[] vvipGroup =
        Arrays.stream(testCustomers)
            .filter(customer -> customer.getCustomerType() == VVIP)
            .toArray(Customer[]::new);

    System.out.println(Arrays.toString(noneGroup));
    System.out.println(Arrays.toString(generalGroup));
    System.out.println(Arrays.toString(vipGroup));
    System.out.println(Arrays.toString(vvipGroup));
  }
}
