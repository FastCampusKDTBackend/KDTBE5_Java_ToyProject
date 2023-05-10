package com.smartstore.customer;

import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;
import com.smartstore.util.Map;

import java.util.Arrays;

public class Customer {
    private int customerId;
    private String customerName;
    private MembershipType membership;

    public void setMembership(int usageTime, int paymentAmount) {
        Memberships memberships = Memberships.getInstance();
        Map<MembershipType, MembershipRequirement> membershipList = memberships.getMembershipList();
        for(MembershipType membershipType :MembershipType.values()){
            if(membershipList.get(membershipType).getMinUsageTime() > usageTime && membershipList.get(membershipType).getMinPaymentAmount() > paymentAmount){
                this.membership = membershipType;
                return;
            }
        }
        this.membership = null;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


}
