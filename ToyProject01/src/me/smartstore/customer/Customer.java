package me.smartstore.customer;

import me.smartstore.group.Group;

import java.util.Objects;

public class Customer {
    private String customerName;
    private String customerId;
    private String customerShoppingTime;
    private Group customerGroup;

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

    public String getCustomerShoppingTime() {
        return customerShoppingTime;
    }

    public void setCustomerShoppingTime(String customerShoppingTime) {
        this.customerShoppingTime = customerShoppingTime;
    }

    public Group getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(Group customerGroup) {
        this.customerGroup = customerGroup;
    }


}
