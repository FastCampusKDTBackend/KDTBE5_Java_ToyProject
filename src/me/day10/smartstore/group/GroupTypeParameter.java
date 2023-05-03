package me.day10.smartstore.group;

public class GroupTypeParameter {
    private Integer minSpentTime;
    private Integer minTotalPaid;

    public GroupTypeParameter() {}

    public void setMinSpentTime(Integer minSpentTime) {
        this.minSpentTime = minSpentTime;
    }

    public void setMinTotalPaid(Integer minTotalPaid) {
        this.minTotalPaid = minTotalPaid;
    }

    @Override
    public String toString() {
        return String.format("Parameter{minSpentTime=%d, minTotalPaid=%d}",
                minSpentTime, minTotalPaid);
    }
}
