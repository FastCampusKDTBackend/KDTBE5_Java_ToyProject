package service;

import domain.customer.Customer;
import domain.customer.Customers;
import exception.ArrayEmptyException;

import java.util.Objects;

public class CustomerService {

    private static CustomerService customerService;
    private final Customers customers;

    private CustomerService() {
        customers = Customers.getInstance();
    }

    public static CustomerService getInstance() {
        if (Objects.isNull(customerService)){
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void insertCustomer(Customer customer){
        customers.add(customer);
    }

    public Customers selectAllCustomer() {
        return customers;
    }

    public void deleteCustomerByNumber(int number){
        if (customers.isEmpty()){
            throw new ArrayEmptyException();
        }
        customers.pop(number);
    }

    // null 을 반환하지 말고 exception 을 던질 것
    // 지금은 input 에서 범위 처리 중
    public Customer selectCustomerByNumber(int number) {
        if (customers.get(number) != null){
            return customers.get(number);
        }
        return null;
    }
}
