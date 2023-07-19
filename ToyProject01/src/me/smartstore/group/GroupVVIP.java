package me.smartstore.group;

public class GroupVVIP extends Group{


    public GroupVVIP() {
        this.groupType = "VVIP";
    }

    @Override
    void setMinimumTime(double time) {

    }

    @Override
    void setMinimumPay(double pay) {

    }

    @Override
    double getMinimumTime() {
        return 0;
    }

    @Override
    double getMinimumPay() {
        return 0;
    }


}
