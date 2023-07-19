package me.smartstore.group;

public class GroupGeneral extends Group {


    public GroupGeneral() {
        this.groupType = "General";
    }

    @Override
    void setMinimumTime(double time) {
        this.time = time;
    }

    @Override
    void setMinimumPay(double pay) {
        this.pay = pay;
    }

    @Override
    double getMinimumTime() {
        return this.time;
    }

    @Override
    double getMinimumPay() {
        return this.pay;
    }

    @Override
    public String toString() {
        return "parameter{" +
                "minimumSpentTime=" + time +
                ", minimumTotalPay=" + pay +
                '}';
    }
}
