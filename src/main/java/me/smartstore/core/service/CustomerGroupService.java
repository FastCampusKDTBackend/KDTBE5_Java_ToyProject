package me.smartstore.core.service;

import me.smartstore.core.domain.CustomerGroup;
import me.smartstore.core.domain.Parameter;
import me.smartstore.core.manager.CustomerGroupManager;
import me.smartstore.enums.CustomerType;
import me.smartstore.exceptions.StoreException;

/**
 * 고객 유형 관리 서비스 제공 클래스
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public class CustomerGroupService {
  private static final CustomerGroupService customerGroupService = new CustomerGroupService();
  private final CustomerGroupManager customerGroupManager = CustomerGroupManager.getInstance();
  private final CustomerService customerService = CustomerService.getInstance();

  public static CustomerGroupService getInstance() {
    return customerGroupService;
  }

  /**
   * 고객 그룹 분류 기준 설정. 분류 기준이 새로 설정된 경우 고객 정보도 함께 업데이트
   *
   * @param customerType 고객 유형
   * @param parameter 분류 기준
   * @return 설정이 완료된 고객 그룹
   * @throws StoreException 일치하는 고객 그룹이 데이터베이스 없는 경우
   */
  public CustomerGroup setParameter(CustomerType customerType, Parameter parameter)
      throws StoreException {
    CustomerGroup customerGroup =
        customerGroupManager.selectCustomerGroupByCustomerType(customerType);

    if (customerGroup.getParameter() == null) {
      customerGroup.setParameter(parameter);
    } else {
      if (parameter.getMinSpentTime() != null) {
        customerGroup.getParameter().setMinSpentTime(parameter.getMinSpentTime());
      }

      if (parameter.getMinPayAmount() != null) {
        customerGroup.getParameter().setMinPayAmount(parameter.getMinPayAmount());
      }
    }

    customerService.classifyAllCustomers();

    return customerGroupManager.save(customerGroup);
  }

  /**
   * @param customerType 고객 유형
   * @return 고객 유형과 일치하는 그룹
   * @throws StoreException 데이터베이스에 일치하는 그룹이 없을 경우
   */
  public CustomerGroup find(CustomerType customerType) throws StoreException {
    return customerGroupManager.selectCustomerGroupByCustomerType(customerType);
  }

  /**
   * @return 데이터베이스에 저장된 모든 고객 그룹
   * @throws StoreException 데이터베이스 오류
   */
  public CustomerGroup[] findAll() throws StoreException {
    return customerGroupManager.selectAllCustomerGroup();
  }
}
