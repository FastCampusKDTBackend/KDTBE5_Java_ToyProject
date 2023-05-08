package me.smartstore.menu;

public enum OrderType {

    A("오름차순"), D("내림차순"),
    ASCENDING("오름차순"), DESCENDING("내림차순");


    String orderType = "";

    OrderType(String orderType) {
        this.orderType = orderType;
    }

    public OrderType replaceFullName() {
        if (this == A) return ASCENDING;
        else if (this == D) return DESCENDING;
        return this;
    }
}
