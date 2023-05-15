package com.smartstore.function.mainmenu;

import com.smartstore.function.*;
import com.smartstore.util.Function;
import com.smartstore.util.Handleable;
import com.smartstore.util.HandleableParam;

public enum MainMenuFunction implements Function<Handleable> {
    MAIN_MENU(0, MainMenu.getInstance(), "Main Menu"),
    MEMBERSHIP_MANAGEMENT(1, MembershipMenu.getInstance(), "Membership Management"),
    CUSTOMER_MANAGEMENT(2, CustomerMenu.getInstance(), "Customer Management"),
    REPORT_MANAGEMENT(3, SummaryMenu.getInstance(), "Report"),
    QUIT(4, Back.getInstance(), "Quit");

    private final int menuNumber;
    private final Handleable handler;

    private final String menuText;

    MainMenuFunction(int menuNumber, Handleable handler, String menuText) {
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
        return menuNumber;
    }

    public Handleable getMenuHandler() {
        return handler;
    }

    @Override
    public <T> void run(T value) {
        getMenuHandler().run();
    }
}
