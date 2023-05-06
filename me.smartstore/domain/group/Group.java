package domain.group;

import java.util.Objects;

public class Group {

    private GroupType groupType; // 그룹 타입
    private Integer minTime;
    private Integer minPay;

    public Group() {
        minTime = 0;
        minPay = 0;
    }

    public Group(GroupType groupType) {
        this();
        this.groupType = groupType;
    }

    public Group(Integer minTime, Integer minPay, GroupType groupType) {
        this.minTime = minTime;
        this.minPay = minPay;
        this.groupType = groupType;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public Integer getMinPay() {
        return minPay;
    }

    public void setMinPay(Integer minPay) {
        this.minPay = minPay;
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
        Group group = (Group) o;
        return groupType == group.groupType && Objects.equals(minTime, group.minTime) && Objects.equals(minPay, group.minPay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupType, minTime, minPay);
    }

    @Override
    public String toString() {
        return groupType + " Group Info [" +
                "minTime = " + minTime +
                ", minPay = " + minPay + "]";
    }
}
