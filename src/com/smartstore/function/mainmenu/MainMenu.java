package com.smartstore.function.mainmenu;

import com.smartstore.util.Function;

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
        System.out.println(Function.of(0, MainMenuFunction.class).getMenuText());
    }

    @Override
    public boolean handleChoice(String menuNumber) {
        if(Integer.parseInt(menuNumber) == MainMenuFunction.QUIT.getMenuNumber()) {
            return true;
        }
        //call Menu with menuNumber
        Function.of(Integer.parseInt(menuNumber), MainMenuFunction.class).run(Integer.parseInt(menuNumber));
        return false;
    }

    @Override
    public void run() {
        printSplashScreen();
        MainMenuHandler.super.run();
    }

}
