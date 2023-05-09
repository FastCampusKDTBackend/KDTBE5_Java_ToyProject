package me.smartstore.customer;

import me.smartstore.exception.DuplicateValueException;
import me.smartstore.group.Group;

import java.util.Objects;

public class Customer {
    private String customerName;
    private String customerId;
    private int useHours;
    private int customerPay;

    private Group customerGroup;

    public Customer() {}

    public Customer(String customerName, String customerId, int useHours, int customerPay) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.useHours = useHours;
        this.customerPay = customerPay;
        this.customerGroup = null;
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
        if (Customers.getInstance().checkId(customerId)) throw new DuplicateValueException();
        this.customerId = customerId;
    }

    public int getUseHours() {
        return useHours;
    }

    public void setUseHours(int useHours) {
        this.useHours = useHours;
    }

    public int getCustomerPay() {
        return customerPay;
    }

    public void setCustomerPay(int customerPay) {
        this.customerPay = customerPay;
    }

    public Group getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(Group customerGroup) {
        this.customerGroup = customerGroup;
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
                ", useHours=" + useHours +
                ", customerPay=" + customerPay +
                ", customerGroup=" + customerGroup.getGroupType() +
                '}';
    }
}
