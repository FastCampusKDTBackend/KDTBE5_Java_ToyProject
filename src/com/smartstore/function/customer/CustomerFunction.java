package com.smartstore.function.customer;

import com.smartstore.util.Function;
import com.smartstore.util.Handleable;
import com.smartstore.function.Back;
import com.smartstore.function.customer.add.AddCustomer;
import com.smartstore.function.customer.delete.DeleteCustomer;
import com.smartstore.function.customer.update.UpdateCustomer;
import com.smartstore.function.customer.view.ViewCustomer;

public enum CustomerFunction implements Function<Handleable> {
    ADD(1, AddCustomer.getInstance(), "Add Customer Data"),
    VIEW(2, ViewCustomer.getInstance(), "View Customer Data"),
    UPDATE(3, UpdateCustomer.getInstance(), "Update Customer Data"),
    DELETE(4, DeleteCustomer.getInstance(), "Delete Customer Data"),
    BACK(5, Back.getInstance(), "Return to Prev Menu");

    private final int menuNumber;
    private final Handleable handler;

    private final String menuText;
    CustomerFunction(int menuNumber, Handleable handler, String menuText) {
        this.menuNumber = menuNumber;
        this.handler = handler;
        this.menuText = menuText;
    }

    @Override
    public String getMenuText() {
        return menuText;
    }

    @Override
    public int getMenuNumber() {
        return this.menuNumber;
    }

    @Override
    public Handleable getMenuHandler() {
        return this.handler;
    }

}
