package me.smartstore.group;

import java.util.Objects;

public class Group {
    private GroupConditions groupConditions;
    private GroupType groupType;

    public Group() {
    }

    public Group(GroupConditions groupConditions, GroupType groupType) {
        this.groupConditions = groupConditions;
        this.groupType = groupType;
    }

    public GroupConditions getParameter() {
        return groupConditions;
    }

    public void setParameter(GroupConditions groupConditions) {
        this.groupConditions = groupConditions;
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
        return Objects.equals(groupConditions, group.groupConditions) && groupType == group.groupType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupConditions, groupType);
    }

    @Override
    public String toString() {
        return "GroupType=" + groupType +"\n" +
                "Parameter=" + groupConditions;
    }
}
