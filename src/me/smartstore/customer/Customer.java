package me.smartstore.customer;

import me.smartstore.group.Group;

import java.util.Objects;

public class Customer {

    private String customerName;
    private String customerId;
    private int customerTotalTime;
    private int customerTotalPay;
    private Group group;

    public Customer(){

    }

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public Customer(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public Customer(String customerName, String customerId, int customerTotalTime, int customerTotalPay) {
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

    public int getCustomerTotalTime() {
        return customerTotalTime;
    }

    public void setCustomerTotalTime(int customerTotalTime) {
        this.customerTotalTime = customerTotalTime;
    }

    public int getCustomerTotalPay() {
        return customerTotalPay;
    }

    public void setCustomerTotalPay(int customerTotalPay) {
        this.customerTotalPay = customerTotalPay;
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
                "customerName='" + customerName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerTotalTime=" + customerTotalTime +
                ", customerTotalPay=" + customerTotalPay +
                ", group=" + group +
                '}';
    }
}
