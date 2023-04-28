package me.smartstore.domain.group;

import java.util.Objects;

public class Parameter {
    private Integer minUsageTime;
    private Integer minPurchaseAmount;

    public Parameter() {}

    public Parameter(Integer minUsageTime, Integer minPurchaseAmount) {
        this.minUsageTime = minUsageTime;
        this.minPurchaseAmount = minPurchaseAmount;
    }

    public Integer getMinUsageTime() {
        return minUsageTime;
    }

    public Integer getMinPurchaseAmount() {
        return minPurchaseAmount;
    }

    public void setMinUsageTime(Integer minUsageTime) {
        this.minUsageTime = minUsageTime;
    }

    public void setMinPurchaseAmount(Integer minPurchaseAmount) {
        this.minPurchaseAmount = minPurchaseAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parameter parameter = (Parameter) o;

        if (!Objects.equals(minUsageTime, parameter.minUsageTime))
            return false;
        return Objects.equals(minPurchaseAmount, parameter.minPurchaseAmount);
    }

    @Override
    public int hashCode() {
        int result = minUsageTime != null ? minUsageTime.hashCode() : 0;
        result = 31 * result + (minPurchaseAmount != null ? minPurchaseAmount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "minUsageTime=" + minUsageTime +
                ", minPurchaseAmount=" + minPurchaseAmount +
                '}';
    }
}
