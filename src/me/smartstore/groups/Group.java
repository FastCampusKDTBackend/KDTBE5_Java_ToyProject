package me.smartstore.groups;

import java.util.Objects;

public class Group {

    // 기준치를 가지는 Parameter
    // 기준치를 GroupType에 따라 구분
    private Parameter parmeter;
    private GroupType groupType;

    public Group() {
    }

    public Group(Parameter parmeter, GroupType groupType) {
        this.parmeter = parmeter;
        this.groupType = groupType;
    }

    public Parameter getParmeter() {
        return parmeter;
    }

    public void setParmeter(Parameter parmeter) {
        this.parmeter = parmeter;
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
        return Objects.equals(parmeter, group.parmeter) && groupType == group.groupType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parmeter, groupType);
    }

    @Override
    public String toString() {
        return "Group{" +
                "parmeter=" + parmeter +
                ", groupType=" + groupType +
                '}';
    }
}
