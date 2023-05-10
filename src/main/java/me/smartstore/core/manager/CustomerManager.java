package me.smartstore.core.manager;

import static me.smartstore.enums.SortBy.*;
import static me.smartstore.exceptions.StoreErrorCode.*;

import java.util.Arrays;
import java.util.Objects;
import me.smartstore.core.domain.Customer;
import me.smartstore.enums.CustomerType;
import me.smartstore.exceptions.StoreException;

public class CustomerManager {
  private static final CustomerManager customerManager = new CustomerManager();
  private static final int DEFAULT_SIZE = 10;
  private static int size = 0;
  private static Customer[] customers = new Customer[DEFAULT_SIZE];

  private CustomerManager() {}

  public static CustomerManager getInstance() {

    return customerManager;
  }

  public int size() {
    return size;
  }

  public Boolean isEmpty() {
    return size == 0;
  }

  public Boolean isFull() {
    return size == customers.length;
  }

  public Customer save(Customer customer) throws StoreException {
    if (isFull()) {
      customers = Arrays.copyOf(customers, size * 2);
    }

    customers[size++] = customer;

    return customer;
  }

  public Customer findByIndex(int index) {
    return customers[index - 1];
  }

  public Customer findById(Long id) throws StoreException {
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> id.equals(customer.getId()))
        .findFirst()
        .orElseThrow(
            () -> {
              throw new StoreException(NO_CUSTOMER);
            });
  }

  public Customer[] selectAll() {
    return Arrays.stream(customers).filter(Objects::nonNull).toArray(Customer[]::new);
  }

  public Customer[] selectByCustomerType(CustomerType customerType) {
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .toArray(Customer[]::new);
  }

  public Customer[] selectByCustomerTypeOrderByName(CustomerType customerType) {
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .sorted(NAME.getCustomerComparator())
        .toArray(Customer[]::new);
  }

  public Customer[] selectByCustomerTypeOrderByNameDesc(CustomerType customerType) {
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .sorted(NAME.getCustomerComparator().reversed())
        .toArray(Customer[]::new);
  }

  public Customer[] selectByCustomerTypeOrderBySpentTime(CustomerType customerType) {
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .sorted(SPENT_TIME.getCustomerComparator())
        .toArray(Customer[]::new);
  }

  public Customer[] selectByCustomerTypeOrderBySpentTimeDesc(CustomerType customerType) {
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .sorted(SPENT_TIME.getCustomerComparator().reversed())
        .toArray(Customer[]::new);
  }

  public Customer[] selectByCustomerTypeOrderByPayAmount(CustomerType customerType) {
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .sorted(PAY_AMOUNT.getCustomerComparator())
        .toArray(Customer[]::new);
  }

  public Customer[] selectByCustomerTypeOrderByPayAmountDesc(CustomerType customerType) {
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .sorted(PAY_AMOUNT.getCustomerComparator().reversed())
        .toArray(Customer[]::new);
  }

  public void deleteById(Long id) throws StoreException {
    Customer target =
        Arrays.stream(customers)
            .filter(Objects::nonNull)
            .filter(customer -> id.equals(customer.getId()))
            .findFirst()
            .orElseThrow(
                () -> {
                  throw new StoreException(NOT_EXIST_ID);
                });

    delete(target);
  }

  public void delete(Customer customer) throws StoreException {
    int idx = -1;
    for (int i = 0; i < size; i++) {
      if (customer.getId().equals(customers[i].getId())) {
        idx = i;
      }
    }
    if (idx == -1) throw new StoreException(NOT_EXIST_CUSTOMER);
    System.arraycopy(customers, idx, customers, idx - 1, customers.length - idx);
    size--;
  }
}
