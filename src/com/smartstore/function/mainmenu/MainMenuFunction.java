package com.smartstore.function.mainmenu;

import com.smartstore.function.Back;
import com.smartstore.function.Handler;
import com.smartstore.function.Function;

public enum MainMenuFunction implements Function {
    MAIN_MENU(0, MainMenu.getInstance()),
    MEMBERSHIP_MANAGEMENT(1, MembershipMenu.getInstance()),
    CUSTOMER_MANAGEMENT(2, CustomerMenu.getInstance()),
    REPORT_MANAGEMENT(3, Back.getInstance()),
    QUIT(4, Back.getInstance());

    private final int menuNumber;
    private final Handler mainMenuHandler;

    MainMenuFunction(int menuNumber, Handler mainMenuHandler) {
        this.menuNumber = menuNumber;
        this.mainMenuHandler = mainMenuHandler;
    }

    @Override
    public int getMenuNumber() {
        return menuNumber;
    }

    @Override
    public Handler getMenuHandler() {
        return mainMenuHandler;
    }

    @Override
    public void run() {
        mainMenuHandler.run(menuNumber);
    }
}
