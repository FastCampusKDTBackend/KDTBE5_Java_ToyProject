package com.smartstore.function;


import com.smartstore.util.Handleable;
import com.smartstore.util.HandleableParam;

public class Back implements Handleable, HandleableParam {
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
    public void run(){
    }

    @Override
    public <T> void run(T value) {

    }

}
