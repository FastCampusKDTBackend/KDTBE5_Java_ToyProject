package me.smartstore.group;

public class GroupGeneral extends Group {


    public GroupGeneral() {
        this.groupName = "General";
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


}
