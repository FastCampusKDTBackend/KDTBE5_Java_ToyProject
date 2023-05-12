package me.smartstore.core.manager;

import static me.smartstore.exceptions.StoreErrorCode.NO_MATCHING_GROUP;
import static me.smartstore.exceptions.StoreErrorCode.UNKNOWN_TYPE;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import me.smartstore.core.domain.CustomerGroup;
import me.smartstore.enums.CustomerType;
import me.smartstore.exceptions.StoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class CustomerGroupManagerTest {
  private static final CustomerGroupManager customerGroupManager =
      CustomerGroupManager.getInstance();

  @DisplayName("[SAVE] 고객 그룹 - 저장 성공")
  @Test
  void givenCustomerGroup_whenSave_thenReturnsSavedCustomerGroup() {
    CustomerGroup customerGroup = new CustomerGroup(CustomerType.GENERAL, null);

    CustomerGroup actual = customerGroupManager.save(customerGroup);

    assertEquals(customerGroup, actual);
    assertEquals(actual.getCustomerType(), customerGroup.getCustomerType());
    assertEquals(actual.getParameter(), customerGroup.getParameter());
  }

  @DisplayName("[SAVE] 고객 그룹을 알 수 없는 경우 - 저장 실패")
  @Test
  void givenUnknownCustomerType_whenSave_thenThrowsUnknownTypeStoreException() {
    // Given
    CustomerGroup customerGroup = new CustomerGroup(null, null);

    // When & Then
    StoreException storeException =
        assertThrows(StoreException.class, () -> customerGroupManager.save(customerGroup));
    assertEquals(UNKNOWN_TYPE, storeException.getErrorCode());
    assertEquals(UNKNOWN_TYPE.getMessage(), storeException.getMessage());
  }

  @DisplayName("[SELECT] 고객 유형별 그룹 조회 - 조회 성공")
  @ParameterizedTest(name = "고객 유형 (CustomerType) : {0}")
  @EnumSource(CustomerType.class)
  void givenCustomerType_whenSelectCustomerGroup_thenReturnsSelectedCustomerGroup(
      CustomerType customerType) {
    // Given

    // Given && When
    CustomerGroup actual = customerGroupManager.selectCustomerGroupByCustomerType(customerType);

    // Then
    assertEquals(customerType, actual.getCustomerType());
  }

  @DisplayName("[SELECT] 고객 유형이 주어지지 않은 경우 - 조회 실패")
  @Test
  void givenNothing_whenSelectCustomerGroup_thenReturnsSelectedCustomerGroup() {
    // Given

    // When & Then
    StoreException exception =
        assertThrows(
            StoreException.class,
            () -> customerGroupManager.selectCustomerGroupByCustomerType(null));
    assertEquals(NO_MATCHING_GROUP, exception.getErrorCode());
    assertEquals(NO_MATCHING_GROUP.getMessage(), exception.getMessage());
  }

  @DisplayName("[SELECT] 모든 고객 그룹 조회")
  @ParameterizedTest(name = "고객 유형 (CustomerType): {0}")
  @EnumSource(CustomerType.class)
  void givenNothing_whenSelectAllCustomerGroup_thenReturnsAllCustomerGroupArray(
      CustomerType customerType) {
    // Given

    // When
    CustomerGroup[] actual = customerGroupManager.selectAllCustomerGroup();

    // Then
    assertTrue(Arrays.stream(actual).anyMatch(group -> group.getCustomerType() == customerType));
  }
}
