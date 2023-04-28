package me.smartstore.domain.customer;

import me.smartstore.utils.MyArray;

public class Customers extends MyArray<Customer> {
    private static Customers allCustomers;

    public static Customers getInstance() {
        if (allCustomers == null) {
            allCustomers = new Customers();
        }
        return allCustomers;
    }

    private Customers() {}

    // TODO: Refresh 메서드 구현 (새로운 고객 추가, 분류기준 변경시 작동)
    public void refresh() {}
}
