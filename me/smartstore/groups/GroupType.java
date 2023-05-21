package me.smartstore.groups;

public enum GroupType {
    NONE("비회원"),
    GENERAL("일반"),
    VIP("우수"),
    VVIP("최우수"),
    N("비회원"),
    G("일반"),
    V("우수"),
    VV("최우수");
  
  String groupType = "";
  
  GroupType(String groupType) {
    this.groupType = groupType;
  }
  
  public String getGroupType() {
    return this.groupType;
  }
  
  public void setGroupType(String groupType) {
    this.groupType = groupType;
  }
  
  public GroupType replaceFullName() {
    if (this == N)
      return NONE; 
    if (this == G)
      return GENERAL; 
    if (this == V)
      return VIP; 
    if (this == VV)
      return VVIP; 
    return this;
  }
  
  public static int size() {
    return (values()).length / 2;
  }
}
