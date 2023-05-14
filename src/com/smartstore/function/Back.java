package com.smartstore.function;


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
    public void run(int menu) {

    }

}
