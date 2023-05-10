package me.smartstore.core.domain;

import me.smartstore.enums.CustomerType;

/**
 * 고객 유형별 세부 기준 관리를 위한 그룹화 클래스
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 * @see CustomerType
 * @see Parameter
 */
public class CustomerGroup {
  private CustomerType customerType;
  private Parameter parameter;

  public CustomerGroup(CustomerType customerType, Parameter parameter) {
    this.customerType = customerType;
    this.parameter = parameter;
  }

  public CustomerType getCustomerType() {
    return customerType;
  }

  public Parameter getParameter() {
    return parameter;
  }

  public void setCustomerType(CustomerType customerType) {
    this.customerType = customerType;
  }

  public void setParameter(Parameter parameter) {
    if (this.parameter == null) {
      this.parameter = parameter;
    } else {
      if (parameter.getMinSpentTime() != null) {
        this.parameter.setMinSpentTime(parameter.getMinSpentTime());
      }
      if (parameter.getMinPayAmount() != null) {
        this.parameter.setMinPayAmount(parameter.getMinPayAmount());
      }
    }
  }

  public String groupTitle() {
    StringBuilder sb = new StringBuilder();
    sb.append("==============================\n");
    sb.append("Group: ").append(customerType);
    if (parameter == null) {
      sb.append(" ( Time : null, Pay Amount : null )\n");
    } else {
      sb.append(" ( Time :")
          .append(parameter.getMinSpentTime())
          .append(", Pay Amount :")
          .append(parameter.getMinPayAmount())
          .append(" )\n");
    }
    sb.append("==============================");

    return sb.toString();
  }

  @Override
  public String toString() {
    String parameterStr = parameter == null ? "null" : parameter.toString();
    return "GroupType: " + customerType + "\nParameter: " + parameterStr;
  }
}
