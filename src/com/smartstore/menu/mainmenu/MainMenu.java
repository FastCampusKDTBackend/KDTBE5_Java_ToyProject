package com.smartstore.menu.mainmenu;

import com.smartstore.membership.MembershipFunction;
import com.smartstore.menu.Menu;
import com.smartstore.menu.Screen;
import com.smartstore.util.Function;

import java.util.Arrays;

public class MainMenu<T extends Function> implements Menu {
    @Override
    public void handleChoice(int menuNumber) {
        System.out.println("Handel called");
        //call Menu with menuNumber
        @SuppressWarnings("unchecked")
        T function = (T) Function.of(menuNumber, MembershipFunction.class);
        function.run();
    }

    public static MainMenu getInstance() {
        if(instance == null){
            instance = new MainMenu();
        }
        return instance;
    }

    private void printSlashScreen(){
        System.out.println(Arrays.toString(Screen.of(-1).getMenus()));
    }

    @Override
    public void run(int menuNumber){
        //print splash screen
        printSlashScreen();
        //call run(super)
        Menu.super.run(menuNumber);
    }
    private static MainMenu instance;

    private MainMenu(){

    }
}
