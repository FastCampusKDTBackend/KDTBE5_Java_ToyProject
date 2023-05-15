package me.smartstore.core.manager;

import static me.smartstore.exceptions.StoreErrorCode.*;

import java.util.Arrays;
import me.smartstore.core.domain.CustomerGroup;
import me.smartstore.enums.CustomerType;
import me.smartstore.exceptions.StoreException;

/**
 * 스마트스토어의 모든 고객 그룹, 세분화 기준 저장 (Database 대체용)
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public class CustomerGroupManager {
  private static CustomerGroupManager customerGroupManager = new CustomerGroupManager();
  private final CustomerGroup[] customerGroups;

  public CustomerGroupManager() {
    customerGroups =
        Arrays.stream(CustomerType.values())
            .map(customerType -> new CustomerGroup(customerType, null))
            .toArray(CustomerGroup[]::new);
  }

  public static CustomerGroupManager getInstance() {
    if (customerGroupManager == null) {
      customerGroupManager = new CustomerGroupManager();
    }
    return customerGroupManager;
  }

  /**
   * 설정이 완료된 고객 그룹 저장
   *
   * @param customerGroup 고객 그룹
   * @return 저장된 고객 그룹
   * @throws StoreException 그룹 타입을 알 수 없는 경우
   */
  public CustomerGroup save(CustomerGroup customerGroup) throws StoreException {
    if (customerGroup == null || customerGroup.getCustomerType() == null)
      throw new StoreException(UNKNOWN_TYPE);

    for (int idx = 0; idx < customerGroups.length; idx++) {
      if (customerGroup.getCustomerType() == customerGroups[idx].getCustomerType()) {
        customerGroups[idx] = customerGroup;
      }
    }

    return customerGroup;
  }

  /**
   * @param customerType 고객 유형
   * @return 일치하는 그룹
   * @throws StoreException 일치하는 그룹 없음
   */
  public CustomerGroup selectCustomerGroupByCustomerType(CustomerType customerType)
      throws StoreException {
    return Arrays.stream(customerGroups)
        .filter(customerGroup -> customerGroup.getCustomerType() == customerType)
        .findFirst()
        .orElseThrow(
            () -> {
              throw new StoreException(NO_MATCHING_GROUP);
            });
  }

  /**
   * @return 모든 고객 그룹
   */
  public CustomerGroup[] selectAllCustomerGroup() {
    return Arrays.stream(customerGroups).toArray(CustomerGroup[]::new);
  }
}