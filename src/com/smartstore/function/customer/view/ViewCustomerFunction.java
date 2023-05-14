package com.smartstore.function.customer.view;

import com.smartstore.function.Back;
import com.smartstore.function.Function;
import com.smartstore.function.Handleable;

public enum ViewCustomerFunction implements Function {

    ALL(1, ViewCustomerAll.getInstance(), "ALL Customer List"),
    PAGING(2, ViewCustomerByPaging.getInstance(), "Customer List By Paging"),
    BY_ID(3, ViewCustomerById.getInstance(), "Find Customer by Id"),
    BACK(4, Back.getInstance(), "Return to Prev Menu");


    private final int menuNumber;
    private final Handleable handler;
    private final String menuText;
    ViewCustomerFunction(int menuNumber, Handleable handler, String menuText) {
        this.menuNumber = menuNumber;
        this.handler = handler;
        this.menuText = menuText;
    }

    @Override
    public int getMenuNumber() {
        return this.menuNumber;
    }

    @Override
    public String getMenuText() { return this.menuText; }

    @Override
    public Handleable getMenuHandler() {
        return this.handler;
    }

}
