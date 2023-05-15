package com.smartstore.customer;

import com.smartstore.membership.MembershipRequirement;
import com.smartstore.membership.MembershipType;
import com.smartstore.membership.Memberships;
import com.smartstore.util.CustomEnumMap;

public class Customer {
    private String customerId;
    private String customerName;

    private int usageTime;
    private int paymentAmount;
    private MembershipType membership;

    public Customer() {
    }

    public Customer(String customerName, String  customerId, int usageTime, int paymentAmount) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.usageTime = usageTime;
        this.paymentAmount = paymentAmount;
        setMembership(usageTime, paymentAmount);
    }

    public void setMembership(int usageTime, int paymentAmount) {
        Memberships memberships = Memberships.getInstance();
        MembershipType membership = MembershipType.NONE;
        //Map is CustomMap
        CustomEnumMap<MembershipType, MembershipRequirement> membershipList = memberships.getMembershipList();
        int currentUsageTime;
        int currentPaymentTime;
        boolean isUsageLowerThenCurrent;
        boolean isPaymentLowerThenCurrent;
        for(MembershipType membershipType : MembershipType.values()){
            try {
                currentUsageTime = membershipType == MembershipType.NONE ? 0 : membershipList.get(membershipType).getMinUsageTime();
                currentPaymentTime = membershipType == MembershipType.NONE ? 0 : membershipList.get(membershipType).getMinPaymentAmount();

                isUsageLowerThenCurrent = usageTime < currentUsageTime;
                isPaymentLowerThenCurrent = paymentAmount < currentPaymentTime;

                if(!isUsageLowerThenCurrent){
                    if (!isPaymentLowerThenCurrent){
                        membership = membershipType;
                    } else{
                        break;
                    }
                } else {
                    break;
                }
            }catch (NullPointerException e){
                //Set General(lowers Membership) is not Set yet
                //Set Membership as null
                membership = MembershipType.NONE;
            }

        }
        this.membership = membership;
    }

    public void updateMembership(){
        setMembership(usageTime, paymentAmount);
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getUsageTime() {
        return usageTime;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public MembershipType getMembership() {
        return membership;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setUsageTime(int usageTime) {
        this.usageTime = usageTime;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    @Override
    public String toString() {
        return "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", usageTime=" + usageTime +
                ", paymentAmount=" + paymentAmount +
                ", membership=" + membership +
                "\n";
    }
}
