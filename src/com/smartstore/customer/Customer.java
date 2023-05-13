package com.smartstore.customer;

import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;
import com.smartstore.util.Map;

public class Customer {
    private String customerId;
    private String customerName;

    private int usageTime;
    private int paymentAmount;
    private MembershipType membership;

    public Customer(String customerName, String  customerId, int usageTime, int paymentAmount) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.usageTime = usageTime;
        this.paymentAmount = paymentAmount;
        this.membership = setMembership(usageTime, paymentAmount);
    }

    public MembershipType setMembership(int usageTime, int paymentAmount) {
        Memberships memberships = Memberships.getInstance();
        MembershipType membership = null;
        Map<MembershipType, MembershipRequirement> membershipList = memberships.getMembershipList();
        for(MembershipType membershipType :MembershipType.values()){
            try {
                if(membershipList.get(membershipType).getMinUsageTime() > usageTime && membershipList.get(membershipType).getMinPaymentAmount() > paymentAmount){
                    membership = membershipType;
                }
            }catch (NullPointerException e){
                //Set General(lowers Membership) is not Set yet
                //Set Membership as null
                membership = null;
            }

        }
        return membership;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


}
