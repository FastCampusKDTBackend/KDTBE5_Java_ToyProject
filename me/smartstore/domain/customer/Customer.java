package me.smartstore.domain.customer;

import me.smartstore.domain.group.Group;

import java.util.Objects;

public class Customer {
    private String customerName;
    private String customerId;
    private Integer totalUsageTime;
    private Integer totalPurchaseAmount;
    private Group group;

    public Customer() {}

    public Customer(String customerId) {
        this.customerId = customerId;
    }

    public Customer(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public Customer(String customerName, String customerId, Integer totalUsageTime, Integer totalPurchaseAmount) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.totalUsageTime = totalUsageTime;
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getTotalUsageTime() {
        return totalUsageTime;
    }

    public void setTotalUsageTime(Integer totalUsageTime) {
        this.totalUsageTime = totalUsageTime;
    }

    public Integer getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public void setTotalPurchaseAmount(Integer totalPurchaseAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!Objects.equals(customerName, customer.customerName))
            return false;
        if (!Objects.equals(customerId, customer.customerId)) return false;
        if (!Objects.equals(totalUsageTime, customer.totalUsageTime))
            return false;
        if (!Objects.equals(totalPurchaseAmount, customer.totalPurchaseAmount))
            return false;
        return Objects.equals(group, customer.group);
    }

    @Override
    public int hashCode() {
        int result = customerName != null ? customerName.hashCode() : 0;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (totalUsageTime != null ? totalUsageTime.hashCode() : 0);
        result = 31 * result + (totalPurchaseAmount != null ? totalPurchaseAmount.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", totalUsageTime=" + totalUsageTime +
                ", totalPurchaseAmount=" + totalPurchaseAmount +
                ", group=" + group +
                '}';
    }
}
