package com.smartstore.menu.mainmenu;

import com.smartstore.menu.Menu;
import com.smartstore.menu.customer.CustomerMenu;
import com.smartstore.menu.membership.MembershipMenu;
import com.smartstore.menu.report.ReportMenu;
import com.smartstore.util.Function;

public enum MainMenuFunction implements Function {
    MAIN_MENU(0, MainMenu.getInstance()),
    MEMBERSHIP_MANAGEMENT(1, MembershipMenu.getInstance()),
    CUSTOMER_MANAGEMENT(2, CustomerMenu.getInstance()),
    REPORT_MANAGEMENT(3, ReportMenu.getInstance()),
    QUIT(4, Quit.getInstance());

    private final int menuNumber;
    private final Menu menuController;

    MainMenuFunction(int menuNumber, Menu menuController) {
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
