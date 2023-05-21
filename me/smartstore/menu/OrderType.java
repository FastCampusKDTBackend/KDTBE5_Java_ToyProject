package me.smartstore.menu;

public enum OrderType {
  ASCENDING("오름차순"),
  DESCENDING("내림차순"),
  A("A"),
  D("D");
  
  private String sortType;
  
  OrderType(String sortType) {
    this.sortType = sortType;
  }
  
  public String getSortType() {
    return this.sortType;
  }
  
  public void setSortType(String sortType) {
    this.sortType = sortType;
  }
  
  public OrderType replaceFullName() {
    if (this == A)
      return ASCENDING; 
    if (this == D)
      return DESCENDING; 
    return this;
  }
  
  public static int size() {
    return values().length / 2;
  }
}
