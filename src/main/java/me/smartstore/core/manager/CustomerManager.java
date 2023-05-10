package me.smartstore.core.manager;

import static me.smartstore.enums.SortBy.*;
import static me.smartstore.exceptions.StoreErrorCode.*;

import java.util.Arrays;
import java.util.Objects;
import me.smartstore.core.domain.Customer;
import me.smartstore.enums.CustomerType;
import me.smartstore.exceptions.StoreException;

/**
 * 스마트스토어 이용 고객 정보 저장 (Database 대체용)
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
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

  /**
   * 기존에 저장되어 있는 고객인 경우 고객 정보를 새롭게 업데이트. 그렇지 않은 경우 고객 정보를 새로 저장.
   *
   * @param customer 고객 정보
   * @return 저장 또는 업데이트 된 고객 정보
   * @throws StoreException 기타 데이터베이스 오류
   */
  public Customer save(Customer customer) throws StoreException {

    for (int idx = 0; idx < customers.length; idx++) {
      if (customer.getId().equals(customers[idx].getId())) {
        customers[idx] = customer;
        return customer;
      }
    }

    if (isFull()) customers = Arrays.copyOf(customers, size * 2);

    customers[size++] = customer;

    return customer;
  }

  /**
   * @param id 고객정보 관리번호
   * @return 해당 관리번호의 고객정보
   * @throws StoreException 일치하는 고객정보가 없는 경우n
   */
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

  /**
   * @return 저장되어 있는 모든 고객정보
   * @throws StoreException 등록된 고객 정보가 없는 경우
   */
  public Customer[] selectAll() throws StoreException {
    if (isEmpty()) throw new StoreException(NO_CUSTOMER);
    return Arrays.stream(customers).filter(Objects::nonNull).toArray(Customer[]::new);
  }

  /**
   * @param customerType 고객 유형
   * @return 유형이 일치하는 모든 고객정보
   * @throws StoreException 등록된 고객 정보가 없는 경우
   */
  public Customer[] selectByCustomerType(CustomerType customerType) throws StoreException {
    if (isEmpty()) throw new StoreException(NO_CUSTOMER);
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .toArray(Customer[]::new);
  }

  /**
   * @param customerType 고객 유형
   * @return 유형이 일치하는 모든 고객정보를 이름에 따라 오름차순으로 정렬한 결과
   * @throws StoreException 등록된 고객 정보가 없는 경우
   */
  public Customer[] selectByCustomerTypeOrderByNameAsc(CustomerType customerType)
      throws StoreException {
    if (isEmpty()) throw new StoreException(NO_CUSTOMER);
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .sorted(NAME.getCustomerComparator())
        .toArray(Customer[]::new);
  }

  /**
   * @param customerType 고객 유형
   * @return 유형이 일치하는 모든 고객정보를 이름에 따라 내림차순으로 정렬한 결과
   * @throws StoreException 등록된 고객 정보가 없는 경우
   */
  public Customer[] selectByCustomerTypeOrderByNameDesc(CustomerType customerType)
      throws StoreException {
    if (isEmpty()) throw new StoreException(NO_CUSTOMER);
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .sorted(NAME.getCustomerComparator().reversed())
        .toArray(Customer[]::new);
  }

  /**
   * @param customerType 고객 유형
   * @return 유형이 일치하는 모든 고객정보를 이용시간에 따라 오름차순으로 정렬한 결과
   * @throws StoreException 등록된 고객 정보가 없는 경우
   */
  public Customer[] selectByCustomerTypeOrderBySpentTimeAsc(CustomerType customerType)
      throws StoreException {
    if (isEmpty()) throw new StoreException(NO_CUSTOMER);
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .sorted(SPENT_TIME.getCustomerComparator())
        .toArray(Customer[]::new);
  }

  /**
   * @param customerType 고객 유형
   * @return 유형이 일치하는 모든 고객정보를 이용시간에 따라 내림차순으로 정렬한 결과
   * @throws StoreException 등록된 고객 정보가 없는 경우
   */
  public Customer[] selectByCustomerTypeOrderBySpentTimeDesc(CustomerType customerType)
      throws StoreException {
    if (isEmpty()) throw new StoreException(NO_CUSTOMER);
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .sorted(SPENT_TIME.getCustomerComparator().reversed())
        .toArray(Customer[]::new);
  }

  /**
   * @param customerType 고객 유형
   * @return 유형이 일치하는 모든 고객정보를 결제금액에 따라 오름차순으로 정렬한 결과
   * @throws StoreException 등록된 고객 정보가 없는 경우
   */
  public Customer[] selectByCustomerTypeOrderByPayAmountAsc(CustomerType customerType)
      throws StoreException {
    if (isEmpty()) throw new StoreException(NO_CUSTOMER);
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .sorted(PAY_AMOUNT.getCustomerComparator())
        .toArray(Customer[]::new);
  }

  /**
   * @param customerType 고객 유형
   * @return 유형이 일치하는 모든 고객정보를 결제금액에 따라 내림차순으로 정렬한 결과
   * @throws StoreException 등록된 고객 정보가 없는 경우
   */
  public Customer[] selectByCustomerTypeOrderByPayAmountDesc(CustomerType customerType)
      throws StoreException {
    if (isEmpty()) throw new StoreException(NO_CUSTOMER);
    return Arrays.stream(customers)
        .filter(Objects::nonNull)
        .filter(customer -> customer.getCustomerType() == customerType)
        .sorted(PAY_AMOUNT.getCustomerComparator().reversed())
        .toArray(Customer[]::new);
  }

  /**
   * @param id 삭제 대상의 관리 번호
   * @throws StoreException 존재하지 않는 관리 번호
   */
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

  /**
   * @param customer 삭제할 고객정보
   * @throws StoreException 데이터베이스에 해당 고객정보가 없는 경우
   */
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
