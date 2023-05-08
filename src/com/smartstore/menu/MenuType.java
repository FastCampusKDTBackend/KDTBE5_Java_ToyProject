package com.smartstore.menu;

import java.util.Arrays;

public enum MenuType {
    MAIN_MENU(0, MainMenu.getInstance()),
    GRADE_MANAGEMENT(1, GradeMenu.getInstance()),
    CUSTOMER_MANAGEMENT(2,CustomerMenu.getInstance()),
    REPORT_MANAGEMENT(3,ReportMenu.getInstance()),
    QUIT(4,Quit.getInstance());

        private final int menuNumber;
        private final MenuController menuController;

    MenuType(int menuNumber, MenuController menuController) {
        this.menuNumber = menuNumber;
        this.menuController = menuController;
    }

    private boolean isValidNumber(int menuNumber){
        return this.menuNumber == menuNumber;
    }

    public static MenuType of(int menuNumber){
        return Arrays.stream(MenuType.values())
                .filter(menuType -> menuType.isValidNumber(menuNumber))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Can't Find Function"));
    }

    public void run() {
        menuController.run();
    }
}
