package customer;

import group.Group;

import java.util.Objects;

public class Customer {
    private String customerName;
    private String customerID;
    private Integer customerTotalTime;
    private Integer customerTotalPay;
    private Group group;

    public Customer() {}

    public Customer(String customerName, String customerID, Integer totalTime, Integer totalPay) {
        this.customerName = customerName;
        this.customerID = customerID;
        this.customerTotalTime = totalTime;
        this.customerTotalPay = totalPay;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Integer getTotalTime() {
        return customerTotalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.customerTotalTime = totalTime;
    }

    public Integer getTotalPay() {
        return customerTotalPay;
    }

    public void setTotalPay(Integer totalPay) {
        this.customerTotalPay = totalPay;
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
        return customerID.equals(customer.customerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "UserID='" + customerID +
                ", Name='" + customerName +
                ", SpentTime=" + customerTotalTime +
                ", totalPay=" + customerTotalPay +
                ", group= " + group +
                '}';

    }
}
