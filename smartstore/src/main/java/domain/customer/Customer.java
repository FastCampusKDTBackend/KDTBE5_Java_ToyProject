package domain.customer;

import domain.group.GroupType;
import domain.group.Parameter;
import util.random.RandomStringGenerator;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Customer {
    private String userId;
    private String name;
    private int spentTime;
    private int totalPay;
    private GroupType groupType;

    public Customer(){
        this.userId = UUID.randomUUID().toString().split("-")[0];
        this.name = RandomStringGenerator.getRandomAlphabetString();
        this.spentTime = 0;
        this.totalPay = 0;
        this.groupType = GroupType.NONE;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public int getTotalPay() {
        return totalPay;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateGroup() {
        groupType = GroupType.findGroupType(spentTime, totalPay);
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
        updateGroup();
    }

    public void setTotalPay(int totalPay) {
        this.totalPay = totalPay;
        updateGroup();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", spentTime=" + spentTime +
                ", totalPay=" + totalPay +
                ", groupType=" + groupType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return spentTime == customer.spentTime && totalPay == customer.totalPay && Objects.equals(userId, customer.userId) && Objects.equals(name, customer.name) && groupType == customer.groupType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, spentTime, totalPay, groupType);
    }
}
