package me.smartstore.group;

public class GroupVIP extends Group{


    public GroupVIP() {
        this.groupName = "VIP";
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
