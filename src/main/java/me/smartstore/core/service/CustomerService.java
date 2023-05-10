package me.smartstore.core.service;

import me.smartstore.core.domain.Customer;
import me.smartstore.core.domain.CustomerDTO;
import me.smartstore.core.domain.CustomerGroup;
import me.smartstore.core.manager.CustomerManager;
import me.smartstore.enums.SortBy;
import me.smartstore.enums.SortOrder;
import me.smartstore.exceptions.StoreException;

import java.util.Arrays;

public class CustomerService {
  private static final CustomerService customerService = new CustomerService();
  private static final CustomerManager customerManager = CustomerManager.getInstance();
  private static final CustomerGroupService customerGroupService =
      CustomerGroupService.getInstance();

  private static SortBy lastRequestSortBy = SortBy.NAME;
  private static SortOrder lastRequestedSortOrder = SortOrder.ASCENDING;

  private static CustomerGroup[] customerGroups;

  public static CustomerService getInstance() {
    return customerService;
  }

  public void saveNewCustomer(CustomerDTO newCustomerDTO) throws StoreException {
    Customer customer = new Customer(newCustomerDTO);
    System.out.println(customerManager.save(customer));
  }

  public CustomerDTO[] findAll() {
    return Arrays.stream(customerManager.selectAll())
        .map(CustomerDTO::from)
        .toArray(CustomerDTO[]::new);
  }

  public int getNumberOfCustomers() {
    return customerManager.size();
  }

  public void updateCustomerById(Long id, CustomerDTO updated) {
    Customer customer = customerManager.findById(id);
  }

  public void deleteCustomerById(Long id) {
    customerManager.deleteById(id);
  }

  public void classifyCustomer() {
    //    Arrays.stream(customerGroups)
    //        .filter(customerGroup -> customerGroup.getParameter() != null)
    //        .forEach(
    //            customerGroup -> {
    //              Arrays.stream(customerManager.selectAll())
    //                  .filter(
    //                      customer ->
    //                          customer.getSpentTime() != null && customer.getPayAmount() != null)
    //                  .forEach(
    //                      customer -> {
    //                        if (customerGroup.getParameter().getMinSpentTime()
    //                                <= customer.getSpentTime()
    //                            && customerGroup.getParameter().getMinPayAmount()
    //                                <= customer.getPayAmount()) {
    //                          customer.setCustomerType(customerGroup.getCustomerType());
    //                        }
    //                      });
    //            });
  }

  public void classifyAllCustomers() {
    customerGroups = customerGroupService.findAll();
    Arrays.stream(customerGroups)
        .filter(customerGroup -> customerGroup.getParameter() != null)
        .forEach(
            customerGroup -> {
              Arrays.stream(customerManager.selectAll())
                  .filter(
                      customer ->
                          customer.getSpentTime() != null && customer.getPayAmount() != null)
                  .forEach(
                      customer -> {
                        if (customerGroup.getParameter().getMinSpentTime()
                                <= customer.getSpentTime()
                            && customerGroup.getParameter().getMinPayAmount()
                                <= customer.getPayAmount()) {
                          customer.setCustomerType(customerGroup.getCustomerType());
                        }
                      });
            });
  }

  public void displayClassificationSummary() {
    displayClassificationSummary(lastRequestSortBy, lastRequestedSortOrder);
  }

  public void displayClassificationSummary(SortBy sortby, SortOrder sortOrder) {
    classifyAllCustomers();
    lastRequestSortBy = sortby;
    lastRequestedSortOrder = sortOrder;
    switch (sortby) {
      case NAME -> displayClassificationSummaryByName(sortOrder);
      case SPENT_TIME -> displayClassificationSummaryBySpentTime(sortOrder);
      case PAY_AMOUNT -> displayClassificationSummaryByPayAmount(sortOrder);
    }
  }

  public void displayClassificationSummaryByName(SortOrder sortOrder) {
    Arrays.stream(customerGroups)
        .forEach(
            group -> {
              System.out.println(group.groupTitle());
              if (sortOrder == SortOrder.ASCENDING) {
                Customer[] customers =
                    customerManager.selectByCustomerTypeOrderByName(group.getCustomerType());
                for (int idx = 0; idx < customers.length; idx++) {
                  System.out.println("No. " + (idx + 1) + " => " + customers[idx]);
                }
              } else {
                Customer[] customers =
                    customerManager.selectByCustomerTypeOrderByNameDesc(group.getCustomerType());
                for (int idx = 0; idx < customers.length; idx++) {
                  System.out.println("No. " + (idx + 1) + " => " + customers[idx]);
                }
              }
            });
  }

  public void displayClassificationSummaryBySpentTime(SortOrder sortOrder) {
    Arrays.stream(customerGroups)
        .forEach(
            group -> {
              System.out.println(group.groupTitle());
              if (sortOrder == SortOrder.ASCENDING) {
                Customer[] customers =
                    customerManager.selectByCustomerTypeOrderBySpentTime(group.getCustomerType());
                for (int idx = 0; idx < customers.length; idx++) {
                  System.out.println("No. " + (idx + 1) + " => " + customers[idx]);
                }
              } else {
                Customer[] customers =
                    customerManager.selectByCustomerTypeOrderBySpentTimeDesc(
                        group.getCustomerType());
                for (int idx = 0; idx < customers.length; idx++) {
                  System.out.println("No. " + (idx + 1) + " => " + customers[idx]);
                }
              }
            });
  }

  public void displayClassificationSummaryByPayAmount(SortOrder sortOrder) {
    Arrays.stream(customerGroups)
        .forEach(
            group -> {
              System.out.println(group.groupTitle());
              if (sortOrder == SortOrder.ASCENDING) {
                Customer[] customers =
                    customerManager.selectByCustomerTypeOrderByPayAmount(group.getCustomerType());
                for (int idx = 0; idx < customers.length; idx++) {
                  System.out.println("No. " + (idx + 1) + " => " + customers[idx]);
                }
              } else {
                Customer[] customers =
                    customerManager.selectByCustomerTypeOrderByPayAmountDesc(
                        group.getCustomerType());
                for (int idx = 0; idx < customers.length; idx++) {
                  System.out.println("No. " + (idx + 1) + " => " + customers[idx]);
                }
              }
            });
  }
}
