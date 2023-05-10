package me.smartstore.group;

public class Group {
    private GroupType customerGroup;
    private Parameter parameter;

    public Group(GroupType customerGroup, Parameter parameter) {
        this.customerGroup = customerGroup;
        this.parameter = parameter;
    }

    public GroupType getCustomerGroup() {
        return customerGroup;
    }

    public Parameter getParameter() {
        return parameter;
    }
}
