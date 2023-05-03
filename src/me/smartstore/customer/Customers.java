package me.smartstore.customer;

import me.smartstore.collections.DArray;

public class Customers extends DArray {
    private static Customers customers;

    public static Customers getInstance() {
        if (customers == null) {
            customers = new Customers();
        }
        return customers;
    }

    // @TODO:  refreshGroupType do not implement yet.
    private void refreshGroupType(){

    }
}
