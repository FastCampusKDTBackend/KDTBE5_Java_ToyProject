package customer;

import group.Group;

import java.util.Objects;

public class Customer {
    private String cusName;
    private String cusId;
    private int cusTotalTime;
    private int cusTotalPay;
    private Group group;

    public Customer() {
    }

    public Customer(String cusId) {
        this.cusId = cusId;
    }

    public Customer(String cusName, String cusId) {
        this.cusName = cusName;
        this.cusId = cusId;
    }

    public Customer(String cusName, String cusId, int cusTotalTime, int cusTotalPay) {
        this.cusName = cusName;
        this.cusId = cusId;
        this.cusTotalTime = cusTotalTime;
        this.cusTotalPay = cusTotalPay;
    }

    public Customer(String cusName, String cusId, int cusTotalTime, int cusTotalPay, Group group) {
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

    public int getCusTotalTime() {
        return cusTotalTime;
    }

    public void setCusTotalTime(int cusTotalTime) {
        this.cusTotalTime = cusTotalTime;
    }

    public int getCusTotalPay() {
        return cusTotalPay;
    }

    public void setCusTotalPay(int cusTotalPay) {
        this.cusTotalPay = cusTotalPay;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void addPurchase(int pay) {
        cusTotalPay += pay;
    }

    public void addTime(int time) {
        cusTotalTime += time;
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
}
