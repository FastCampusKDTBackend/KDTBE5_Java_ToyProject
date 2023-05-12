package me.smartstore.group;

public class GroupParameter {
    private Integer minSpentHours;
    private Integer minTotalPaidAmount;

    public GroupParameter() {}

    public GroupParameter(Integer minSpentHours, Integer minTotalPaidAmount) {
        this.minSpentHours = minSpentHours;
        this.minTotalPaidAmount = minTotalPaidAmount;
    }

    public void setMinSpentHours(Integer minSpentHours) {
        this.minSpentHours = minSpentHours;
    }

    public void setMinTotalPaidAmount(Integer minTotalPaidAmount) {
        this.minTotalPaidAmount = minTotalPaidAmount;
    }

    public Integer getMinSpentHours() { return minSpentHours; }

    public Integer getMinTotalPaidAmount() { return minTotalPaidAmount; }

    @Override
    public String toString() {
        return String.format("Parameter{minSpentHours=%d, minTotalPaidAmount=%d}",
                minSpentHours, minTotalPaidAmount);
    }
}
