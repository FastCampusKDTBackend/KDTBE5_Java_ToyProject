package me.smartstore.group;

import me.smartstore.collections.MyArrayList;
import me.smartstore.customer.Customer;
import me.smartstore.customer.Customers;

public class ClassifiedCustomers {
    private MyArrayList<Customer> classifiedByGeneral;
    private MyArrayList<Customer> classifiedByVIP;
    private MyArrayList<Customer> classifiedByVVIP;

    private MyArrayList<MyArrayList<Customer>> classifications;

    private static ClassifiedCustomers classifiedCustomers;

    private ClassifiedCustomers() {
    }

    public static ClassifiedCustomers getInstance() {
        if (classifiedCustomers == null) {
            classifiedCustomers = new ClassifiedCustomers();
        }
        return classifiedCustomers;
    }

    public void refresh() {
        Customers customers = Customers.getInstance();
        MyArrayList<Customer> customerList = customers.getCustomers();
        initCustomerList();

        for (Customer customer : customerList) {
            if (customer.getGroup() == GroupType.General) {
                classifiedByGeneral.add(customer);
                continue;
            }
            if (customer.getGroup() == GroupType.VIP) {
                classifiedByVIP.add(customer);
                continue;
            }
            classifiedByVVIP.add(customer);
        }
    }

    private void initCustomerList() {
        classifiedByGeneral = new MyArrayList<>();
        classifiedByVIP = new MyArrayList<>();
        classifiedByVVIP = new MyArrayList<>();
    }

    public MyArrayList<Customer> getClassifiedByGeneral() {
        return classifiedByGeneral;
    }

    public MyArrayList<Customer> getClassifiedByVIP() {
        return classifiedByVIP;
    }

    public MyArrayList<Customer> getClassifiedByVVIP() {
        return classifiedByVVIP;
    }

    public MyArrayList<MyArrayList<Customer>> getClassifications() {
        return classifications;
    }
}
