package me.smartstore.core.domain;

import me.smartstore.enums.CustomerType;

public record CustomerDTO(
    Long id,
    String name,
    String userId,
    Integer spentTime,
    Integer payAmount,
    CustomerType customerType) {

  public static CustomerDTO from(Customer customer) {
    return new CustomerDTO(
        customer.getId(),
        customer.getName(),
        customer.getName(),
        customer.getSpentTime(),
        customer.getPayAmount(),
        customer.getCustomerType());
  }

  public static CustomerDTO of(
      String customerName,
      String customerID,
      Integer customerSpentTime,
      Integer customerPayAmount) {
    return new CustomerDTO(
        null, customerName, customerID, customerSpentTime, customerPayAmount, null);
  }

  @Override
  public String toString() {
    // Customer{userId='TEST123', name='TEST', spentTime=null, payAmount=null, group=null}
    return "Customer{"
        + "userId='"
        + userId
        + '\''
        + ", name='"
        + name
        + '\''
        + ", spentTime="
        + spentTime
        + ", payAmount="
        + payAmount
        + ", customerType="
        + customerType
        + '}';
  }
}
