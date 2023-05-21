package me.smartstore.groups;

import java.util.Objects;
import me.smartstore.customers.Customers;

public class Group {
    private GroupType type;
    private Parameter param;

    public Group() {
        this(null, null);
    }

    public Group(GroupType type, Parameter param) {
        this.type = type;
        this.param = param;
    }

    public GroupType getType() {
        return type;
    }

    public void setType(GroupType type) {
        this.type = type;
    }

    public Parameter getParam() {
        return param;
    }

    public void setParam(Parameter param) {
        this.param = param;
    }

    public Customers getCustomers(Customers allCustomers) {
        return allCustomers.findCustomers(this);
    }

    public void update(GroupType type, Parameter param) {
        this.type = type;
        this.param = param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Group group = (Group) o;
        return type == group.type && Objects.equals(param, group.param);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, param);
    }

    @Override
    public String toString() {
        if (type == null)
            return "그룹이 없어요.";
        return (param == null) ? ("GroupType: " + type + "\nParameter: null")
                : ("GroupType: " + type + "\nParameter: " + param);
    }
}
