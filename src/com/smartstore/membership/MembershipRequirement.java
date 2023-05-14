package com.smartstore.membership;

public class MembershipRequirement {
    private final int minUsageTime;
    private final int minPaymentAmount;

    public MembershipRequirement(int minUsageTime, int minPaymentAmount) {
        this.minUsageTime = minUsageTime;
        this.minPaymentAmount = minPaymentAmount;
    }

    //for unregister customer
    public MembershipRequirement() {
        this.minUsageTime = 0;
        this.minPaymentAmount = 0;
    }

    public int getMinUsageTime() {
        return minUsageTime;
    }

    public int getMinPaymentAmount() {
        return minPaymentAmount;
    }

}
