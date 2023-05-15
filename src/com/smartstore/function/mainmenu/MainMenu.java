package com.smartstore.function.mainmenu;

import com.smartstore.util.Define;
import com.smartstore.util.Function;
import com.smartstore.util.PrettyTerminal;

public class MainMenu implements MainMenuHandler {
    private static MainMenu instance;
    public static MainMenu getInstance() {
        if(instance == null){
            instance = new MainMenu();
        }
        return instance;
    }

    private MainMenu(){

    }

    private void printSplashScreen(){
        System.out.println(Define.SPLASHSCREEN);
    }

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == MainMenuFunction.QUIT.getMenuNumber()) {
            return true;
        }
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), MainMenuFunction.class).run(Integer.parseInt(menuNumber));
        PrettyTerminal.cls();
        return false;
    }

    @Override
    public void run() {
        printSplashScreen();
        MainMenuHandler.super.run();
    }

}
