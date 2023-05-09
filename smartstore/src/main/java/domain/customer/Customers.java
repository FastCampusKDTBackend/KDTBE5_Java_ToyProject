package domain.customer;

import domain.group.GroupType;
import util.common.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Customers {
    private static Customers customers = null;

    private final List<Customer> customerRepository = new ArrayList<>();

    private Customers() {
    }

    public static Customers getInstance() {
        if (customers == null) {
            customers = new Customers();
        }
        return customers;
    }

    public Customer getByCustomerNumber(int customerNumber) {
        return customerRepository.get(customerNumber - 1);
    }

    public void save(Customer customer) {
        customerRepository.add(customer);
    }

    public void deleteByCustomerNumber(int customerNumber) {
        Customer customer = getByCustomerNumber(customerNumber);
        System.out.println(customer);
        customerRepository.remove(customer);
        viewCustomersInfo();
    }

    public int size() {
        return customerRepository.size();
    }

    public void viewCustomersInfo() {

        if (customerRepository.size() == 0) {
            throw new NotFoundException("No Customers. Please input one first.");
        }

        for (int i = 0; i < customerRepository.size(); i++) {
            System.out.println("No. " + (i + 1) + " => " + customerRepository.get(i).toString());
        }
    }

    public List<Customer> findByGroup(GroupType groupType) {
        return customerRepository.stream()
                .filter(customer -> customer.getGroupType() == groupType)
                .collect(Collectors.toList());
    }

}
