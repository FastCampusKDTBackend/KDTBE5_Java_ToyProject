package me.smartstore.customer;

import me.smartstore.group.GroupType;

import java.util.Objects;

public class Customer implements Comparable<Customer> {
    private int cusNo;
    private String cusId;
    private String cusName;
    private Integer cusTotalTime;
    private Integer cusTotalPay;
    private GroupType group; // 현재 분류 기준에 의해 각 고객을 분류된 결과

    private static int seqNum = 1;

    public Customer() {
        //초기 값 설정
        cusNo = seqNum;
        cusId = null;
        cusName = null;
        cusTotalTime = 0;
        cusTotalPay = 0;
        group = GroupType.NONE;
        seqNum++;
    }

    public Customer(String cusName, String cusId, Integer cusTotalTime, Integer cusTotalPay) {
        this();
        this.cusName = cusName;
        this.cusId = cusId;
        this.cusTotalTime = cusTotalTime;
        this.cusTotalPay = cusTotalPay;
    }

    public int getCusNo() {
        return cusNo;
    }

    public void setCusNo(int cusNo) {
        this.cusNo = cusNo;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public Integer getCusTotalTime() {
        return cusTotalTime;
    }

    public void setCusTotalTime(Integer cusTotalTime) {
        this.cusTotalTime = cusTotalTime;
    }

    public Integer getCusTotalPay() {
        return cusTotalPay;
    }

    public void setCusTotalPay(Integer cusTotalPay) {
        this.cusTotalPay = cusTotalPay;
    }

    public GroupType getGroup() {
        return group;
    }

    public void setGroup(GroupType group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(cusId, customer.cusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cusId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusName='" + cusName + '\'' +
                ", cusId='" + cusId + '\'' +
                ", cusTotalTime=" + cusTotalTime +
                ", cusTotalPay=" + cusTotalPay +
                ", group=" + group +
                '}';
    }

    @Override
    public int compareTo(Customer o) {
        return o.getCusNo();
    }
}
