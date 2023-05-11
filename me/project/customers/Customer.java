package me.project.customers;

import me.project.groups.Group;
import java.util.Objects;

// 이 클래스는 회원 추가 하는 클래스
public class Customer implements Comparable<Customer>{
    private static int serialNum = 0; //static, 객체가 하나 생성될 때 마다 1씩 증가, 모든 객체가 공유
    private String customerNum; //객체 고유의 값
    private String customerName; //고객 이름
    private String customerID; //고객 아이디
    private int spentTime; //고객이 스토어를 이용한 시간
    private int totalPay; //고객이 스토어에서 이용한 금액
    private Group group;

    public Customer() {
        serialNum++;
        this.customerNum = String.format("%04d",serialNum);
    }

    public Customer(String customerName, String customerID, int spentTime, int totalPay) {
        serialNum++;
        this.customerNum = String.format("%04d",serialNum);
        this.customerName = customerName;
        this.customerID = customerID;
        this.spentTime = spentTime;
        this.totalPay = totalPay;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
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

    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

    public int getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(int totalPay) {
        this.totalPay = totalPay;
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
        return spentTime == customer.spentTime && totalPay == customer.totalPay && Objects.equals(customerName, customer.customerName) && Objects.equals(customerID, customer.customerID) && Objects.equals(group, customer.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, customerID, spentTime, totalPay, group);
    }

    @Override
    public String toString() {
        return "고객번호 : " + customerNum + "\n" +
                "고객 이름 : " + customerName + "\n" +
                "고객 ID : " + customerID + "\n" +
                "스토어 이용시간 : " + spentTime + "\n" +
                "총 사용금액 : " + totalPay ;
    }

    @Override
    public int compareTo(Customer o) {
        if (this.customerName != null && o.customerName != null) {
            if (this.customerName.compareTo(o.customerName) < 0) {
                return -1;
            }
            else if (this.customerName.compareTo(o.customerName) == 0) {
                return 0;
            }
            else {
                return 1;
            }
        }
        else
            return 0;
    }
}