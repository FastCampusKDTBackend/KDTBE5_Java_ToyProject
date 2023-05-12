package me.smartstore.group;

import java.util.Objects;

public class GroupConditions {
    private Integer minTime;
    private Integer minPay;

    public GroupConditions() {
    }

    public GroupConditions(Integer minTime, Integer minPay) {
        this.minTime = minTime;
        this.minPay = minPay;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public Integer getMinPay() {
        return minPay;
    }

    public void setMinPay(Integer minPay) {
        this.minPay = minPay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupConditions groupConditions = (GroupConditions) o;
        return Objects.equals(minTime, groupConditions.minTime) && Objects.equals(minPay, groupConditions.minPay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minTime, minPay);
    }

    @Override
    public String toString() {
        return "GroupConditions{" +
                "minTime=" + minTime +
                ", minPay=" + minPay +
                '}';
    }
}
