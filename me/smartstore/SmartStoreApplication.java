package me.smartstore;

import me.smartstore.domain.menu.MainMenu;

public class SmartStoreApplication {
    private static SmartStoreApplication smartStoreApplication;
    private final MainMenu mainMenu;

    public static SmartStoreApplication getInstance() {
        if (smartStoreApplication == null) {
            smartStoreApplication = new SmartStoreApplication();
        }
        return smartStoreApplication;
    }
    private SmartStoreApplication() {
        this.mainMenu = MainMenu.getInstance();
    }

    public void details() {
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.04.27");
        System.out.println(" Copyright 2023 HoonSubKim All rights reserved.");
        System.out.println("===========================================\n");
    }

    public void run() {
        details();
        mainMenu.manage();
    }

}
