package com.smartstore.function.customer;

import com.smartstore.function.Function;
import com.smartstore.function.MenuController;
import com.smartstore.function.menu.Back;

public enum CustomerFunction implements Function {
    ADD(1, new AddCustomer()),
    VIEW(2, new ViewCustomer()),
    UPDATE(3,new UpdateCustomer()),
    DELETE(3,new DeleteCustomer()),
    BACK(5, new Back());


    private final int menuNumber;
    private final MenuController menuController;
    CustomerFunction(int menuNumber, MenuController menuController) {
        this.menuNumber = menuNumber;
        this.menuController = menuController;
    }

    @Override
    public int getMenuNumber() {
        return this.menuNumber;
    }

    @Override
    public MenuController getMenuController() {
        return this.menuController;
    }

}
