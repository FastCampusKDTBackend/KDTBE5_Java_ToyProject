package com.smartstore.customer;

import com.smartstore.membership.MembershipType;

public class Customer {
    private int customerId;
    private String customerName;
    private MembershipType membership;



    //TODO: 2023-04-30  constructor to builder
    Customer(int customerId, String customerName, MembershipType membership){
        this.customerId = customerId;
        this.customerName = customerName;
        this.membership = membership;
    }

    public void updateUser(int customerId){

    }

    public void deleteUser(int customerId){

    }
}
