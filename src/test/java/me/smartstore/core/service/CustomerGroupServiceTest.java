package me.smartstore.core.service;

import static me.smartstore.exceptions.StoreErrorCode.NO_MATCHING_GROUP;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import me.smartstore.core.domain.CustomerGroup;
import me.smartstore.core.domain.CustomerGroupDTO;
import me.smartstore.core.domain.Parameter;
import me.smartstore.enums.CustomerType;
import me.smartstore.exceptions.StoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerGroupServiceTest {
  private final CustomerGroupService customerGroupService = CustomerGroupService.getInstance();

  @DisplayName("[setGroupParameter] 그룹 DTO, 파라미터 전달하면 파라미터 업데이트 후 저장하고 업데이트 된 DTO 반환하고 고객 재분류")
  @Test
  void givenCustomerGroupDTOAndParameter_whenSetGroupParameter_thenSavesAndReturnsUpdatedDTO() {
    // Given
    CustomerGroupDTO beforeGroupDTO =
        CustomerGroupDTO.of(CustomerType.GENERAL, new Parameter(123, 123456));
    Parameter updateParameter = new Parameter(987, 987654);

    // When
    CustomerGroupDTO afterGroupDTO =
        customerGroupService.setGroupParameter(beforeGroupDTO, updateParameter);

    // Then
    assertEquals(afterGroupDTO.customerType(), beforeGroupDTO.customerType());
    assertEquals(afterGroupDTO.parameter().getMinSpentTime(), 987);
    assertEquals(afterGroupDTO.parameter().getMinPayAmount(), 987654);
  }

  @DisplayName("[find] 고객 유형을 전달하면 일치하는 그룹 DTO 를 반환")
  @Test
  void givenCustomerType_whenFind_thenReturnsCustomerGroupDTO() {
    // Given
    CustomerType customerType = CustomerType.GENERAL;
    CustomerGroup customerGroup = new CustomerGroup(customerType, any());
    CustomerGroupDTO customerGroupDTO = CustomerGroupDTO.fromEntity(customerGroup);

    // When
    CustomerGroupDTO actual = customerGroupService.find(customerType);

    // Then
    assertEquals(customerGroupDTO.customerType(), actual.customerType());
    assertEquals(customerGroupDTO.parameter(), actual.parameter());
  }

  @DisplayName("[find] 고객 유형을 전달하지 않는 경우 예외 발생")
  @Test
  void givenNothing_whenFind_thenOccursStoreException() {
    // Given

    // When & Then
    StoreException exception =
        assertThrows(StoreException.class, () -> customerGroupService.find(null));
    assertEquals(NO_MATCHING_GROUP.getMessage(), exception.getMessage());
  }

  @DisplayName("[findAll] 모든 고객 그룹 요청시 고객 그룹 DTO 를 배열로 반환")
  @Test
  void givenCustomerTypeAndParameter_thenSetGroupDTOParameter() {
    // Given
    CustomerGroup[] customerGroups =
        Arrays.stream(CustomerType.values())
            .map(type -> new CustomerGroup(type, null))
            .toArray(CustomerGroup[]::new);
    CustomerGroupDTO[] customerGroupDTOs =
        Arrays.stream(customerGroups)
            .map(CustomerGroupDTO::fromEntity)
            .toArray(CustomerGroupDTO[]::new);

    // When
    CustomerGroupDTO[] actual = customerGroupService.findAll();

    // Then
    assertArrayEquals(customerGroupDTOs, actual);
  }
}
