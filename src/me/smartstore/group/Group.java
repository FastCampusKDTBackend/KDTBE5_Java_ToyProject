package me.smartstore.group;

import me.smartstore.customer.Customers;
import me.smartstore.group.GroupType;
import me.smartstore.group.Parameter;

import java.util.Objects;

public class Group {
    private GroupType groupType;
    private Parameter parameter;

    public Group() {
        this(null, null);
    }

    public Group(GroupType groupType, Parameter parameter) {
        this.groupType = groupType;
        this.parameter = parameter;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void getGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter param) {
        this.parameter = param;
    }

    public Customers getCustomers(Customers allCustomers) {
        return allCustomers.findCustomers(this.getGroupType());
    }

    public void update(GroupType groupType, Parameter parameter) {
        this.groupType = groupType;
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
        if (groupType == null) {
            return "No group.";
        } else if (parameter == null) {
            return "GroupType: " + groupType + "\nParameter: null";
        } else {
            return "GroupType: " + groupType + "\nParameter: " + parameter;
        }
    }
}