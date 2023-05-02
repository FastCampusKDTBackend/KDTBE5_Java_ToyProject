package smartstore.group;

public class Group {

    private Parameter parameter;
    private GroupType groupType;

    public Group() {
    }

    public Group(Parameter parameter, GroupType groupType) {
        this.parameter = parameter;
        this.groupType = groupType;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (getParameter() != null ? !getParameter().equals(group.getParameter()) : group.getParameter() != null)
            return false;
        return getGroupType() == group.getGroupType();
    }

    @Override
    public int hashCode() {
        int result = getParameter() != null ? getParameter().hashCode() : 0;
        result = 31 * result + (getGroupType() != null ? getGroupType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "parameter=" + parameter +
                ", groupType=" + groupType +
                '}';
    }
}
