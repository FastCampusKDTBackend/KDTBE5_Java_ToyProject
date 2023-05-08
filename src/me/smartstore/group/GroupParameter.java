package me.smartstore.group;

public class GroupParameter {
    private Integer minSpentHours;
    private Integer minTotalAmountPaid;

    public GroupParameter() {}

    public void setMinSpentHours(Integer minSpentHours) {
        this.minSpentHours = minSpentHours;
    }

    public void setMinTotalAmountPaid(Integer minTotalAmountPaid) {
        this.minTotalAmountPaid = minTotalAmountPaid;
    }

    @Override
    public String toString() {
        return String.format("Parameter{minSpentHours=%d, minTotalAmountPaid=%d}",
                minSpentHours, minTotalAmountPaid);
    }
}
