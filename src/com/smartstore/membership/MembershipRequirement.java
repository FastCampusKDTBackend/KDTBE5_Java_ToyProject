package com.smartstore.membership;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MembershipRequirement)) return false;
        MembershipRequirement that = (MembershipRequirement) o;
        return minUsageTime == that.minUsageTime && minPaymentAmount == that.minPaymentAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minUsageTime, minPaymentAmount);
    }
}
