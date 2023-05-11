package me.smartstore;

import me.smartstore.group.Group;
import me.smartstore.group.GroupType;
import me.smartstore.group.Groups;
import me.smartstore.group.Parameter;
import me.smartstore.menu.MainMenu;

public class SmartStoreApp {
    private final Groups allGroups = Groups.getInstance();
    private final MainMenu mainMenu = MainMenu.getInstance();


    // singleton
    private static SmartStoreApp smartStoreApp;

    public static SmartStoreApp getInstance() {
        if (smartStoreApp == null) {
            smartStoreApp = new SmartStoreApp();
        }
        return smartStoreApp;
    }

    private SmartStoreApp() {}

    public void details() {
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.05.10");
        System.out.println(" Copyright 2023 Jongmin No rights reserved.");
        System.out.println("===========================================\n");
    }

    public void run() {
        details();
        allGroups.add(new Group(new Parameter(0, 0), GroupType.NONE));
        mainMenu.manage();
    }
}
