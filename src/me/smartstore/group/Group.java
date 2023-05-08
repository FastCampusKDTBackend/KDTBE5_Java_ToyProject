package me.smartstore.group;

import java.util.Objects;

public class Group {

    private GroupType groupType;
    private int minHours;
    private int minPay;

    public Group(GroupType groupType, int minHours, int minPay) {
        this.groupType = groupType;
        this.minHours = minHours;
        this.minPay = minPay;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public int getMinHours() {
        return minHours;
    }

    public void setMinHours(int minHours) {
        this.minHours = minHours;
    }

    public int getMinPay() {
        return minPay;
    }

    public void setMinPay(int minPay) {
        this.minPay = minPay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return minHours == group.minHours && minPay == group.minPay && groupType == group.groupType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupType, minHours, minPay);
    }

    @Override
    public String toString() {
        return "GroupType: " + groupType + "\n" +
                "minimumSpentTime: " + minHours + ", minimumTotalPay: " + minPay;
    }
}
