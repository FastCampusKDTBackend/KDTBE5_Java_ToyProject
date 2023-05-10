package me.smartstore.core.service;

import me.smartstore.core.domain.Customer;
import me.smartstore.core.domain.CustomerDTO;
import me.smartstore.core.domain.CustomerGroup;
import me.smartstore.core.manager.CustomerManager;
import me.smartstore.enums.CustomerType;
import me.smartstore.enums.SortBy;
import me.smartstore.enums.SortOrder;
import me.smartstore.exceptions.StoreException;

import java.util.Arrays;

/**
 * 고객 정보 관리 서비스 제공 클래스
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public class CustomerService {
  private static CustomerService customerService = new CustomerService();
  private static final CustomerManager customerManager = CustomerManager.getInstance();
  private static final CustomerGroupService customerGroupService =
      CustomerGroupService.getInstance();

  private static SortBy lastRequestSortBy = SortBy.NAME;
  private static SortOrder lastRequestedSortOrder = SortOrder.ASCENDING;

  private static CustomerGroup[] customerGroups = customerGroupService.findAll();

  public static CustomerService getInstance() {
    if (customerService == null) {
      customerService = new CustomerService();
    }
    return customerService;
  }

  /**
   * @param customerDTO 고객 정보
   * @throws StoreException 데이터베이스 오류
   */
  public void save(CustomerDTO customerDTO) throws StoreException {
    Customer customer = new Customer(customerDTO);
    classifyCustomer(customer);
    customerManager.save(customer);
  }

  /**
   * @return 모든 고객 정보
   * @throws StoreException 등록된 고객 정보가 없는 경우
   */
  public CustomerDTO[] findAll() throws StoreException {
    return Arrays.stream(customerManager.selectAll())
        .map(CustomerDTO::from)
        .toArray(CustomerDTO[]::new);
  }

  public int getNumberOfCustomers() {
    return customerManager.size();
  }

  /**
   * @param id 업데이트 할 고객정보 관리번호
   * @param customerDTO 업데이트할 고객정보
   * @throws StoreException 데이터베이스 오류
   */
  public void updateCustomerById(Long id, CustomerDTO customerDTO) throws StoreException {
    Customer customer = customerManager.findById(id);

    if (customerDTO.name() != null) {
      customer.setName(customerDTO.name());
    }
    if (customerDTO.userId() != null) {
      customer.setUserId(customerDTO.userId());
    }
    if (customerDTO.spentTime() != null) {
      customer.setSpentTime(customerDTO.spentTime());
    }
    if (customerDTO.payAmount() != null) {
      customer.setPayAmount(customerDTO.payAmount());
    }

    classifyCustomer(customer);

    customerManager.save(customer);
  }

  public void deleteCustomerById(Long id) {
    customerManager.deleteById(id);
  }

  /**
   * 고객정보를 바탕으로 고객 분류
   *
   * @param customer 고객정보
   */
  public void classifyCustomer(Customer customer) {
    for (CustomerGroup customerGroup : customerGroups) {
      if (customerGroup.getParameter() == null) {
        continue;
      }
      if (customer.getSpentTime() == null || customer.getPayAmount() == null) {
        customer.setCustomerType(CustomerType.NONE);
      } else {
        if (customerGroup.getParameter().getMinSpentTime() <= customer.getSpentTime()
            && customerGroup.getParameter().getMinPayAmount() <= customer.getPayAmount()) {
          customer.setCustomerType(customerGroup.getCustomerType());
        }
      }
    }
    if (customer.getCustomerType() == null) {
      customer.setCustomerType(CustomerType.NONE);
    }
  }

  public void classifyAllCustomers() {
    customerGroups = customerGroupService.findAll();
    Customer[] customers = customerManager.selectAll();

    for (Customer customer : customers) {
      classifyCustomer(customer);
      customerManager.save(customer);
    }
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
              System.out.println("\n" + group.groupTitle());
              if (sortOrder == SortOrder.ASCENDING) {
                Customer[] customers =
                    customerManager.selectByCustomerTypeOrderByNameAsc(group.getCustomerType());
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
                    customerManager.selectByCustomerTypeOrderBySpentTimeAsc(
                        group.getCustomerType());
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
              System.out.println();
            });
  }

  public void displayClassificationSummaryByPayAmount(SortOrder sortOrder) {
    Arrays.stream(customerGroups)
        .forEach(
            group -> {
              System.out.println(group.groupTitle());
              if (sortOrder == SortOrder.ASCENDING) {
                Customer[] customers =
                    customerManager.selectByCustomerTypeOrderByPayAmountAsc(
                        group.getCustomerType());
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
              System.out.println();
            });
  }
}
