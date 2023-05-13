package com.smartstore.function.customer;

import com.smartstore.function.Function;
import com.smartstore.function.Handleable;
import com.smartstore.function.Back;
import com.smartstore.function.customer.add.AddCustomer;
import com.smartstore.function.customer.delete.DeleteCustomer;
import com.smartstore.function.customer.update.UpdateCustomer;
import com.smartstore.function.customer.view.ViewCustomer;

public enum CustomerFunction implements Function {
    ADD(1, AddCustomer.getInstance()),
    VIEW(2, ViewCustomer.getInstance()),
    UPDATE(3, UpdateCustomer.getInstance()),
    DELETE(4, DeleteCustomer.getInstance()),
    BACK(5, Back.getInstance());


    private final int menuNumber;
    private final Handleable handler;
    CustomerFunction(int menuNumber, Handleable handler) {
        this.menuNumber = menuNumber;
        this.handler = handler;
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
