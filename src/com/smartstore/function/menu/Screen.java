package com.smartstore.function.menu;

import java.util.Arrays;

public enum Screen {

    SPLASH(new String[]{"this is splash screen"}, -1),

    MAIN_MENU(new String[]{
            "Membership Management",
            "Customer Management",
            "Report",
            "Quit"}, 0),
    MEMBERSHIP(new String[]{
            "Set Membership Requirement",
            "View Membership Requirement",
            "Update Membership Requirement",
            "Back"}, 1),
    CUSTOMER(new String[]{
            "Add Customer Data",
            "View Customer Data",
            "Update Customer Data",
            "Delete Customer Data",
            "Back"}, 2),
    REPORT(new String[]{
            "Report Summary",
            "Sort",
            "Back"}, 3),
    QUIT(new String[]{"EXIT PROGRAM"}, 4);

    private final String[] menus;
    private final int menuNumber;

    Screen(String[] message, int menuNumber) {
        this.menus = message;
        this.menuNumber = menuNumber;
    }

    private boolean isMatchedMenuNumber(int menuNumber){
        return this.menuNumber == menuNumber;
    }

    public static Screen of(int menuNumber){
        return Arrays.stream(Screen.values())
                .filter(screen -> screen.isMatchedMenuNumber(menuNumber))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Can't Find Menu"));
    }

    public String[] getMenus() {
        return menus;
    }
}
