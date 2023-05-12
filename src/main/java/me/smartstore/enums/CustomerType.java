package me.smartstore.enums;

/**
 * 고객 유형
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-10
 */
public enum CustomerType {
  NONE("해당없음"),
  GENERAL("일반고객"),
  VIP("우수고객"),
  VVIP("최우수고객"),
  N("해당없음"),
  G("일반고객"),
  V("우수고객"),
  VV("최우수고객");

  private final String description;

  CustomerType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public CustomerType replaceFullName() {
    if (this == N) return NONE;
    else if (this == G) return GENERAL;
    else if (this == V) return VIP;
    else if (this == VV) return VVIP;
    else return this;
  }
}
