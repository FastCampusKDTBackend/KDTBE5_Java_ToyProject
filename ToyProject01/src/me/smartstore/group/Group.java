package me.smartstore.group;

public abstract class Group {

    public String groupName;
    public double time;
    public double pay;

    abstract void setMinimumTime(double time);

    abstract void setMinimumPay(double pay);

    abstract double getMinimumTime();

    abstract double getMinimumPay();



}
