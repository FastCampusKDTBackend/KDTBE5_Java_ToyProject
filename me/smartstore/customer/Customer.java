package me.smartstore.customer;

import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Parameter;

public class Customer {
    private String cusName;
    private String cusId;
    private Integer cusTotalTime;
    private Integer cusTotalPay;
    private Group group;

    public Customer() {
        this.group = new Group(new Parameter(0, 0), GroupType.NONE);
    }

    public Customer(String cusId) {
        this.cusId = cusId;
    }

    public Customer(String cusName, String cusId) {
        this.cusName = cusName;
        this.cusId = cusId;
    }

    public Customer(String cusName, String cusId, Integer cusTotalTime, Integer cusTotalPay, Group group) {
        this.cusName = cusName;
        this.cusId = cusId;
        this.cusTotalTime = cusTotalTime;
        this.cusTotalPay = cusTotalPay;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (getCusName() != null ? !getCusName().equals(customer.getCusName()) : customer.getCusName() != null)
            return false;
        if (getCusId() != null ? !getCusId().equals(customer.getCusId()) : customer.getCusId() != null) return false;
        if (getCusTotalTime() != null ? !getCusTotalTime().equals(customer.getCusTotalTime()) : customer.getCusTotalTime() != null)
            return false;
        if (getCusTotalPay() != null ? !getCusTotalPay().equals(customer.getCusTotalPay()) : customer.getCusTotalPay() != null)
            return false;
        return getGroup() != null ? getGroup().equals(customer.getGroup()) : customer.getGroup() == null;
    }

    @Override
    public int hashCode() {
        int result = getCusName() != null ? getCusName().hashCode() : 0;
        result = 31 * result + (getCusId() != null ? getCusId().hashCode() : 0);
        result = 31 * result + (getCusTotalTime() != null ? getCusTotalTime().hashCode() : 0);
        result = 31 * result + (getCusTotalPay() != null ? getCusTotalPay().hashCode() : 0);
        result = 31 * result + (getGroup() != null ? getGroup().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusName='" + cusName + '\'' +
                ", cusId='" + cusId + '\'' +
                ", cusTotalTime=" + cusTotalTime +
                ", cusTotalPay=" + cusTotalPay +
                ", group=" + group.getGroupType() +
                '}';
    }
}
