package me.smartstore.group;

public class Parameter {
    private int minimumHours;
    private int minimumTotalAmount;

    public Parameter(int minimumHours, int minimumTotalAmount) {
        this.minimumHours = minimumHours;
        this.minimumTotalAmount = minimumTotalAmount;
    }

    public int getMinimumHours() {
        return minimumHours;
    }

    public void setMinimumHours(int minimumHours) {
        this.minimumHours = minimumHours;
    }

    public int getMinimumTotalAmount() {
        return minimumTotalAmount;
    }

    public void setMinimumTotalAmount(int minimumTotalAmount) {
        this.minimumTotalAmount = minimumTotalAmount;
    }
}
