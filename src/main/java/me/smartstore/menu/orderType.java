package me.smartstore.menu;

import me.smartstore.customer.SortBy;

public enum OrderType {
    ACSENDING,
    DESCENDING;

    private static final OrderType[] orderTypes = OrderType.values();

    public static OrderType getOrderType(int i) {
        return orderTypes[i-1];
    }
}
