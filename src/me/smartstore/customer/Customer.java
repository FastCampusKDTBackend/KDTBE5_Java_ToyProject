package me.smartstore.customer;


import me.smartstore.group.Group;

import java.util.Objects;

public class Customer {
    private String userId;
    private String name;
    private Integer spentTime;
    private Integer totalPay;
    private Group group;
    public Customer() {
    }

    public Customer(String userId) {
        this.userId = userId;
    }

    public Customer(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Customer(String userId, String name, Integer spentTime, Integer totalPay) {
        this.userId = userId;
        this.name = name;
        this.spentTime = spentTime;
        this.totalPay = totalPay;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(Integer spentTime) {
        this.spentTime = spentTime;
    }

    public Integer getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Integer totalPay) {
        this.totalPay = totalPay;
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
        return Objects.equals(userId, customer.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", spentTime=" + spentTime +
                ", totalPay=" + totalPay +
                ", group=" + group +
                '}';
    }
}
