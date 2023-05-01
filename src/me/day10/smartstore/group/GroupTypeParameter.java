package me.day10.smartstore.group;

public class GroupTypeParameter {
    int minSpentTime;
    int minTotalPaid;

    public GroupTypeParameter(int minSpentTime, int minTotalPaid) {
        this.minSpentTime = minSpentTime;
        this.minTotalPaid = minTotalPaid;
    }

    @Override
    public String toString() {
        return String.format("Parameter{minSpentTime=%d, minTotalPaid=%d}",
                minSpentTime, minTotalPaid);
    }
}
