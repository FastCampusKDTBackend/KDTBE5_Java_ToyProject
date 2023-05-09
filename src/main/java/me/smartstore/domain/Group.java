package me.smartstore.domain;

import me.smartstore.enums.GroupType;

public class Group {
  private GroupType groupType;
  private Parameter parameter;

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
    if (this.parameter == null) {
      this.parameter = parameter;
    } else {
      if (parameter.getMinSpentTime() != null) {
        this.parameter.setMinSpentTime(parameter.getMinSpentTime());
      }
      if (parameter.getMinPayAmount() != null) {
        this.parameter.setMinPayAmount(parameter.getMinPayAmount());
      }
    }
  }

  @Override
  public String toString() {
    return "GroupType:" + groupType + "\nParameter: " + parameter.toString();
  }
}
