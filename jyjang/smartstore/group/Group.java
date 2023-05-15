package jyjang.smartstore.group;

import java.util.Objects;

public class Group {

    private Parameter parameter; // 분류기준
    private GroupType groupType; // 그룹 타입

    public Group() {
    }

    public Group(GroupType groupType, Parameter parameter) {
        this.groupType = groupType;
        this.parameter = parameter;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupType == group.groupType && Objects.equals(parameter, group.parameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupType, parameter);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupType=" + groupType +
                ", parameter=" + parameter +
                '}';
    }
}
