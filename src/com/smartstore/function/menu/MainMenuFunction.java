package com.smartstore.function.menu;

import com.smartstore.function.Back;
import com.smartstore.function.MenuHandler;
import com.smartstore.menu.CustomerMenu;
import com.smartstore.menu.MainMenu;
import com.smartstore.menu.MembershipMenu;
import com.smartstore.menu.SummaryMenu;
import com.smartstore.function.Function;

public enum MainMenuFunction implements Function {
    MAIN_MENU(0, MainMenu.getInstance()),
    MEMBERSHIP_MANAGEMENT(1, MembershipMenu.getInstance()),
    CUSTOMER_MANAGEMENT(2, CustomerMenu.getInstance()),
    REPORT_MANAGEMENT(3, Back.getInstance()),
    QUIT(4, Back.getInstance());

    private final int menuNumber;
    private final MenuHandler menuHandler;

    MainMenuFunction(int menuNumber, MenuHandler menuHandler) {
        this.menuNumber = menuNumber;
        this.menuHandler = menuHandler;
    }

    @Override
    public int getMenuNumber() {
        return menuNumber;
    }

    @Override
    public MenuHandler getMenuController() {
        return menuHandler;
    }

    @Override
    public void run() {
        menuHandler.run(menuNumber);
    }
}
