package me.smartstore.customer;

import me.smartstore.group.Group;

import java.util.Objects;

public class Customer implements Comparable<Customer> {
    private String customerName;
    private String customerId;
    private Integer totalTime;
    private Integer totalPay;
    private Group group; // 현재 분류 기준에 의해 각 고객을 분류된 결과

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Customer() {
    }

    public Customer(String customerId) {
        this.customerId = customerId;
    }

    public Customer(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public Customer(String customerName, String customerId, Integer totalTime, Integer totalPay) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.totalTime = totalTime;
        this.totalPay = totalPay;
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

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Integer totalPay) {
        this.totalPay = totalPay;
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
                    ", spentTime=" + totalTime +
                    ", totalPay=" + totalPay +
                    group.toStringForCustomer() + '}';
    }
    @Override
    public int compareTo(Customer o) {
        return 0;
    }


}
