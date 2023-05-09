package com.smartstore.function.menu;

import com.smartstore.function.MenuController;
import com.smartstore.menu.CustomerMenu;
import com.smartstore.menu.MembershipMenu;
import com.smartstore.menu.SummaryMenu;
import com.smartstore.function.Function;

public enum MainMenuFunction implements Function {
    MAIN_MENU(0, MenuManager.getInstance()),
    MEMBERSHIP_MANAGEMENT(1, MembershipMenu.getInstance()),
    CUSTOMER_MANAGEMENT(2, CustomerMenu.getInstance()),
    REPORT_MANAGEMENT(3, SummaryMenu.getInstance()),
    QUIT(4, new Quit());

    private final int menuNumber;
    private final MenuController menuController;

    MainMenuFunction(int menuNumber, MenuController menuController) {
        this.menuNumber = menuNumber;
        this.menuController = menuController;
    }

    public boolean isMatchedMenuNumber(int menuNumber){
        return this.menuNumber == menuNumber;
    }

    public void run() {
        menuController.run(menuNumber);
    }
}
