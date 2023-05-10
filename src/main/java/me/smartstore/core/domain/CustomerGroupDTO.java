package me.smartstore.core.domain;

import me.smartstore.enums.CustomerType;

/**
 * 스마트스토어 애플리케이션 내에서 그룹 정보 전달
 *
 * @param customerType 그룹별 고객 유형
 * @param parameter 그룹별 분류 기준
 */
public record CustomerGroupDTO(CustomerType customerType, Parameter parameter) {
  public static CustomerGroupDTO from(CustomerGroup customerGroup) {
    return new CustomerGroupDTO(customerGroup.getCustomerType(), customerGroup.getParameter());
  }

  public static CustomerGroupDTO of(CustomerType customerType, Parameter parameter) {
    return new CustomerGroupDTO(customerType, parameter);
  }
}
