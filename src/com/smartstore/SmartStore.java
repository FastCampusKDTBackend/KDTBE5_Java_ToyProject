package com.smartstore;

import com.smartstore.util.Function;
import com.smartstore.function.mainmenu.MainMenuFunction;

public class SmartStore {
    public static void run(){
        //Load Initial Screen
        Function.of(0, MainMenuFunction.class).run();
    }
}
