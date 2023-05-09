package me.smartstore.group;

public class GroupParameter {
    private Integer minSpentHours;
    private Integer minTotalPaidAmount;

    public GroupParameter() {}

    public void setMinSpentHours(Integer minSpentHours) {
        this.minSpentHours = minSpentHours;
    }

    public void setMinTotalPaidAmount(Integer minTotalPaidAmount) {
        this.minTotalPaidAmount = minTotalPaidAmount;
    }

    @Override
    public String toString() {
        return String.format("Parameter{minSpentHours=%d, minTotalPaidAmount=%d}",
                minSpentHours, minTotalPaidAmount);
    }
}
