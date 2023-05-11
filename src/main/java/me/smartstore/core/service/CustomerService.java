package me.smartstore.core.service;

import java.util.Arrays;
import me.smartstore.core.domain.Customer;
import me.smartstore.core.domain.CustomerDTO;
import me.smartstore.core.domain.CustomerGroupDTO;
import me.smartstore.core.manager.CustomerManager;
import me.smartstore.enums.CustomerType;
import me.smartstore.enums.SortBy;
import me.smartstore.enums.SortOrder;
import me.smartstore.exceptions.StoreException;

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

  /////////////////////////////////////////////////////////
  // 요청 사항 처리를 위한 캐싱 역할 변수들
  private static SortBy lastRequestedSortBy = SortBy.NAME;
  private static SortOrder lastRequestedSortOrder = SortOrder.ASCENDING;
  private static CustomerGroupDTO[] customerGroupDTOs = customerGroupService.findAll();
  private static final CustomerDTO[][] classifiedCustomerDTOs =
      new CustomerDTO[customerGroupDTOs.length][];

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
   * @throws StoreException 데이터베이스 오류
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

  /**
   * 고객 관리 번호를 통한 조회 및 삭제
   *
   * @param id 고객 관리 번호
   */
  public void deleteCustomerById(Long id) {
    customerManager.deleteById(id);
  }

  /**
   * 고객정보를 바탕으로 고객 분류
   *
   * @param customer 고객정보
   */
  public void classifyCustomer(Customer customer) {
    for (CustomerGroupDTO customerGroupDTO : customerGroupDTOs) {
      if (customerGroupDTO.parameter() == null) {
        continue;
      }
      if (customer.getSpentTime() == null || customer.getPayAmount() == null) {
        customer.setCustomerType(CustomerType.NONE);
      } else {
        if (customerGroupDTO.parameter().getMinSpentTime() <= customer.getSpentTime()
            && customerGroupDTO.parameter().getMinPayAmount() <= customer.getPayAmount()) {
          customer.setCustomerType(customerGroupDTO.customerType());
        }
      }
    }
    if (customer.getCustomerType() == null) {
      customer.setCustomerType(CustomerType.NONE);
    }
  }

  /** 모든 고객 분류 */
  public void classifyAllCustomers() {
    customerGroupDTOs = customerGroupService.findAll();
    Customer[] customers = customerManager.selectAll();

    for (Customer customer : customers) {
      classifyCustomer(customer);
      customerManager.save(customer);
    }
  }

  /** 고객 분류 및 요약 정보 출력. 가장 최근에 조회한 기준에 따라 자동으로 정렬. */
  public CustomerDTO[][] getClassifiedCustomerData() {
    return getClassifiedCustomerData(lastRequestedSortBy, lastRequestedSortOrder);
  }

  /**
   * 고객 분류 결과를 기준에 따라 정렬한 결과를 뷰로 전달
   *
   * @param sortBy 정렬기준
   * @param sortOrder 정렬순서 (오름차순, 내림차순)
   */
  public CustomerDTO[][] getClassifiedCustomerData(SortBy sortBy, SortOrder sortOrder) {
    classifyAllCustomers();
    lastRequestedSortBy = sortBy;
    lastRequestedSortOrder = sortOrder;
    switch (sortBy) {
      case NAME -> groupingCustomersByCustomerTypeSortByName(sortOrder);
      case SPENT_TIME -> groupingCustomersByCustomerTypeSortBySpentTime(sortOrder);
      case PAY_AMOUNT -> groupingCustomersByCustomerTypeSortByPayAmount(sortOrder);
    }

    return classifiedCustomerDTOs;
  }

  /**
   * 고객 분류 결과를 이름순으로 정렬.
   *
   * @param sortOrder 정렬순서 (오름차순, 내림차순)
   */
  private void groupingCustomersByCustomerTypeSortByName(SortOrder sortOrder) {
    for (int gIdx = 0; gIdx < customerGroupDTOs.length; gIdx++) {
      if (sortOrder == SortOrder.ASCENDING) {
        classifiedCustomerDTOs[gIdx] =
            Arrays.stream(
                    customerManager.selectByCustomerTypeOrderByNameAsc(
                        customerGroupDTOs[gIdx].customerType()))
                .map(CustomerDTO::from)
                .toArray(CustomerDTO[]::new);
      } else {
        classifiedCustomerDTOs[gIdx] =
            Arrays.stream(
                    customerManager.selectByCustomerTypeOrderByNameDesc(
                        customerGroupDTOs[gIdx].customerType()))
                .map(CustomerDTO::from)
                .toArray(CustomerDTO[]::new);
      }
    }
  }

  /**
   * 고객 분류 결과를 이용시간순으로 정렬.
   *
   * @param sortOrder 정렬순서 (오름차순, 내림차순)
   */
  private void groupingCustomersByCustomerTypeSortBySpentTime(SortOrder sortOrder) {
    for (int gIdx = 0; gIdx < customerGroupDTOs.length; gIdx++) {
      if (sortOrder == SortOrder.ASCENDING) {
        classifiedCustomerDTOs[gIdx] =
            Arrays.stream(
                    customerManager.selectByCustomerTypeOrderBySpentTimeAsc(
                        customerGroupDTOs[gIdx].customerType()))
                .map(CustomerDTO::from)
                .toArray(CustomerDTO[]::new);
      } else {
        classifiedCustomerDTOs[gIdx] =
            Arrays.stream(
                    customerManager.selectByCustomerTypeOrderBySpentTimeDesc(
                        customerGroupDTOs[gIdx].customerType()))
                .map(CustomerDTO::from)
                .toArray(CustomerDTO[]::new);
      }
    }
  }

  /**
   * 고객 분류 결과를 사용금액순으로 정렬.
   *
   * @param sortOrder 정렬순서 (오름차순, 내림차순)
   */
  private void groupingCustomersByCustomerTypeSortByPayAmount(SortOrder sortOrder) {
    for (int gIdx = 0; gIdx < customerGroupDTOs.length; gIdx++) {
      if (sortOrder == SortOrder.ASCENDING) {
        classifiedCustomerDTOs[gIdx] =
            Arrays.stream(
                    customerManager.selectByCustomerTypeOrderByPayAmountAsc(
                        customerGroupDTOs[gIdx].customerType()))
                .map(CustomerDTO::from)
                .toArray(CustomerDTO[]::new);
      } else {
        classifiedCustomerDTOs[gIdx] =
            Arrays.stream(
                    customerManager.selectByCustomerTypeOrderByPayAmountDesc(
                        customerGroupDTOs[gIdx].customerType()))
                .map(CustomerDTO::from)
                .toArray(CustomerDTO[]::new);
      }
    }
  }
}
