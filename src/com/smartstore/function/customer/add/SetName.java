package com.smartstore.function.customer.add;

import com.smartstore.function.ValidIntegerChecker;
import com.smartstore.function.customer.CustomerMenuHandler;

public class SetName implements AddMenuHandler, ValidIntegerChecker {
    private static SetName instance;

    private SetName(){

    }

    public static SetName getInstance(){
        if(instance == null){
            return new SetName();
        }
        return instance;
    }


    @Override
    public <T> void run(T value) {

    }

    @Override
    public boolean handleChoice(String menuNumber) {
        return false;
    }

    @Override
    public void run() {
        System.out.println();

    }
}
