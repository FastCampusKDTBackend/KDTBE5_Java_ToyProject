package com.smartstore.function;

public class Back implements MenuHandler {

    private static Back instance;

    private Back(){

    }

    public static Back getInstance() {
        if(instance == null){
            return new Back();
        }
        return instance;
    }

    public boolean isExit(){
        return true;
    }


    @Override
    public boolean handleChoice(String menuNumber) {
        return false;
    }

    @Override
    public void run(int menuNumber) {
    }

}
