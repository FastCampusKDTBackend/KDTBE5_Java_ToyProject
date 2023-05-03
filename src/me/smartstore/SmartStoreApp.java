package me.smartstore;

import me.smartstore.menu.*;

public class SmartStoreApp {
    private static SmartStoreApp smartStoreApp;
    private final MainMenu mainMenu=MainMenu.getInstance();

    private SmartStoreApp() {
    }

    public static SmartStoreApp getInstance(){
        if (smartStoreApp == null) {
            smartStoreApp = new SmartStoreApp();
        }
        return smartStoreApp;
    }

    public void details(){
        System.out.println("\n\n===========================================");
        System.out.println(" Title : SmartStore Customer Classification");
        System.out.println(" Release Date : 23.04.27");
        System.out.println(" Copyright 2023 Chaewon Eom All rights reserved.");
        System.out.println("===========================================\n");
    }

    public SmartStoreApp test(){

        return this; // smartStoreApp
    }

    public void run(){
        details();
        mainMenu.root();
    }

}
