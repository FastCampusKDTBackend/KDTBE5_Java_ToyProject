package me.smartstore.core.manager;

import static me.smartstore.enums.CustomerType.*;
import static me.smartstore.exceptions.StoreErrorCode.*;

import java.util.Arrays;
import me.smartstore.core.domain.CustomerGroup;
import me.smartstore.core.domain.Parameter;
import me.smartstore.enums.CustomerType;
import me.smartstore.exceptions.StoreException;

/**
 * 고객 그룹, 세분화 기준 관리
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public class CustomerGroupManager {
  private static final CustomerGroupManager customerGroupManager = new CustomerGroupManager();
  private final CustomerGroup[] customerGroups;

  public CustomerGroupManager() {
    customerGroups =
        new CustomerGroup[] {
          new CustomerGroup(NONE, null),
          new CustomerGroup(GENERAL, null),
          new CustomerGroup(VIP, null),
          new CustomerGroup(VVIP, null)
        };
  }

  public static CustomerGroupManager getInstance() {
    return customerGroupManager;
  }

  /**
   * @param customerType 고객 유형
   * @return 일치하는 그룹
   * @throws StoreException 일치하는 그룹 없음
   */
  public CustomerGroup select(CustomerType customerType) throws StoreException {
    return Arrays.stream(customerGroups)
        .filter(customerGroup -> customerGroup.getCustomerType() == customerType)
        .findFirst()
        .orElseThrow(
            () -> {
              throw new StoreException(NO_GROUP);
            });
  }

  /**
   * @return 모든 고객 그룹
   */
  public CustomerGroup[] selectAll() {
    return Arrays.stream(customerGroups).toArray(CustomerGroup[]::new);
  }

  /**
   * 그룹별 세분화 기준 저장
   *
   * @param customerType 고객 유형
   * @param parameter 세부화 기준
   * @return 설정이 완료된 고객 그룹
   */
  public CustomerGroup save(CustomerType customerType, Parameter parameter) {
    CustomerGroup customerGroup = select(customerType);

    if (customerGroup.getParameter() == null) {
      customerGroup.setParameter(parameter);
      return customerGroup;
    }

    if (parameter.getMinSpentTime() != null) {
      customerGroup.getParameter().setMinSpentTime(parameter.getMinSpentTime());
    }

    if (parameter.getMinPayAmount() != null) {
      customerGroup.getParameter().setMinPayAmount(parameter.getMinPayAmount());
    }

    return customerGroup;
  }
}
