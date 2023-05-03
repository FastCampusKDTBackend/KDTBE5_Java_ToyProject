package Customer;

import Group.GroupType;

import java.util.Objects;

public class Customer {
    private String ctmId;
    private String ctmName;
    private Integer totalTime;
    private Integer totalPay;
    private GroupType groupType;

    public Customer() {

    }

    public Customer(String ctmId, String ctmName) {
        this.ctmId = ctmId;
        this.ctmName = ctmName;
    }

    public Customer(String ctmId, String ctmName, Integer totalTime, Integer totalPay) {
        this.ctmId = ctmId;
        this.ctmName = ctmName;
        this.totalTime = totalTime;
        this.totalPay = totalPay;
    }

    public Customer(String ctmId, String ctmName, Integer totalTime, Integer totalPay, GroupType groupType) {
        this.ctmId = ctmId;
        this.ctmName = ctmName;
        this.totalTime = totalTime;
        this.totalPay = totalPay;
        this.groupType = groupType;
    }

    public String getCtmId() {
        return ctmId;
    }

    public void setCtmId(String ctmId) {
        this.ctmId = ctmId;
    }

    public String getCtmName() {
        return ctmName;
    }

    public void setCtmName(String ctmName) {
        this.ctmName = ctmName;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Integer totalPay) {
        this.totalPay = totalPay;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(ctmId, customer.ctmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ctmId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userId='" + ctmId + '\'' +
                ", name='" + ctmName + '\'' +
                ", spentTime=" + totalTime +
                ", totalPay=" + totalPay +
                ", group=" + groupType +
                '}';
    }
}
