package com.smartstore.menu;

import java.util.Arrays;

public enum MainMenuFunction {
    MAIN_MENU(0, MainMenu.getInstance()),
    GRADE_MANAGEMENT(1, GradeMenu.getInstance()),
    CUSTOMER_MANAGEMENT(2,CustomerMenu.getInstance()),
    REPORT_MANAGEMENT(3,ReportMenu.getInstance()),
    QUIT(4,Quit.getInstance());

    private final int menuNumber;
    private final Menu menuController;

    MainMenuFunction(int menuNumber, Menu menuController) {
        this.menuNumber = menuNumber;
        this.menuController = menuController;
    }

    private boolean isMatchedMenuNumber(int menuNumber){
        return this.menuNumber == menuNumber;
    }

    public static MainMenuFunction of(int menuNumber){
        return Arrays.stream(MainMenuFunction.values())
                .filter(mainMenuFunction -> mainMenuFunction.isMatchedMenuNumber(menuNumber))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Can't Find Function"));
    }

    public void run() {
        menuController.run(menuNumber);
    }
}
