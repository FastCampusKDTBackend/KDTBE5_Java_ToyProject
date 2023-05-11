package me.smartstore.customer;

import me.smartstore.group.Group;

import java.util.Objects;

public class Customer {
    private String customerName;
    private String customerId;
    private Integer customerTotalTime;
    private Integer customerTotalPay;
    private Group group;

    public Customer() {
    }

    public Customer(String customerName, String customerId, Integer customerTotalTime, Integer customerTotalPay) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.customerTotalTime = customerTotalTime;
        this.customerTotalPay = customerTotalPay;
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

    public Integer getCustomerTotalTime() {
        return customerTotalTime;
    }

    public void setCustomerTotalTime(Integer cusTotalTime) {
        this.customerTotalTime = cusTotalTime;
    }

    public Integer getCustomerTotalPay() {
        return customerTotalPay;
    }

    public void setCustomerTotalPay(Integer cusTotalPay) {
        this.customerTotalPay = cusTotalPay;
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
        return Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId='" + customerId + '\'' +
                ", name='" + customerName + '\'' +
                ", spentTime=" + customerTotalTime +
                ", totalPay=" + customerTotalPay +
                ", group=" + group;
    }
}