package me.smartstore.utils;

import me.smartstore.enums.CustomerType;
import me.smartstore.enums.SortOrder;
import me.smartstore.exceptions.StoreException;

import static me.smartstore.exceptions.StoreErrorCode.INVALID_FORMAT;

/**
 * 스마트스토어에 필요한 부가 기능 제공 유틸리티
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public class StoreUtility {
  public static CustomerType convertInputStrToCustomerType(String input) throws StoreException {
    try {
      return CustomerType.valueOf(input).replaceFullName();
    } catch (IllegalArgumentException e) {
      throw new StoreException(INVALID_FORMAT);
    }
  }

  public static SortOrder convertInputStrToSortOrder(String input) throws StoreException {
    try {
      return SortOrder.valueOf(input.toUpperCase()).replaceAbbreviation();
    } catch (IllegalArgumentException e) {
      throw new StoreException(INVALID_FORMAT);
    }
  }
}
