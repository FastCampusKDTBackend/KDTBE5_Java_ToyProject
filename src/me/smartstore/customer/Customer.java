package me.smartstore.customer;

import me.smartstore.group.Group;
import java.util.Objects;


public class Customer implements Comparable<Customer> {
    private String userId;
    private String name;
    private Integer spentTime;
    private Integer totalPay;
    private Group group;

    public Customer() {
    }

    public Customer(String name, String userId, int spentTime, int totalPay) {
        this.name = name;
        this.userId = userId;
        this.spentTime = Integer.valueOf(spentTime);
        this.totalPay = Integer.valueOf(totalPay);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSpentTime() {
        return this.spentTime;
    }

    public void setSpentTime(Integer spentTime) {
        this.spentTime = spentTime;
    }

    public Integer getTotalPay() {
        return this.totalPay;
    }

    public void setTotalPay(Integer totalPay) {
        this.totalPay = totalPay;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int compareTo(Customer o) {
        if (this.name.compareTo(o.name) < 0)
            return -1;
        if (this.name.compareTo(o.name) == 0)
            return this.userId.compareTo(o.userId);
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
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
