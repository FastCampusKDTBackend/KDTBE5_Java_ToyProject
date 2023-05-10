package me.smartstore.core.domain;

public class Parameter {
  private Integer minSpentTime;
  private Integer minPayAmount;

  public Parameter() {
    this.minSpentTime = null;
    this.minPayAmount = null;
  }

  public Parameter(Integer minSpentTime, Integer minPayAmount) {
    this.minSpentTime = minSpentTime;
    this.minPayAmount = minPayAmount;
  }

  public Integer getMinSpentTime() {
    return minSpentTime;
  }

  public void setMinSpentTime(Integer minSpentTime) {
    this.minSpentTime = minSpentTime;
  }

  public Integer getMinPayAmount() {
    return minPayAmount;
  }

  public void setMinPayAmount(Integer minPayAmount) {
    this.minPayAmount = minPayAmount;
  }

  @Override
  public String toString() {
    return "Parameter{"
        + "minimumSpentTime="
        + minSpentTime
        + ", minimumTotalPayment="
        + minPayAmount
        + '}';
  }
}
