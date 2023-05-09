package me.smartstore.customer;

import me.smartstore.group.GroupType;

import java.util.Objects;

public class Customer {
    private String name;
    private String id;
    private int hours;
    private int totalAmount;
    private GroupType group;

    public Customer(String name, String id, int hours, int totalAmount, GroupType group) {
        this.name = name;
        this.id = id;
        this.hours = hours;
        this.totalAmount = totalAmount;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public GroupType getGroup() {
        return group;
    }

    public void setGroup(GroupType group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return hours == customer.hours && totalAmount == customer.totalAmount && Objects.equals(name, customer.name) && Objects.equals(id, customer.id) && group == customer.group;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, hours, totalAmount, group);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", hours=" + hours +
                ", totalAmount=" + totalAmount +
                ", group=" + group +
                '}';
    }
}
