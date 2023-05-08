package com.smartstore;

import com.smartstore.menu.mainmenu.MainMenuFunction;

public class SmartStore {
    public static void run(){
        //Load Initial Screen
        MainMenuFunction mainMenuFunction = MainMenuFunction.of(0);
        mainMenuFunction.run();
    }
}
