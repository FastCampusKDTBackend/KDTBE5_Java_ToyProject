package com.smartstore.function;


import com.smartstore.util.Handleable;
import com.smartstore.util.HandleableParam;
import com.smartstore.util.Handler;

public class Back implements Handler, HandleableParam, Handleable {
    private static Back instance;

    private Back(){

    }

    public static Back getInstance() {
        if(instance == null){
            instance = new Back();
        }
        return instance;
    }

    @Override
    public void run() {

    }

    @Override
    public boolean handleChoice(String menuNumber) {
        return false;
    }

    @Override
    public int getCurrentMenuNumber() {
        return 0;
    }

    @Override
    public <T> void run(T value) {

    }
}
