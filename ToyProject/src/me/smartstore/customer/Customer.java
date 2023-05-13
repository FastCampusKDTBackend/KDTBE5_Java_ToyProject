package me.smartstore.customer;

import me.smartstore.group.Group;
import me.smartstore.group.Groups;

public class Customer {
    private String customerName;
    private String customerId;
    private Integer customerTotalTime;
    private Integer customerTotalPay;
    private Group group; // 분류 기준에 따라 고객을 분류한 결과

    public Customer() {
    }

    public Customer(String customerId) {
        this.customerId = customerId;
    }

    public Customer(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public Customer(String customerName, String customerId, Integer customerTotalTime, Integer customerTotalPay) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.customerTotalTime = customerTotalTime;
        this.customerTotalPay = customerTotalPay;
    }

    public Customer(String customerName, String customerId, Integer customerTotalTime, Integer customerTotalPay, Group group) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.customerTotalTime = customerTotalTime;
        this.customerTotalPay = customerTotalPay;
    }

    public void getCustomerInfo() {

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

    public void setCustomerTotalTime(Integer customerTotalTime) {
        this.customerTotalTime = customerTotalTime;
    }

    public Integer getCustomerTotalPay() {
        return customerTotalPay;
    }

    public void setCustomerTotalPay(Integer customerTotalPay) {
        this.customerTotalPay = customerTotalPay;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
