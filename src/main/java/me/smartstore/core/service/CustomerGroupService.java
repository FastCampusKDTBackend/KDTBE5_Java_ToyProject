package me.smartstore.core.service;

import static me.smartstore.exceptions.StoreErrorCode.NO_MATCHING_GROUP;

import java.util.Arrays;
import me.smartstore.core.domain.CustomerGroup;
import me.smartstore.core.domain.CustomerGroupDTO;
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
  private static CustomerGroupService customerGroupService = new CustomerGroupService();
  private final CustomerGroupManager customerGroupManager = CustomerGroupManager.getInstance();
  private final CustomerService customerService = CustomerService.getInstance();

  public static CustomerGroupService getInstance() {
    if (customerGroupService == null) {
      customerGroupService = new CustomerGroupService();
    }
    return customerGroupService;
  }

  /**
   * 그룹 파라미터 설정 후 저장
   *
   * @param customerGroupDTO 고객 그룹 DTO
   * @param parameter 분류 기준
   * @return 설정이 완료된 고객 그룹
   */
  public CustomerGroupDTO setGroupParameter(
      CustomerGroupDTO customerGroupDTO, Parameter parameter) {
    Parameter updatedParameter = customerGroupDTO.parameter();

    if (customerGroupDTO.parameter() != null) {
      if (parameter.getMinSpentTime() != null)
        updatedParameter.setMinSpentTime(parameter.getMinSpentTime());
      if (parameter.getMinPayAmount() != null)
        updatedParameter.setMinPayAmount(parameter.getMinPayAmount());
    } else {
      updatedParameter = parameter;
    }

    CustomerGroupDTO updatedDTO =
        CustomerGroupDTO.of(customerGroupDTO.customerType(), updatedParameter);

    customerGroupManager.save(CustomerGroupDTO.toEntity(updatedDTO));
    customerService.classifyAllCustomers();

    return updatedDTO;
  }

  /**
   * @param customerType 고객 유형
   * @return 고객 유형과 일치하는 그룹
   * @throws StoreException 데이터베이스에 일치하는 그룹이 없을 경우
   */
  public CustomerGroupDTO find(CustomerType customerType) throws StoreException {
    CustomerGroup customerGroup =
        customerGroupManager.selectCustomerGroupByCustomerType(customerType);

    if (customerGroup == null) throw new StoreException(NO_MATCHING_GROUP);

    return CustomerGroupDTO.fromEntity(customerGroup);
  }

  /**
   * @return 데이터베이스에 저장된 모든 고객 그룹
   * @throws StoreException 데이터베이스 오류
   */
  public CustomerGroupDTO[] findAll() throws StoreException {
    return Arrays.stream(customerGroupManager.selectAllCustomerGroup())
        .map(CustomerGroupDTO::fromEntity)
        .toArray(CustomerGroupDTO[]::new);
  }
}
